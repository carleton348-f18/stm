import examples.CoarseHashSet;
import examples.FineHashSet;
import examples.STMHashSet;
import examples.SimpleHashSet;
import examples.SimpleSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class HashSetTest {

    private long startTime;

    @Before
    public void setUp() {
        startTime = System.currentTimeMillis();
    }

    @After
    public void tearDown() {
        System.out.println("Running time = " + (System.currentTimeMillis()-startTime));
    }

    @Test
    public void warmup() {
        SimpleHashSet<Integer> set = new SimpleHashSet<Integer>();
        set.add(1);
        set.add(2);
        set.add(3);
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
    }

    public static void threadTrouble(SimpleSet<Integer> set, int size) throws
            Exception {

        class Task extends Thread {
            private int taskNumber;
            private int size;
            private SimpleSet<Integer> set;

            public Task(int taskNumber, int size, SimpleSet<Integer> set) {
                this.taskNumber = taskNumber;
                this.size = size;
                this.set = set;
            }

            @Override
            public void run() {
                for (int i = 0; i < size; i++) {
                    set.add(taskNumber * size + i);
                }
            }
        }
        long startTime = System.currentTimeMillis();
        int taskCount = 4;
        Task[] tasks = new Task[taskCount];
        for (int i = 0; i < taskCount; i++) {
            tasks[i] = new Task(i, size, set);
        }
        for (int i = 0; i < taskCount; i++) {
            tasks[i].start();
        }
        for (int i = 0; i < taskCount; i++) {
            tasks[i].join();
            System.out.println("Thread " + i + "done");
        }
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println("Time to insert = " + endTime);
        for (int i = 0; i < size * taskCount; i++) {
            assertTrue("Does not contain " + i, set.contains(i));
        }
    }

    // This test is supposed to fail; it's unsynchronized
    @Test(expected= AssertionError.class)
    public void testHashSet() throws Exception {
        SimpleSet<Integer> set = new SimpleHashSet<>();
        threadTrouble(set, 100_000);
    }

    @Test
    public void testSTMHashSet() throws Exception {
        SimpleSet<Integer> set = new STMHashSet<>();
        threadTrouble(set, 100_000);
    }

    @Test
    public void testCoarseHashSet() throws Exception {
        SimpleSet<Integer> set = new CoarseHashSet<>();
        threadTrouble(set, 100_000);
    }

    @Test
    public void testFineHashSet() throws Exception {
        SimpleSet<Integer> set = new FineHashSet<>();
        threadTrouble(set, 100_000);
    }
}