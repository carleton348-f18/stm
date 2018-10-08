import org.junit.Test;
import stm.STM;
import stm.TxObject;

import java.util.concurrent.Callable;

import static org.junit.Assert.assertEquals;

/**
 * A simple example of a program that uses STM for synchronization.
 */
public class SimpleTransactionTest {
    // Create a transactional object that holds an integer.
    private static TxObject<Integer> x = new TxObject<>(0);

    @Test
    public void transactionTest() throws Exception {

        Callable<Integer> incrementFiveTimes = () -> {
            // This print may happen more than once if the transaction aborts
            // and restarts.

            // This loop repeatedly reads and writes a TxObject. The read and
            // write operations should all behave as if the entire transaction
            // happened exactly once, and as if there were no
            // intervening reads or writes from other threads.
            for (int i = 0; i < 5; i++) {
                Integer val = x.read();
                Thread.sleep(500);
                x.write(val + 1);
                System.out.println(Thread.currentThread().getName()
                                           + " wrote x = " + (val + 1));
            }
            return x.read();
        };

        Thread t1 = new Thread(() -> {
            STM.execute(incrementFiveTimes);
        });

        Thread t2 = new Thread(() -> {
            STM.execute(incrementFiveTimes);
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        long finalValue = STM.execute(() -> x.read());
        assertEquals(10, finalValue);

    }
}