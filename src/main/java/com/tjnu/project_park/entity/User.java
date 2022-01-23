package com.tjnu.project_park.entity;

import java.util.Date;
import java.util.Objects;

/**
 * 用户信息实体类
 */
public class User {
    private Integer uid;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Date createdTime;
    private Date modifiedTime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createdTime;
    }

    public void setCreateTime(Date createTime) {
        this.createdTime = createTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUid(), user.getUid()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getPhone(), user.getPhone()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(createdTime, user.createdTime) && Objects.equals(getModifiedTime(), user.getModifiedTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getUsername(), getPassword(), getPhone(), getEmail(), createdTime, getModifiedTime());
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }
}
