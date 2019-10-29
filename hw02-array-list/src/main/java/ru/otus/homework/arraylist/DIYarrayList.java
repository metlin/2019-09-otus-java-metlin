package ru.otus.homework.arraylist;

import java.util.*;
import java.util.function.UnaryOperator;

public class DIYarrayList<E> implements List<E> {

    private E[] array = (E[]) new Object[10];
    private int arraySize = 0;

    @Override
    public int size() {
        return arraySize;
    }

    @Override
    public boolean isEmpty() {
        return arraySize == 0;
    }

    @Override
    public boolean contains(Object object) {
        if (object == null && checkNull()) {
            System.out.println("Объект " + object + " содержится в коллекции");
            return true;
        }

        for (E e : array) {
            if (object != null && object.equals(e)) {
                System.out.println("Объект " + object + " содержится в коллекции");
                return true;
            }
        }

        System.out.println("Объект " + object + " отсутствует в коллекции");
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    private void expandArray(E oldArray[]) {
        E[] newArray = (E[]) new Object[oldArray.length * 2];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        array = newArray;
    }

    private void reduceArray(E oldArray[], int index) {
        E[] newArray = (E[]) new Object[oldArray.length - 1];
        int y = 0;
        for (int i = 0; i < oldArray.length; i++) {
            if (i != index) {
                newArray[y] = oldArray[i];
                y++;
            }
        }

        array = newArray;
    }

    private boolean checkNull() {
        for (int i = 0; i < arraySize; i++) {
            if (array[i] == null) {
                return true;
            }
        }

        return false;
    }

    private int indexNull() {
        for (int i = 0; i < arraySize; i++) {
            if (array[i] == null) {
                return i;
            }
        }

        return -1;
    }

    private void checkIndex(int index) {
        if (index >= size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
    }

    @Override
    public boolean add(E e) {
        if (arraySize >= array.length) {
            expandArray(array);
        }

        for (int i = arraySize; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = e;
                arraySize++;
                return true;
            }
        }

        throw new IllegalArgumentException();
    }

    @Override
    public boolean remove(Object object) {
        if (object == null && checkNull()) {
            reduceArray(array, indexNull());
            arraySize--;
            return true;
        }

        for (int i = 0; i < arraySize; i++) {
            if (object != null && object.equals(array[i])) {
                reduceArray(array, i);
                arraySize--;
                return true;
            }
        }

        System.out.println("Объеет " + object + " не найден");
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void replaceAll(UnaryOperator<E> operator) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(Comparator<? super E> c) {
        E[] newArray = (E[]) new Object[arraySize];
        System.arraycopy(array, 0, newArray, 0, arraySize);
        array = newArray;

        Arrays.sort(array, c);
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("clear()");
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public E set(int index, E element) {
        checkIndex(index);
        return array[index] = element;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        remove(get(index));
        return array[index];
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Spliterator<E> spliterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIteratorImpl();
    }

    private class ListIteratorImpl implements ListIterator<E> {

        private int index = -1;

        @Override
        public boolean hasNext() {
            index++;
            return index != size();
        }

        @Override
        public E next() {
            if (hasNext()) {
                return array[index];
            }

            return null;
        }

        @Override
        public boolean hasPrevious() {
            throw new UnsupportedOperationException();
        }

        @Override
        public E previous() {
            throw new UnsupportedOperationException();
        }

        @Override
        public int nextIndex() {
            return index + 1;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(E o) {
            array[index] = o;
        }

        @Override
        public void add(E o) {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String toString() {
        String string = "[";
        for (int i = 0; i < arraySize; i++) {
            string = string + array[i] + ", ";
        }

        return string + "]";
    }
}