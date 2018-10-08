package stm;

/**
 * This class holds transactional state for a single thread. You should use
 * java.lang.ThreadLocal to allocate a TxManager to each Java thread. This
 * class is only used within the STM implementation, so it and its members are
 * set to package (default) visibility.
 */
class TxManager {

    /**
     * Start a transaction by initializing any necessary state. This method
     * should throw AlreadyActiveTxException if a transaction
     * is already being executed.
     */
    void start() {
        // TODO implement me
    }

    /**
     * Try to commit a completed transaction. This method should update any
     * written TxObjects, acquiring locks on those objects as needed.
     *
     * @return true if the commit succeeds, false if the transaction aborted
     */
    boolean commit() {
        // TODO implement me
        return false;
    }

    /**
     * This method cleans up any transactional state if a transaction aborts.
     */
    void abort() {
        // TODO implement me
    }
}