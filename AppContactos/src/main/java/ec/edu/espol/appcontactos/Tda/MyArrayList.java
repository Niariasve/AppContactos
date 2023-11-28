/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.appcontactos.Tda;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 *
 * @author User
 */
public class MyArrayList<E> implements List<E> {
    private E[] elementos;
    private int size;
    private int capacidad = 50;
    
    public MyArrayList() {
        elementos = (E[]) new Object[capacidad];
        this.size = 0;
    }
    
    public MyArrayList(int capacidad) {
        elementos = (E[]) new Object[capacidad];
        this.size = 0;
    }
    
    private class Iterador implements Iterator<E> {
        private int itr = 0;

        @Override
        public boolean hasNext() {
            return itr < size;
        }

        @Override
        public E next() {
            if (!hasNext())
                throw new NoSuchElementException();           
            E elem = elementos[itr];
            itr++;
            return elem;
        }
        
    }
 
    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        for (E e : this) {
            if (e.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterador();
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    private void addCapacity() {
        E[] nuevoArray = (E[]) new Object[capacidad*2];
        for (int i = 0; i < size; i++) {
            nuevoArray[i] = elementos[i];
        }
        this.elementos = nuevoArray;
        this.capacidad = capacidad*2;
    }

    @Override
    public boolean add(E e) {
        if (size == capacidad)
            addCapacity();
        elementos[size] = e;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (this.get(i).equals(o)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void clear() {
        //los elementos quedan como basura
        size = 0;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size) 
            throw new IndexOutOfBoundsException();
        
        return elementos[index];
    }

    @Override
    public E set(int index, E element) {
        if (index < 0 || index > size) 
            throw new IndexOutOfBoundsException();
        
        E prev = elementos[index];
        elementos[index] = element;
        return prev;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) 
            throw new IndexOutOfBoundsException();
        if (size == capacidad)
            addCapacity();
        for (int i = size; i > index; i--) {
            elementos[i] = elementos[i-1];
        }
        size++;
        elementos[index] = element;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index > size) 
            throw new IndexOutOfBoundsException();
        if (size == capacidad)
            addCapacity();
        E elem = elementos[index];
        for (int i = index; i < size; i++) {
            elementos[i] = elementos[i+1];
        }
        size--;
        return elem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
           if (this.get(i).equals(o))
               return i;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (this.get(i).equals(o))
                index = i;
        }
        return index;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void sort(Comparator<? super E> comparador) {
        Arrays.sort(elementos, 0, size, comparador);
    }
}
