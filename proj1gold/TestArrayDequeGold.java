import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    /**
     * 随机调用这两种方法，直到出现分歧
     */
    @Test
    public void autoChecker() {
        StudentArrayDeque<Integer> testDeque = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> realDeque = new ArrayDequeSolution<>();
        String message = "";
        for (int i = 0; i < 100; i++) { // 连续判断测试100次
            int testNumRemoved = 0;
            int realNumRemoved = 0;
            // 如果队列是空，则先添加元素
            if (testDeque.size() == 0) {
                // 添加的元素在0-100之间
                int numAdded = StdRandom.uniform(100);
                double firstOrLast = StdRandom.uniform();
                if (firstOrLast < 0.5) {
                    message += "addFirst(" + numAdded + ")\n";
                    testDeque.addFirst(numAdded);
                    realDeque.addFirst(numAdded);
                }
                else {
                    message += "addLast(" + numAdded + ")\n";
                    testDeque.addLast(numAdded);
                    realDeque.addLast(numAdded);
                }
            }
            else {
                // 队列不为空，判断增或删 / 前或后
                int numAdded = StdRandom.uniform(100);
                double addOrRemove = StdRandom.uniform();
                double firstOrLast = StdRandom.uniform();
                if (addOrRemove < 0.5 && firstOrLast < 0.5) {
                    message += "addFirst(" + numAdded + ")\n";
                    testDeque.addFirst(numAdded);
                    realDeque.addFirst(numAdded);
                }
                else if (addOrRemove < 0.5 && firstOrLast >= 0.5){
                    message += "addLast(" + numAdded + ")\n";
                    testDeque.addLast(numAdded);
                    realDeque.addLast(numAdded);
                }
                else if (addOrRemove >= 0.5 && firstOrLast < 0.5){
                    testNumRemoved = testDeque.removeFirst();
                    realNumRemoved = realDeque.removeFirst();
                    message += "removeFirst()\n";
                }
                else {
                    testNumRemoved = testDeque.removeLast();
                    realNumRemoved = realDeque.removeLast();
                    message += "removeLast()\n";
                }
            }
            // 只需要判断两个队列删除的元素是否相同
            assertEquals(message, testNumRemoved, realNumRemoved);
        }
    }
}
