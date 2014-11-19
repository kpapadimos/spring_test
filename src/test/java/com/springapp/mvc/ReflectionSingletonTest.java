package com.springapp.mvc;

import com.springapp.mvc.singletons.EagerInitializedSingleton;
import org.junit.Test;

import java.lang.reflect.Constructor;

/**
 * Created by papadimos on 14/11/2014.
 *
 * Reflection can be used to destroy all the above singleton implementation approaches.
 *
 * When you run the above test class, you will notice that hashCode of both the instances are not same
 * that destroys the singleton pattern.
 * Reflection is very powerful and used in a lot of frameworks like Spring and Hibernate.
 */
public class ReflectionSingletonTest {

    @Test
    public void main() {
        EagerInitializedSingleton instanceOne = EagerInitializedSingleton.getInstance();
        EagerInitializedSingleton instanceTwo = null;
        try {
            Constructor[] constructors = EagerInitializedSingleton.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                //Below code will destroy the singleton pattern
                constructor.setAccessible(true);
                instanceTwo = (EagerInitializedSingleton) constructor.newInstance();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(instanceOne.hashCode());
        System.out.println(instanceTwo.hashCode());
    }
}
