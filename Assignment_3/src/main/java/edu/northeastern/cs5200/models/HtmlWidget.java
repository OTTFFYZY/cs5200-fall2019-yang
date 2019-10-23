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
public class HtmlWidget extends Widget {
    private String html;
    
    public HtmlWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String html) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.html = html;
        this.setDtype("html");
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    
}
