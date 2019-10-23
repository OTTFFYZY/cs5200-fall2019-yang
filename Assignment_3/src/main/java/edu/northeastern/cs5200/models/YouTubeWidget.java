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
public class YouTubeWidget extends Widget {
    private String url;
    private int shareble;
    private int expandable;

    public YouTubeWidget(int id, String name, int width, int height, String cssClass, String cssStyle, String text, int order, String url, int shareble, int expandable) {
        super(id, name, width, height, cssClass, cssStyle, text, order);
        this.url = url;
        this.shareble = shareble;
        this.expandable = expandable;
        this.setDtype("youtube");
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getShareble() {
        return shareble;
    }

    public void setShareble(int shareble) {
        this.shareble = shareble;
    }

    public int getExpandable() {
        return expandable;
    }

    public void setExpandable(int expandable) {
        this.expandable = expandable;
    }
    
    
}
