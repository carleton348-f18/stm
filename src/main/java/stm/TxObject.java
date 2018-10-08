package stm;

/**
 * A stm.TxObject is a special kind of object that can be read and written as part
 * of a transaction.
 *
 * @param <T> type of the value stored in this stm.TxObject
 */
public final class TxObject<T> {
    private T value;

    public TxObject(T value) {
        this.value = value;
    }

    public T read()  {
        // TODO implement me
        return value;
    }

    public void write(T value)  {
        // TODO implement me
        this.value = value;
    }
}
