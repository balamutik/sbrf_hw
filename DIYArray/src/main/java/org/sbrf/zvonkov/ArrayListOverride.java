package org.sbrf.zvonkov;

import java.util.*;

public class ArrayListOverride<E> implements List<E> {
    private final int INIT_SIZE = 32;
    private Object[] mainArray;
    private int curPos = 0;

    public ArrayListOverride(){
        mainArray = new Object[INIT_SIZE];
    }
    public ArrayListOverride(int size){
        mainArray = new Object[size];
    }

    private void createNewArray(int size){
        Object[] newArray = new Object[size];
        System.arraycopy(mainArray, 0, newArray, 0, curPos);
        mainArray = newArray;
    }

    @Override
    public int size() {
        return mainArray.length;
    }

    @Override
    public boolean isEmpty() {
        if(curPos>0)
            return true;
        else
            return false;
    }

    @Override
    public boolean contains(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        return (Object[]) mainArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Object[] newArray = c.toArray();
        int newSize = newArray.length;
        createNewArray(newSize+curPos);
        System.arraycopy(newArray,0, mainArray, curPos, newSize);
        curPos += newSize;
        return true;
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
    public void clear() {
        curPos = 0;
    }

    @Override
    public E get(int index) {
        if(index>mainArray.length-1)
            return null;
        return (E) mainArray[index];    }

    @Override
    public E set(int index, E element) {
        if(index>mainArray.length-1)
            return null;
        mainArray[index] = element;
        return (E) mainArray[index];
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E element) {
        if(mainArray.length-1 == curPos )
            createNewArray(curPos * 2);
        mainArray[curPos++] = element;
        return true;
    }

    @Override
    public E remove(int index) {
        for (int i = index; i<curPos; i++)
            mainArray[i] = mainArray[i+1];
        mainArray[curPos] = null;
        curPos--;
        return null;
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
    public ListIterator<E> listIterator() {
        return new Iter(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
    private class Iter implements ListIterator<E>{
        int cursor = 0;
        int lastRet = -1;

        Iter(int index){
            cursor = index;
        }

        @Override
        public boolean hasNext() {
            return cursor != curPos;
        }

        @Override
        public E next() {
            System.out.println("cursor: "+cursor);
            System.out.println("curPos: "+curPos);
            int i = cursor;
            if (i == mainArray.length-1)
                return null;
            Object[] elementData = ArrayListOverride.this.mainArray;
            cursor++;
            return (E) elementData[cursor];
        }

        @Override
        public boolean hasPrevious() {
            return cursor!=0;
        }

        @Override
        public E previous() {
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementData = ArrayListOverride.this.mainArray;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i;
            return (E) elementData[lastRet = i];
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor-1;
        }

        @Override
        public void remove() {

        }

        @Override
        public void set(E e) {

        }

        @Override
        public void add(E e) {

        }
    }
}
