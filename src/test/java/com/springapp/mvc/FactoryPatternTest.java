package com.springapp.mvc;

import com.springapp.mvc.domain.Shape;
import com.springapp.mvc.domain.ShapeFactory;
import org.junit.Test;

/**
 * Created by papadimos on 2/12/2014.
 */
public class FactoryPatternTest {

    @Test
    public void factoryPattern() {
        ShapeFactory shapeFactory = new ShapeFactory();

        //get an object of Circle and call its draw method.
        Shape shape1 = shapeFactory.getShape("CIRCLE");

        //call draw method of Circle
        shape1.draw();

        //get an object of Rectangle and call its draw method.
        Shape shape2 = shapeFactory.getShape("RECTANGLE");

        //call draw method of Rectangle
        shape2.draw();

        //get an object of Square and call its draw method.
        Shape shape3 = shapeFactory.getShape("SQUARE");

        //call draw method of circle
        shape3.draw();
    }
}
