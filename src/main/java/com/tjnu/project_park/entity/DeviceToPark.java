package com.tjnu.project_park.entity;

import java.util.Objects;

/**
 * @param
 * @return
 */
public class DeviceToPark {
    private Integer id;
    private Integer deviceId;
    private Integer parkId;
    private Integer pid;
    private String parkName;
    private Integer floorNum;
    private Integer statue;
    private Integer isDelete;
    private String createdTime;
    private String modifiedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifiedTime() {
        return modifiedTime;
    }

    public void setModifiedTime(String modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeviceToPark)) return false;
        DeviceToPark that = (DeviceToPark) o;
        return Objects.equals(getId(), that.getId()) && Objects.equals(getDeviceId(), that.getDeviceId()) && Objects.equals(getParkId(), that.getParkId()) && Objects.equals(getPid(), that.getPid()) && Objects.equals(getParkName(), that.getParkName()) && Objects.equals(getFloorNum(), that.getFloorNum()) && Objects.equals(getStatue(), that.getStatue()) && Objects.equals(getIsDelete(), that.getIsDelete()) && Objects.equals(getCreatedTime(), that.getCreatedTime()) && Objects.equals(getModifiedTime(), that.getModifiedTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDeviceId(), getParkId(), getPid(), getParkName(), getFloorNum(), getStatue(), getIsDelete(), getCreatedTime(), getModifiedTime());
    }

    @Override
    public String toString() {
        return "DeviceToPark{" +
                "id=" + id +
                ", deviceId=" + deviceId +
                ", parkId=" + parkId +
                ", pid=" + pid +
                ", parkName='" + parkName + '\'' +
                ", floorNum=" + floorNum +
                ", statue=" + statue +
                ", isDelete=" + isDelete +
                ", createdTime='" + createdTime + '\'' +
                ", modifiedTime='" + modifiedTime + '\'' +
                '}';
    }
}
