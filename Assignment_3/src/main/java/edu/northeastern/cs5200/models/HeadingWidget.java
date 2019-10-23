/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.northeastern.cs5200.models;


/**
 *
 * @author Zhenyu Yang
 */
public class HeadingWidget extends Widget {
    private int heading;

    public HeadingWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, int heading) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.heading = heading;
        this.setDtype("heading");
    }
    
    public int getHeading() {
        return heading;
    }

    public void setHeading(int heading) {
        this.heading = heading;
    }
    
}
