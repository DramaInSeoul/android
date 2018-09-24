package gukbab1216.com.chalkak.Model;

import java.io.Serializable;

public class Picture implements Serializable {

    String imgUrl;
    int latitude;
    int longitude;
    String posTitle;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getPosTitle() {
        return posTitle;
    }

    public void setPosTitle(String posTitle) {
        this.posTitle = posTitle;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "imgUrl='" + imgUrl + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", posTitle='" + posTitle + '\'' +
                '}';
    }
}
