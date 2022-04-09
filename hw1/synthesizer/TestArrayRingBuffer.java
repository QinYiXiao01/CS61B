package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        //ArrayRingBuffer arb = new ArrayRingBuffer(10);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }

    @Test
    public void testArray() {
        ArrayRingBuffer<Integer> testBuffer = new ArrayRingBuffer<>(7);
        testBuffer.enqueue(1);
        testBuffer.enqueue(2);
        testBuffer.enqueue(3);
        testBuffer.dequeue();
        testBuffer.dequeue();
        testBuffer.enqueue(4);
        testBuffer.enqueue(5);
        testBuffer.enqueue(6);
        testBuffer.enqueue(7);
        testBuffer.enqueue(8);
        testBuffer.enqueue(9);

        Object[] expected = {8, 9, 3, 4, 5, 6, 7};
        assertArrayEquals(expected, testBuffer.getRb());
    }
} 
