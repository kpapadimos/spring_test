package com.springapp.mvc.singletons;

/**
 * Created by papadimos on 14/11/2014.
 *
 * In eager initialization, the instance of Singleton Class is created at the time of class loading,
 * this is the easiest method to create a singleton class but it has a drawback that
 * instance is created even though client application might not be using it.
 *
 * If your singleton class is not using a lot of resources, this is the approach to use.
 * But in most of the scenarios, Singleton classes are created for resources such as File System, Database connections etc
 * and we should avoid the instantiation until unless client calls the getInstance method.
 * Also this method doesn’t provide any options for exception handling.
 *
 * Both eager initialization and static block initialization creates the instance even before
 * it’s being used and that is not the best practice to use.
 */
public class EagerInitializedSingleton {

    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    //private constructor to avoid client applications to use constructor
    private EagerInitializedSingleton(){}

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}
