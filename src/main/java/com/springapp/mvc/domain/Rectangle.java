package com.springapp.mvc.domain;

/**
 * Created by papadimos on 2/12/2014.
 */
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
