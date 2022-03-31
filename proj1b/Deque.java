public interface Deque<T> {
    void addFirst(T item);

    T removeFirst();

    void addLast(T item);

    T removeLast();

    T get(int index);

    int size();

    boolean isEmpty();
}
