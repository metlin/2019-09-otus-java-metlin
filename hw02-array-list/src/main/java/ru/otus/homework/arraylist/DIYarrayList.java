package ru.otus.homework.arraylist;

import java.util.*;
import java.util.function.UnaryOperator;

public class DIYarrayList<E> implements List<E> {

    private E[] array = (E[]) new Object[0];

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length == 0;
    }

    @Override
    public boolean contains(Object object) {
        for (E e : array) {
            if (e.equals(object)) {
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
        E[] newArray = (E[]) new Object[oldArray.length + 1];

        for (int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }

        array = newArray;
    }

    private void reduceArray(E oldArray[]) {
        E[] newArray = (E[]) new Object[oldArray.length - 1];

        int y = 0;
        for (E e : oldArray) {
            if (e != null) {
                newArray[y] = e;
                y++;
            }
        }

        array = newArray;
    }

    private void checkIndex(int index) {
        if (index > size()) {
            throw new ArrayIndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
    }

    @Override
    public boolean add(E e) {
        expandArray(array);

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = e;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean remove(Object object) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(object)) {
                array[i] = null;
                reduceArray(array);
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
        checkIndex(index);
        expandArray(array);

        for (int i = 0; i < array.length; i++) {
            if (i == index) {
                array[i] = element;
                System.out.println("Объект " + element + " успешно добавлен");
            }
        }
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        return array[index] = null;
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
        return Arrays.toString(array);

    }
}