package bflow.subscription.services;

import bflow.common.exception.NotFoundException;
import bflow.subscription.entities.Plan;
import bflow.subscription.repository.RepositoryPlan;
import bflow.subscription.repository.RepositorySubscription;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlanService {

    private static final String FREE_PLAN_CODE = "FREE";

    private final RepositoryPlan repositoryPlan;

    private final RepositorySubscription repositorySubscription;

    public Plan getFreePlan() {

        return repositoryPlan
                .findByCode(FREE_PLAN_CODE)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Free plan not found"
                        ));
    }

    public Plan getByCode(final String code) {

        return repositoryPlan
                .findByCode(code)
                .orElseThrow(() ->
                        new NotFoundException(
                                "Plan not found"
                        ));
    }

    public List<Plan> getActivePlans() {

        return repositoryPlan.findAll()
                .stream()
                .filter(Plan::isActive)
                .toList();
    }

}