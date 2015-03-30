package org.hz.wifichat;

import java.io.Serializable;

/**
 * wifi连接用户信息
 * Author huarizhong
 * Date 2015/3/30 10:14
 * PackageName org.hz.wifichat
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 2L;
    private int id = 0;//id
    private String nickName="";//昵称
    private String ip="";//ip地址
    private String loginTime="";//登录时间
    private long timeStamp=0;//时间戳

    public Person(){}

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
