package com.tjnu.project_park.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @param
 * @return
 */
public class Park {
    private Integer pid;
    private String location;
    private String picture;
    private String url;
    private String name;
    private String placeDescribe;
    private Date createdTime;
    private Date modifiedTime;

    @Override
    public String toString() {
        return "Park{" +
                "pid=" + pid +
                ", location='" + location + '\'' +
                ", picture='" + picture + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", placeDescribe='" + placeDescribe + '\'' +
                ", createdTime=" + createdTime +
                ", modifiedTime=" + modifiedTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Park)) return false;
        Park park = (Park) o;
        return Objects.equals(getPid(), park.getPid()) && Objects.equals(getLocation(), park.getLocation()) && Objects.equals(getPicture(), park.getPicture()) && Objects.equals(getUrl(), park.getUrl()) && Objects.equals(getName(), park.getName()) && Objects.equals(getPlaceDescribe(), park.getPlaceDescribe()) && Objects.equals(getCreatedTime(), park.getCreatedTime()) && Objects.equals(getModifiedTime(), park.getModifiedTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPid(), getLocation(), getPicture(), getUrl(), getName(), getPlaceDescribe(), getCreatedTime(), getModifiedTime());
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceDescribe() {
        return placeDescribe;
    }

    public void setPlaceDescribe(String placeDescribe) {
        this.placeDescribe = placeDescribe;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime;
    }
}
