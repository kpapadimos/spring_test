package com.springapp.mvc.singletons;

/**
 * Created by papadimos on 14/11/2014.
 *
 * The easier way to create a thread-safe singleton class is to make the global access method synchronized,
 * so that only one thread can execute this method at a time.
 *
 * Above implementation works fine and provides thread-safety but it reduces the performance
 * because of cost associated with the synchronized method,
 * although we need it only for the first few threads who might create the separate instances.
 * To avoid this extra overhead every time, double checked locking principle is used (see ThreadSafeUsingDoubleLockingSingleton.java).
 *
 * In this approach, the synchronized block is used inside the if condition with an additional check
 * to ensure that only one instance of singleton class is created.
 */
public class ThreadSafeSingleton {

    private static ThreadSafeSingleton instance;

    private ThreadSafeSingleton () {}

    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }
}
