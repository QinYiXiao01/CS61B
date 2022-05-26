import edu.princeton.cs.algs4.Queue;
import org.junit.Test;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     * MergeSort的第一步，将一个队列中的单个元素转换成一个个小队列，并将其汇总成为一个"队列的队列"
     */
    private static <Item extends Comparable> Queue<Queue<Item>>
    makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> totalQueue = new Queue<>();
        for (Item item : items) {
            Queue<Item> singleQueue = new Queue();
            singleQueue.enqueue(item);
            totalQueue.enqueue(singleQueue);
        }
        return totalQueue;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * MergeSort的第二步，将两个对半的排序好的子队列合成一个
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> finalQueue = new Queue<>();
        for (int p = 0; p < q1.size() + q2.size(); p++) {
            if (q1.isEmpty()) {
                finalQueue.enqueue(q2.dequeue());
            } else if (q2.isEmpty()) {
                finalQueue.enqueue(q1.dequeue());
            } else {
                if (q1.peek().compareTo(q2.peek()) <= 0) {
                    finalQueue.enqueue(q1.dequeue());
                    finalQueue.enqueue(q2.dequeue());
                } else {
                    finalQueue.enqueue(q2.dequeue());
                    finalQueue.enqueue(q1.dequeue());
                }
            }
        }
        return finalQueue;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> qq = makeSingleItemQueues(items); // qq每个成员是单个小队列
        while (qq.size() != 1) {
            Queue<Queue<Item>> qqTemp = new Queue<>();
            while (!qq.isEmpty()) {
                Queue<Item> q1 = qq.dequeue();
                Queue<Item> q2 = qq.isEmpty() ? new Queue<>() : qq.dequeue();
                qqTemp.enqueue(mergeSortedQueues(q1, q2)); // 将qq中的成员两两捉对存到qqTemp中
            }
            qq = qqTemp; // 此时qqTemp的size变成一半,它变为新的qq
        }
        return qq.dequeue();
}

    public static void main(String[] args) {
        Queue<Integer> nums = new Queue<>();
        nums.enqueue(2);
        nums.enqueue(0);
        nums.enqueue(0);
        nums.enqueue(1);
        nums.enqueue(4);
        nums.enqueue(8);

        System.out.println("Unsorted queue as followed:");
        System.out.println(nums);
        Queue<Integer> sortedQueue = mergeSort(nums);
        System.out.println("Sorted queue as followed:");
        System.out.println(sortedQueue);
    }
}
