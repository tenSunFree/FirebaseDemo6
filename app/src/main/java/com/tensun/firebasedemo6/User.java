package com.tensun.firebasedemo6;

/**
 * Created by Administrator on 2017/10/24.
 */

/** 對於Listview 的每一個Item裡面想顯示的哪些資料 */
public class User {

    private String name;
    private String message;
    private String profilePictureUrl;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }
}
