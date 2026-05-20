package bflow.budget.services;

import bflow.budget.enums.BudgetScope;
import bflow.common.exception.InvalidBudgetDateException;
import bflow.common.exception.InvalidBudgetScopeException;
import bflow.common.exception.InvalidBudgetThresholdException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Component
public class BudgetValidationService {

    public void validateStartDate(final LocalDate startDate) {

        if (startDate == null) {
            throw new InvalidBudgetDateException(
                    "Start date is required"
            );
        }

        if (startDate.isAfter(LocalDate.now())) {
            throw new InvalidBudgetDateException(
                    "Start date cannot be in the future"
            );
        }
    }

    public void validateBudgetConstraints(
            final BudgetScope scope,
            final UUID categoryId,
            final Integer warning,
            final Integer critical
    ) {

        validateThresholds(warning, critical);
        validateBudgetScope(scope, categoryId);
    }

    public void validateThresholds(
            final Integer warning,
            final Integer critical
    ) {

        if (warning != null
                && critical != null
                && warning >= critical) {

            throw new InvalidBudgetThresholdException(
                    "Warning threshold must be lower than critical threshold"
            );
        }
    }

    public void validateAmount(final BigDecimal amount) {

        if (amount == null) {
            throw new IllegalArgumentException(
                    "Budget amount is required"
            );
        }

        if (amount.compareTo(BigDecimal.ONE) < 0) {
            throw new IllegalArgumentException(
                    "Budget amount must be greater than or equal to 1"
            );
        }
    }

    public void validateBudgetScope(
            final BudgetScope scope,
            final UUID categoryId
    ) {

        if (scope == BudgetScope.CATEGORY
                && categoryId == null) {

            throw new InvalidBudgetScopeException(
                    "Category budget requires categoryId"
            );
        }
    }
}