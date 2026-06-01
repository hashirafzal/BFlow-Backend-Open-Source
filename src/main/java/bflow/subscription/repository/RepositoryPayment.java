package bflow.subscription.repository;

import bflow.subscription.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryPayment extends JpaRepository<Payment, UUID> {
    List<Payment> findByUserId(UUID userId);

    Optional<Payment> findByExternalTransactionId(
            String externalTransactionId
    );
}
