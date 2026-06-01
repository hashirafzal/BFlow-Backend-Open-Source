package bflow.subscription.entities;

import bflow.subscription.enums.BillingPeriod;
import jakarta.persistence.*;
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

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BillingPeriod billingPeriod;

    @Column(nullable = false)
    private Integer maxWallets;

    @Column(nullable = false)
    private Integer maxBudgets;

    @Column(nullable = false)
    private Integer maxRecurringTransactions;

    private Integer maxSharedWallets;
    private Integer maxWalletMembers;

    @Column(nullable = false)
    private boolean dashboardCustomization;

    @Column(nullable = false)
    private boolean canCreateSharedWallets;

    @Column(nullable = false)
    private boolean exportEnabled;

    @Column(nullable = false)
    private boolean active = true;

    @CreationTimestamp
    private Instant createdAt;

    @UpdateTimestamp
    private Instant updatedAt;
}
