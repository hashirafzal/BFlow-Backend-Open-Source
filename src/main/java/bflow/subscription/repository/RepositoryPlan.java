package bflow.subscription.repository;

import bflow.subscription.entities.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryPlan
        extends JpaRepository<Plan, UUID> {

    Optional<Plan> findByCode(String code);
}