package stm;

import java.util.concurrent.Callable;

/**
 * This class coordinates transaction execution. You can execute a transaction
 * using execute().
 */
public class STM {

    /**
     * Execute a transaction and return its result. This method needs to
     * repeatedly start, execute, and commit the transaction until it
     * successfully commits.
     *
     * @param <T> return type of the transaction
     * @param tx transaction to be executed
     * @return result of the transaction
     */
    public static <T> T execute(Callable<T> tx) {
        // TODO implement me: definitely not complete
        T result = null;
        try {
            result = tx.call();
        } catch (Exception e) {
            System.out.println("Unhandled exception.");
            e.printStackTrace();
        }

        return result;
    }
}
