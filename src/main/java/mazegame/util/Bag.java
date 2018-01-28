
package mazegame.util;

import mazegame.core.IBag;

import java.util.NoSuchElementException;
import java.util.Random;

public class Bag<T> implements IBag<T> {

    // Same default capacity as java.util.ArrayList
    private static final int DEFAULT_CAPACITY = 16;

    private T[] array;
    private int size;
    private Random random;

    public Bag(int capacity) {
        size = 0;
        // We whould like to initialize our array like this:
        //    array = new T[DEFAULT_CAPACITY];
        // but Java does not allow creating "generic" arrays.
        // The following 3 lines is a workaround:
        @SuppressWarnings("unchecked")
        T[] workaround = (T[]) new Object[capacity];
        this.array = workaround;
        this.random = new Random();
    }

    public Bag() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // creates a new array with the desired capacity
    // and copies the data from the old one to the new one
    private void ensureCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        if (capacity <= capacity()) {
            return;
        }
        @SuppressWarnings("unchecked")
        T[] workaround = (T[]) new Object[capacity];
        // there are better ways to copy big arrays, but they are
        // not part of this course
        for (int i = 0; i < size; i++) {
            workaround[i] = array[i];
        }
        this.array = workaround;
    }

    // doubles the array length
    private void grow() {
        ensureCapacity(2 * capacity());
    }

    @Override
    public boolean add(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        if (size == array.length) {
            grow();
        }
        array[size++] = t;
        return true;
    }

    private int capacity() {
        return array.length;
    }

    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int pos = random.nextInt(size);
        T retval = array[pos];
        // shift left
        for (int i = pos; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size - 1] = null; // helps the garbage collector
        size--;
        return retval;
    }

    @Override
    public void clear() {
        // Creating a new empty array will help the garbage collector
        // a lot.
        @SuppressWarnings("unchecked")
        T[] workaround = (T[]) new Object[DEFAULT_CAPACITY];
        array = workaround;
        size = 0;
    }

    @Override
    public int getCurrentSize() {
        return size;
    }

    @Override
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        for (int i = 0; i < getCurrentSize(); i++) {
            if (array[i].equals(anEntry)) {
                frequency++;
            }
        }
        return frequency;
    }

    @Override
    public boolean contains(T anEntry) {
        return false;
    }

    @Override
    public T[] toArray() {
        T[] newElements = (T[]) new Object[getCurrentSize()];
        for (int i = 0; i < getCurrentSize(); i++) {
            newElements[i] = array[i];
        }
        return newElements;
    }

    @Override
    public boolean remove(T anEntry) {
        return false;
    }
}
