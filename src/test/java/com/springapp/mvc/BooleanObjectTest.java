package com.springapp.mvc;

import org.junit.Test;

/**
 * Created by papadimos on 14/11/2014.
 */
public class BooleanObjectTest {

    @Test
    public void booleanObject() throws Exception {
        Boolean b1 = new Boolean("TRUE");
        Boolean b2 = new Boolean("true");
        Boolean b3 = new Boolean("tRuE");
        Boolean b4 = new Boolean("false");

        System.out.println(b1 == b2);
        System.out.println(b1.equals(b2));
    }
}
