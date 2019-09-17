package com.sagar.chrysalis2019;

public class announcement  {
    private String description;
    private String title;


    public announcement(String description,String title) {
        this.description = description;
        this.title = title;

    }

    public announcement() {

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

   
}
