package com.springapp.mvc;

import org.junit.Test;

/**
 * Created by papadimos on 14/11/2014.
 */
public class ThreadTest {

    @Test
    public void thread() throws Exception {
        final StringBuffer a = new StringBuffer();
        final StringBuffer b = new StringBuffer();

        new Thread()
        {
            public void run()
            {
                System.out.print(a.append("A"));
                synchronized(b)
                {
                    System.out.print(b.append("B"));
                }
            }
        }.start();

        new Thread()
        {
            public void run()
            {
                System.out.print(b.append("C"));
                synchronized(a)
                {
                    System.out.print(a.append("D"));
                }
            }
        }.start();
    }
}
