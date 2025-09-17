package com.example.demo.mq

import com.example.demo.service.UserCreatedEvent
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class UserDomainEventHandler(
    private val publisher: UserRegisteredPublisher
) {
    private val log = LoggerFactory.getLogger(javaClass)

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun onUserCreated(event: UserCreatedEvent) {
        log.info("AFTER_COMMIT: publishing user_registered for {}", event.email)
        publisher.publish(event.email)
    }
}
