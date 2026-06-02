package bflow.subscription.repository;

import bflow.subscription.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RepositoryPayment extends JpaRepository<Payment, UUID> {

    /**
     * Find all payments for a given user.
     *
     * @param userId the user identifier
     * @return list of payments for the user
     */
    List<Payment> findByUserId(UUID userId);

    /**
     * Find a payment by its external transaction identifier.
     *
     * @param externalTransactionId external payment id
     * @return optional payment matching the identifier
     */
    Optional<Payment> findByExternalTransactionId(
            String externalTransactionId
    );
}
