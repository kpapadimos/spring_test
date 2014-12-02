package com.springapp.mvc.domain;

/**
 * Created by papadimos on 2/12/2014.
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
