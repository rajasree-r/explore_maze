package mazegame.util;

import java.util.NoSuchElementException;

public interface Queue<E> {
    boolean isEmpty();
    void    enqueue(E e) throws NullPointerException;
    E       dequeue() throws NoSuchElementException;
}
