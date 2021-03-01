package com.appmovieguide.movieguide;
public class QueueFull extends Exception
{

    public QueueFull()
    {
        super();
        System.err.println("Queue is full");
    }

}
