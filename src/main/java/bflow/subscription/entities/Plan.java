package bflow.subscription.entities;

import bflow.subscription.enums.BillingPeriod;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "plans")
@Getter
@Setter
public class Plan {
    /** Maximum length for the plan code column. */
    private static final int PLAN_CODE_LENGTH = 50;

    /** Maximum length for the plan name column. */
    private static final int PLAN_NAME_LENGTH = 100;

    /** Precision used for monetary columns. */
    private static final int MONEY_PRECISION = 10;

    /** Primary key for the plan. */
    @Id
    @GeneratedValue
    private UUID id;

    /** Unique code for the subscription plan. */
    @Column(nullable = false, unique = true, length = PLAN_CODE_LENGTH)
    private String code;

    /** Display name for the plan. */
    @Column(nullable = false, length = PLAN_NAME_LENGTH)
    private String name;

    /** Price of the plan. */
    @Column(nullable = false, precision = MONEY_PRECISION, scale = 2)
    private BigDecimal price;

    /** Billing frequency for the plan. */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingPeriod billingPeriod;

    /** Maximum number of wallets allowed for this plan. */
    @Column(nullable = false)
    private Integer maxWallets;

    /** Maximum number of budgets allowed for this plan. */
    @Column(nullable = false)
    private Integer maxBudgets;

    /** Maximum number of recurring transactions allowed. */
    @Column(nullable = false)
    private Integer maxRecurringTransactions;

    /** Maximum number of shared wallets permitted. */
    private Integer maxSharedWallets;

    /** Maximum wallet members allowed. */
    private Integer maxWalletMembers;

    /** Whether dashboard customization is enabled. */
    @Column(nullable = false)
    private boolean dashboardCustomization;

    /** Whether shared wallet creation is enabled. */
    @Column(nullable = false)
    private boolean canCreateSharedWallets;

    /** Whether export functionality is enabled. */
    @Column(nullable = false)
    private boolean exportEnabled;

    /** Indicates if the plan is active. */
    @Column(nullable = false)
    private boolean active = true;

    /** Timestamp when the plan was created. */
    @CreationTimestamp
    private Instant createdAt;

    /** Timestamp when the plan was last updated. */
    @UpdateTimestamp
    private Instant updatedAt;
}
