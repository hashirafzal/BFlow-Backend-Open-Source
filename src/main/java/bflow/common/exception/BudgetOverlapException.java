package bflow.common.exception;

/**
 * Exception thrown when a budget overlaps another budget.
 */
public class BudgetOverlapException
        extends RuntimeException {

    public BudgetOverlapException(final String message) {
        super(message);
    }
}
