// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer ;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        // TODO: Create a buffer with capacity = SR / frequency. You'll need to
        //       cast the result of this divsion operation into an int. For better
        //       accuracy, use the Math.round() function before casting.
        //       Your buffer should be initially filled with zeros.
        int capacity = (int)Math.round(SR / frequency);
        buffer = new ArrayRingBuffer<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buffer.enqueue(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        // TODO: Dequeue everything in the buffer, and replace it with random numbers
        //       between -0.5 and 0.5. You can get such a number by using:
        //       double r = Math.random() - 0.5;
        //
        //       Make sure that your random numbers are different from each other.
        while(!buffer.isEmpty()) {
            buffer.dequeue();
        }
        while(!buffer.isFull()) {
            double randNum = getRandNum((ArrayRingBuffer<Double>) buffer);
            buffer.enqueue(randNum);
        }
    }

    /**
     * 得到随机的值（并与队列里其他均不相等）
     * @return
     */
    public double getRandNum(ArrayRingBuffer<Double> buffer) {
        double randNum;
        int first = buffer.getFirst(); // 他是原来的first
        while(true) {
            buffer.setFirst(first);
            randNum = Math.random() - 0.5; // 先生成一个随机数
            if (isUnique(buffer, randNum)) {
                break;
            }
            continue;
        }
        return randNum;
    }

    public boolean isUnique(ArrayRingBuffer<Double> buffer, double num) {
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.setFirst(buffer.move(buffer.getFirst())); // first往后走
            if  (buffer.peek() == num) {
                return false; // 若碰到一样的了就退出遍历
            }
        }
        return true;
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        // TODO: Dequeue the front sample and enqueue a new sample that is
        //       the average of the two multiplied by the DECAY factor.
        //       Do not call StdAudio.play().
        double dqItem = buffer.dequeue();
        buffer.enqueue(DECAY * (dqItem + buffer.peek()) * 0.5);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.peek();
    }
}
