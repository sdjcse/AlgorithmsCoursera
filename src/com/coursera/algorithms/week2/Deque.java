package com.coursera.algorithms.week2;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by sdj on 1/4/17.
 */
public class Deque<Item> implements Iterable<Item>
{
    private int current,end,start;
    Item[] holder = null;
    public Deque()                           // construct an empty deque
    {
        start = 0;
        end = 0;
        holder = (Item [])new Object[1];
    }

    // Method to increase size
    private void increaseSize()
    {
        int size = holder.length;
        Item[] newHolder = (Item [])new Object[size*2];
        for(int i=0;i<size;i++){
            newHolder[i] = holder[i];
        }
        holder = newHolder;
    }

    // Method to reduce size
    private void reduceSize()
    {
        if(end-start > (holder.length/4))
        {
            return;
        }
        Item[] newHolder = (Item [])new Object[holder.length/2];
        for(int i=0;i<end-start;i++)
        {
            newHolder[i] = holder[i];
        }
        holder = newHolder;
    }
    public boolean isEmpty()                 // is the deque empty?
    {
        return end-start==0;
    }
    public int size()                        // return the number of items on the deque
    {
        return end-start;
    }
    public void addFirst(Item item)          // add the item to the front
    {
        if(item == null)
        {
            throw new NullPointerException();
        }
        if(this.isEmpty())
        {
            start = 0;
            end = 1;
            holder[start] = item;
        }
        else
        {

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
        return generalizedRemove(start,true);
    }
    public Item removeLast()                 // remove and return the item from the end
    {
        return generalizedRemove(start,false);
    }

    private Item generalizedRemove(int index,boolean isStart)
    {
        if(this.isEmpty())
        {
            throw new NoSuchElementException();
        }
        Item toBeReturned = holder[index];
        holder[index] = null;
        start = isStart ? ++start : start;
        end = !(isStart) ? --end : end;
        return  toBeReturned;
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
