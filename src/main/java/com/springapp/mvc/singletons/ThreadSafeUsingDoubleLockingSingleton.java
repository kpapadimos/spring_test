package com.springapp.mvc.singletons;

/**
 * Created by papadimos on 14/11/2014.
 *
 * In this approach, the synchronized block is used inside the if condition with an additional check
 * to ensure that only one instance of singleton class is created.
 */
public class ThreadSafeUsingDoubleLockingSingleton {

    private static ThreadSafeUsingDoubleLockingSingleton instance;

    private  ThreadSafeUsingDoubleLockingSingleton() {}

    public static ThreadSafeUsingDoubleLockingSingleton getInstance() {
        if(instance == null) {
            synchronized (ThreadSafeUsingDoubleLockingSingleton.class) {
                if(instance == null) {
                    instance = new ThreadSafeUsingDoubleLockingSingleton();
                }
            }
        }
        return instance;
    }
}
