package examples;

import java.util.ArrayList;

/**
 * This is a simple implementation of a Hash SimpleSet with separate chaining and no
 * rehashing.
 *
 * @param <T> type of the objects in the set.
 */
public class CoarseHashSet<T> implements SimpleSet<T> {
    /**
     * Linked list node.
     */
    private interface Bucket<T> {

    }
    private static class Node<T> implements Bucket<T> {
        T item;
        Bucket<T> next;

        public Node(T item, Bucket<T> next) {
            this.item = item;
            this.next = next;
        }

    }
    private static class Null<T> implements Bucket<T> {

    }

    /**
     * Our array of items. Each location in the array stores a linked list items
     * that hash to that locations.
     */
    private ArrayList<Bucket<T>> table;

    /**
     * Capacity of the array. Since we do not support resizing, this is a
     * constant.
     */
    private static final int CAPACITY = 1024;

    /**
     * Create a new SimpleHashSet.
     */
    public CoarseHashSet() {
        this.table = new ArrayList<>();
        for (int i=0; i < CAPACITY; i++) {
            table.add(new Null<T>());
        }
    }

    /**
     * A helper method to see if an item is stored at a given bucket.
     *
     * @param bucket bucket to be searched
     * @param item item to be searched for
     * @return true if the item is in the bucket
     */
    private boolean contains(Bucket<T> bucket, T item) {
        while (!(bucket instanceof Null)) {
            Node<T> node = (Node<T>)bucket;
            if (item.equals(node.item)) {
                return true;
            }
            bucket = node.next;
        }
        return false;
    }

    public boolean add(T item) {
        // Java might return a negative number for hashCode, so be careful
        int hash = Math.floorMod(item.hashCode(), CAPACITY);
        Bucket<T> bucket = table.get(hash);
        if (contains(bucket, item)) {
            return false;
        }
        table.set(hash, new Node<>(item, bucket));
        return true;
    }

    public boolean contains(T item) {
        int hash = Math.floorMod(item.hashCode(), CAPACITY);
        Bucket<T> bucket = table.get(hash);
        return contains(bucket, item);
    }
}
