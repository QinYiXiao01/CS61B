// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;
    private int capacity;
    private int fillCount;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.first = 0;
        this.last = 0;
        this.capacity = capacity;
        this.fillCount = 0;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        if (rb[last] != null) {
            last = move(last);
        }
        rb[last] = x;
        last = move(last);  // 加到队列后面
        fillCount++;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        T firstItem;
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        if (rb[first] == null) {
            first = move(first);
        }
        firstItem = rb[first];
        rb[first] = null;
        first = move(first);
        fillCount--;

        return firstItem;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        return rb[first];
    }

    /**
     * 在增或删时指针进行移动
     */
    public int move(int pointer) {
        if (pointer == this.capacity - 1) {
            pointer = 0;
        } else {
            pointer++;
        }
        return pointer;
    }

    @Override
    public boolean isEmpty() {
        return this.capacity == 0;
    }

    @Override
    public boolean isFull() {
        return this.capacity == this.fillCount;
    }

    public Object[] getRb() {
        return rb;
    }

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
