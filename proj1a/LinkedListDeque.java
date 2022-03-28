@SuppressWarnings("all")
public class LinkedListDeque<T> {
    private Node sentinel;
    private int size;
    private class Node {
        public T item;
        public Node next;
        public Node prev;

        public Node(T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    // 创建空链表
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    // 创建其他链表
    public LinkedListDeque(LinkedListDeque<T> other) {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        this.size = other.size;

        Node pointer1 = sentinel.next;
        Node pointer2 = other.sentinel.next;
        while (pointer2 != other.sentinel) {
            pointer1 = new Node(pointer2.item, pointer2.next, pointer2.prev);
            pointer1 = pointer1.next;
        }
    }

    /**
     * 添加头节点
     */
    public void addFirst(T item) {
        // 新结点的next->原来第一， 新结点的prev->sentinel
        Node current = new Node(item, sentinel.next, sentinel);
        // 原来第一的next->新结点
        sentinel.next = current;
        // 原来第一的prev->新节点
        current.next.prev = current;
        size++;
    }

    /**
     * 删除头结点
     */
    public void removeFirst() {
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
    }

    /**
     * 添加尾结点
     */
    public void addLast(T item) {
        // 新结点next->sentinel prev->最后一个元素
        Node current = new Node(item, sentinel, sentinel.prev);
        // 最后一个元素的next -> 新结点
        sentinel.prev.next = current;
        // sentinel的前一个 -> 新结点
        sentinel.prev = current;
        size++;
    }

    /**
     * 删除尾结点
     */
    public void removeLast() {
        // sentinel的前一个 -> 倒数第二个元素
        sentinel.prev = sentinel.prev.prev;
        // 倒数第二个的next -> sentinel
        sentinel.prev.next = sentinel;
        size--;
    }

    /**
     * 得到某index的元素
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node pointer = sentinel.next;
        for (int j = 0; j <= index ; j++) {
            pointer = pointer.next;
        }
        return pointer.item;
    }

    /**
     * 同get方法，但是用递归（这个我不会）
     */
    public T getRecursiveImpl(Node start, int index) {
        if (index == 0) {
            return start.item;
        }
        else {
            return getRecursiveImpl(start.next, index - 1);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveImpl(sentinel, index);
    }

    /**
     * 返回size
     */
    public int size() {
        return size;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 打印,以空格分开
     */
    public void printDeque() {
        Node pointer = sentinel.next;
        while (pointer != sentinel) {
            pointer = pointer.next;
            System.out.print(pointer.item + " ");
        }
    }
}


