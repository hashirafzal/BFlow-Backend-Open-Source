package bflow.subscription.repository;

import bflow.subscription.entities.Subscription;
import bflow.subscription.enums.SubscriptionStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositorySubscription extends JpaRepository<Subscription, UUID> {
    Optional<Subscription> findByUserIdAndStatus(
            UUID userId,
            SubscriptionStatus status
    );

    boolean existsByUserId(UUID userId);

    Optional<Subscription> findByUserId(UUID userId);

    List<Subscription> findByStatus(
            SubscriptionStatus status
    );

    List<Subscription> findByAutoRenewTrueAndNextBillingAtBefore(
            Instant date
    );
}
