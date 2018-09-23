package gukbab1216.com.chalkak.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class DramaDto implements Serializable {

    String title, image, description;
    ArrayList<Picture> pictures;


    public DramaDto() {
    }

    public DramaDto(String title, String image, String description) {
        this.title = title;
        this.image = image;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Picture> pictures) {
        this.pictures = pictures;
    }
}
