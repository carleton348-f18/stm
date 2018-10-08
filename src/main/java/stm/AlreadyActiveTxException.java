package stm;

/**
 * This exception is thrown if the user attempts to start a transaction from within another.
 */
public class AlreadyActiveTxException extends RuntimeException {

}
