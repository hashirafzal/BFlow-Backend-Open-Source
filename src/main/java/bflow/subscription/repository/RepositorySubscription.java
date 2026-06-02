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
public interface RepositorySubscription
        extends JpaRepository<Subscription, UUID> {

    /**
     * Find a subscription for a user that matches a specific status.
     *
     * @param userId the user identifier
     * @param status subscription status
     * @return optional subscription matching user and status
     */
    Optional<Subscription> findByUserIdAndStatus(
            UUID userId,
            SubscriptionStatus status
    );

    /**
     * Check whether a user already has any subscription.
     *
     * @param userId the user identifier
     * @return true when a subscription exists for the user
     */
    boolean existsByUserId(UUID userId);

    /**
     * Find a subscription for a user.
     *
     * @param userId the user identifier
     * @return optional subscription for the user
     */
    Optional<Subscription> findByUserId(UUID userId);

    /**
     * Find subscriptions by their status.
     *
     * @param status subscription status
     * @return list of subscriptions matching the status
     */
    List<Subscription> findByStatus(
            SubscriptionStatus status
    );

    /**
     * Find subscriptions with auto-renew enabled that will bill
     * before a given date.
     *
     * @param date threshold billing date
     * @return list of subscriptions due for renewal
     */
    List<Subscription> findByAutoRenewTrueAndNextBillingAtBefore(
            Instant date
    );
}
