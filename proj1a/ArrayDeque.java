@SuppressWarnings("ALL")
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    public static final double RFACTOR = 2;

    // 创建初始空的数组队列
    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 3;
    }

    // 拷贝其他数组队列
    public void ArrayDeque(ArrayDeque other) {
        System.arraycopy(other, 0, items, 0, size);
    }

    /**
     * 为指标加一
     * @param index
     * @return 新的指标
     */
    public int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    /**
     * 为指标减一
     * @param index
     * @return 新的指标
     */
    public int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    /**
     * 扩容，扩完之后 {e, e, e} -> {e, e, e, null, null, null}
     */
    public void grow() {
        int formerLength = items.length;
        T[] a = (T[])new Object[(int) (formerLength * RFACTOR)]; // 建立新数组
        int pointer = nextFirst;
        while (true) {
            pointer = plusOne(pointer);
            a[pointer] = items[pointer];
            if (pointer == nextFirst) {
                break;
            }
        }
        items = a;
        nextFirst = items.length - 1; // 前指针来到最后
        nextLast = formerLength; // 后指针来到后一个
    }

    /**
     * 缩容, 缩完之后，{e, e, e, null, null, ..., e, e} -> {e, e, e, e, null, null}
     */
    public void shrink() {
        // 扩容之后必定前指针在最最后，前指针在物品最后
        int formerLength = items.length;
        T[] b = (T[])new Object[(int) (formerLength / RFACTOR)];
        int pointer1 = nextFirst;
        int pointer2 = 0;
        while (true) {
            b[pointer2] = items[pointer1];
            pointer2 = plusOne(pointer2);
            pointer1 = plusOne(pointer1);
            if (pointer1 == nextFirst) {
                break;
            }
        }
        items = b;
        nextFirst = items.length - 1; // 前指针来到最后
        nextLast = size; // 后指针来到后一个
    }

    /**
     * 加在数组队列前面
     * @param item
     */
    public void addFirst(T item) {
        if (size == items.length) { // 若物品数量和数组大小相等，就扩容
            grow();
        }
        items[nextFirst] = item;
        size++;
        nextFirst = minusOne(nextFirst);
    }

    /**
     * 加在数组队列后面
     * @param item
     */
    public void addLast(T item) {
        if (size == items.length) { // 若物品数量和数组大小相等，就扩容
            grow();
        }
        items[nextLast] = item;
        size++;
        nextLast = plusOne(nextFirst);

    }

    /**
     * 删除数组队列头元素
     * @return
     */
    public T removeFirst() {
        T target = items[nextFirst];
        items[nextFirst] = null;
        size--;
        plusOne(nextFirst);
        return target;
    }

    /**
     * 删除数组队列尾元素
     * @return
     */
    public T removeLast() {
        T target = items[nextLast];
        items[nextLast] = null;
        size--;
        minusOne(nextLast);
        return target;
    }
}

//class Test {
//    public static void main(String[] args) {
//        ArrayDeque<Integer> deque = new ArrayDeque();
//        deque.addFirst(1);
//        deque.addFirst(2);
//        deque.addFirst(3);
//        deque.addFirst(4);
//        deque.addFirst(5);
//        deque.addFirst(6);
//        deque.addFirst(7);
//        deque.addLast(8);
//        deque.addLast(9);
//        deque.addFirst(10);
//        deque.addFirst(11);
//
//    }
//}


