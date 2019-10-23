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
public class ImageWidget extends Widget {
    private String src;

    public ImageWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String src) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.src = src;
        this.setDtype("image");
    }
    
    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }
    
}
