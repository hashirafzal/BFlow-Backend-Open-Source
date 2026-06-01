package bflow.subscription.services;

import bflow.auth.entities.User;
import bflow.common.exception.NotFoundException;
import bflow.subscription.entities.Plan;
import bflow.subscription.entities.Subscription;
import bflow.subscription.enums.BillingPeriod;
import bflow.subscription.enums.SubscriptionStatus;
import bflow.subscription.repository.RepositoryPlan;
import bflow.subscription.repository.RepositorySubscription;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubscriptionService {

    private final RepositorySubscription subscriptionRepository;

    private final PlanService planService;

    public Plan getCurrentPlan(UUID userId) {
        return getActiveSubscription(userId)
                .getPlan();
    }

    public Subscription getActiveSubscription(UUID userId) {
        return subscriptionRepository
                .findByUserIdAndStatus(
                        userId,
                        SubscriptionStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new NotFoundException(
                                "Active subscription not found"
                        ));
    }

    public Subscription createFreeSubscription(final User user) {

        if (subscriptionRepository.existsByUserId(user.getId())) {
            return subscriptionRepository
                    .findByUserId(user.getId())
                    .orElseThrow();
        }

        Plan freePlan = planService.getFreePlan();

        Subscription subscription = new Subscription();

        subscription.setUser(user);
        subscription.setPlan(freePlan);
        subscription.setStatus(SubscriptionStatus.ACTIVE);
        subscription.setCurrentPrice(freePlan.getPrice());

        Instant now = Instant.now();

        subscription.setStartsAt(now);

        if (freePlan.getBillingPeriod() == BillingPeriod.YEARLY) {
            subscription.setEndsAt(now.plus(365, ChronoUnit.DAYS));
            subscription.setNextBillingAt(now.plus(365, ChronoUnit.DAYS));
        } else {
            subscription.setEndsAt(now.plus(30, ChronoUnit.DAYS));
            subscription.setNextBillingAt(now.plus(30, ChronoUnit.DAYS));
        }

        subscription.setAutoRenew(false);

        return subscriptionRepository.save(subscription);
    }
}