package bflow.subscription.entities;

import bflow.auth.entities.User;
import bflow.subscription.enums.PaymentStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Getter
@Setter
public class Payment {

    /** Precision used for monetary columns. */
    private static final int MONEY_PRECISION = 10;

    /** Primary identifier for the payment. */
    @Id
    @GeneratedValue
    private UUID id;

    /** User who made the payment. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    /** Subscription associated with the payment. */
    @ManyToOne(optional = false)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    /** Amount charged for the payment. */
    @Column(nullable = false, precision = MONEY_PRECISION, scale = 2)
    private BigDecimal amount;

    /** Payment provider name. */
    @Column(nullable = false)
    private String provider;

    /** External provider transaction identifier. */
    @Column(nullable = false, unique = true)
    private String externalTransactionId;

    /** ISO currency code used for the payment. */
    @Column(nullable = false)
    private String currency;

    /** Current status of the payment. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    /** Creation timestamp for the payment record. */
    @CreationTimestamp
    private Instant createdAt;
}
