package com.example.demo.vo;

/**
 * @author Administrator
 */
public class MessageVO {


    private String title;
    private String content;
    private String extraInfo;


    public MessageVO(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public MessageVO(String title, String content, String extraInfo) {
        this.title = title;
        this.content = content;
        this.extraInfo = extraInfo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }
}
