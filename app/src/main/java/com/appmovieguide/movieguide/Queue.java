package com.appmovieguide.movieguide;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Queue implements IQueue
{

    private java.util.Queue contents;

    public Queue(boolean isPriority)
    {
        if(isPriority)
            contents = new PriorityQueue();
        else
            contents = new LinkedList();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public Queue(Comparator comp)
    {
        for (Object o : contents = new PriorityQueue(comp)) {

        }
        ;
    }

    public Queue()
    {
        contents = new LinkedList();
    }

    @Override
    public void enqueue(Object item)
    {
        contents.add(item);

    }

    @Override
    public Object peek() throws QueueEmpty
    {
        if (isEmpty())
            throw new QueueEmpty();

        return contents.peek();
    }

    @Override
    public Object dequeue() throws QueueEmpty {

        if (isEmpty())
            throw new QueueEmpty();

        return contents.poll();

    }

    @Override
    public boolean isEmpty() {
        // TODO Auto-generated method stub
        return contents.size() == 0;
    }

    @Override
    public String toString()
    {
        int size = contents.size();

        String retString = null;


        if(contents.getClass().getName().equals("java.util.LinkedList"))
        {
            Object[] tempArr = contents.toArray();

            for(int i=0; i<size; i++)
            {
                retString += tempArr[i] + "\n";
            }

        }
        else
        {
            PriorityQueue temp_pq= new PriorityQueue(contents);
            while(!temp_pq.isEmpty())
            {
                retString += temp_pq.poll() + "\n\n";

            }

        }



        return retString;
    }

    public int size(){
        return contents.size();

    }


}
