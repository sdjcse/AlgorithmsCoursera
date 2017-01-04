package com.coursera.algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sdj on 1/4/17.
 */
public class Deque<Item> implements Iterable<Item>
{
    private int current,end;
    public Deque()                           // construct an empty deque
    {
        Item[] holder = (Item [])new Object[20];
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return false;
    }
    public int size()                        // return the number of items on the deque
    {
        return 0;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if(item == null)
        {
            throw new NullPointerException();
        }

    }
    public void addLast(Item item)           // add the item to the end
    {
        if(item == null)
        {
            throw new NullPointerException();
        }

    }
    public Item removeFirst()                // remove and return the item from the front
    {
        if(this.isEmpty())
        {
            throw new NoSuchElementException();
        }
        return (Item)new Object();
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        if(this.isEmpty())
        {
            throw new NoSuchElementException();
        }
        return (Item)new Object();
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    {
        return (Iterator<Item>) new Object();
    }
    public void remove()
    {
        throw new UnsupportedOperationException();
    }
    public static void main(String[] args)   // unit testing
    {

    }
}
