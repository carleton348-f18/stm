package stm;

/**
 * This exception is thrown if the user attempts to access a TxObject
 * outside of a transaction.
 */
public class NoActiveTxException extends RuntimeException {

}
