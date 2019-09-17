package com.sagar.chrysalis2019.ui.Share;

public class uploadImgae  {
    private String Caption;
    private String ImageUrl;

    public uploadImgae() {

    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public uploadImgae(String caption, String ImageUrl) {
        Caption = caption;
        this.ImageUrl = ImageUrl;
    }

    public String getCaption() {
        return Caption;
    }

    public String getImageUrl() {
        return ImageUrl;
    }
}
