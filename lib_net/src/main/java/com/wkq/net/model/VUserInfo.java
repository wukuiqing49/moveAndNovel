package com.wkq.net.model;

/**
 * 作者:吴奎庆
 * <p>
 * 时间:2020-03-26
 * <p>
 * 用途:
 */


public class VUserInfo {

    /**
     * userId : 34
     * userName : null
     * userIMEI : 1231231241341341
     * userPwd : wukuiqing
     * userPhoneNum : 18538537389
     * userState : 0
     * userIcon : null
     * userAddress : null
     */

    private int userId;
    private String userName;
    private String userIMEI;
    private String userPwd;
    private String userPhoneNum;
    private int userState;
    private String userIcon;
    private String userAddress;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIMEI() {
        return userIMEI;
    }

    public void setUserIMEI(String userIMEI) {
        this.userIMEI = userIMEI;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserPhoneNum() {
        return userPhoneNum;
    }

    public void setUserPhoneNum(String userPhoneNum) {
        this.userPhoneNum = userPhoneNum;
    }

    public int getUserState() {
        return userState;
    }

    public void setUserState(int userState) {
        this.userState = userState;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
}
