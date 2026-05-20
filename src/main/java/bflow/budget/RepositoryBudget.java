package bflow.budget;

import bflow.budget.entity.Budget;
import bflow.budget.enums.BudgetScope;
import bflow.budget.enums.PeriodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository interface for Budget entities.
 */
@Repository
public interface RepositoryBudget extends JpaRepository<Budget, UUID> {
    /**
     * Find all budgets for a specific wallet.
     *
     * @param walletId the wallet ID
     * @return list of budgets
     */
    List<Budget> findByWalletId(UUID walletId);

    Optional<Budget> findByIdAndUserId(UUID budgetId, UUID userId);

    boolean existsByWalletIdAndUserIdAndScopeAndPeriod(
            UUID walletId,
            UUID userId,
            BudgetScope scope,
            PeriodType period
    );

    boolean existsByWalletIdAndUserIdAndCategoryIdAndPeriod(
            UUID walletId,
            UUID userId,
            UUID categoryId,
            PeriodType period
    );

    boolean existsByWalletIdAndUserIdAndScopeAndPeriodAndIdNot(
            UUID walletId,
            UUID userId,
            BudgetScope scope,
            PeriodType period,
            UUID id
    );

    boolean existsByWalletIdAndUserIdAndCategoryIdAndPeriodAndIdNot(
            UUID walletId,
            UUID userId,
            UUID categoryId,
            PeriodType period,
            UUID id
    );
}
