package com.appmovieguide.movieguide;
public class QueueEmpty extends Exception
{

    public QueueEmpty()
    {
        super();
        System.err.println("Queue is empty");
    }
}
