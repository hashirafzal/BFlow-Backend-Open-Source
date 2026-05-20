package bflow.budget.services;

import bflow.budget.DTO.BudgetRequest;
import bflow.budget.RepositoryBudget;
import bflow.budget.entity.Budget;
import bflow.budget.enums.BudgetScope;
import bflow.budget.enums.PeriodType;
import bflow.common.exception.BudgetOverlapException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BudgetOverlapValidationService {

    private final RepositoryBudget repositoryBudget;

    public void validateCreateOverlap(
            final BudgetRequest request,
            final UUID userId
    ) {

        boolean exists;

        if (request.getScope() == BudgetScope.WALLET) {

            exists = repositoryBudget
                    .existsByWalletIdAndUserIdAndScopeAndPeriod(
                            request.getWalletId(),
                            userId,
                            request.getScope(),
                            request.getPeriod()
                    );

        } else {

            exists = repositoryBudget
                    .existsByWalletIdAndUserIdAndCategoryIdAndPeriod(
                            request.getWalletId(),
                            userId,
                            request.getCategoryId(),
                            request.getPeriod()
                    );
        }

        if (exists) {
            throw new BudgetOverlapException(
                    "A budget already exists for this scope and period"
            );
        }
    }

    public void validatePatchOverlap(
            final Budget budget,
            final BudgetScope scope,
            final UUID categoryId,
            final PeriodType period,
            final UUID userId
    ) {

        boolean exists;

        if (scope == BudgetScope.WALLET) {

            exists = repositoryBudget
                    .existsByWalletIdAndUserIdAndScopeAndPeriodAndIdNot(
                            budget.getWallet().getId(),
                            userId,
                            scope,
                            period,
                            budget.getId()
                    );

        } else {

            exists = repositoryBudget
                    .existsByWalletIdAndUserIdAndCategoryIdAndPeriodAndIdNot(
                            budget.getWallet().getId(),
                            userId,
                            categoryId,
                            period,
                            budget.getId()
                    );
        }

        if (exists) {
            throw new BudgetOverlapException(
                    "A budget already exists for this scope and period"
            );
        }
    }
}