package com.taishow.dto;

public class ShowsDto {

    private String showTime;
    private Integer screenId;
    private String screenName;
    private String screenClass;

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public Integer getScreenId() {
        return screenId;
    }

    public void setScreenId(Integer screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }

    public String getScreenClass() {
        return screenClass;
    }

    public void setScreenClass(String screenClass) {
        this.screenClass = screenClass;
    }
}
