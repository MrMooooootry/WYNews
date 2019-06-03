package com.example.tr534.wynews.splash.bean;

/**
 * Created by tr534 on 2018/12/22.
 */

public class Banner {

    /**
     * imgsrc : bigimg
     * skipID : 00AO0001|2298607
     * skipType : photoset
     * subtitle :
     * tag : photoset
     * title : 欧洲航天局公布火星陨石坑照片
     * url : 00AO0001|2298607
     */

    private String imgsrc;
    private String skipID;
    private String skipType;
    private String subtitle;
    private String tag;
    private String title;
    private String url;

    @Override
    public String toString() {
        return "Banner{" +
                "imgsrc='" + imgsrc + '\'' +
                ", skipID='" + skipID + '\'' +
                ", skipType='" + skipType + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", tag='" + tag + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc;
    }

    public String getSkipID() {
        return skipID;
    }

    public void setSkipID(String skipID) {
        this.skipID = skipID;
    }

    public String getSkipType() {
        return skipType;
    }

    public void setSkipType(String skipType) {
        this.skipType = skipType;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
