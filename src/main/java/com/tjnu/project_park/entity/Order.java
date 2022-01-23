package com.tjnu.project_park.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @param
 * @return
 */
public class Order {
    private Integer id;
    private String bookingUser;
    private Date bookingStartTime;
    private Date bookingEndTime;
    private Integer parkId;
    private Integer pid;
    private String plateNumber;
    private Date createdTime;
    private Integer isExist;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", bookingUser='" + bookingUser + '\'' +
                ", bookingStartTime=" + bookingStartTime +
                ", bookingEndTime=" + bookingEndTime +
                ", parkId=" + parkId +
                ", pid=" + pid +
                ", plateNumber='" + plateNumber + '\'' +
                ", createdTime=" + createdTime +
                ", isExist=" + isExist +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId()) && Objects.equals(getBookingUser(), order.getBookingUser()) && Objects.equals(getBookingStartTime(), order.getBookingStartTime()) && Objects.equals(getBookingEndTime(), order.getBookingEndTime()) && Objects.equals(getParkId(), order.getParkId()) && Objects.equals(getPid(), order.getPid()) && Objects.equals(getPlateNumber(), order.getPlateNumber()) && Objects.equals(getCreatedTime(), order.getCreatedTime()) && Objects.equals(getIsExist(), order.getIsExist());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBookingUser(), getBookingStartTime(), getBookingEndTime(), getParkId(), getPid(), getPlateNumber(), getCreatedTime(), getIsExist());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBookingUser() {
        return bookingUser;
    }

    public void setBookingUser(String bookingUser) {
        this.bookingUser = bookingUser;
    }

    public Date getBookingStartTime() {
        return bookingStartTime;
    }

    public void setBookingStartTime(Date bookingStartTime) {
        this.bookingStartTime = bookingStartTime;
    }

    public Date getBookingEndTime() {
        return bookingEndTime;
    }

    public void setBookingEndTime(Date bookingEndTime) {
        this.bookingEndTime = bookingEndTime;
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

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getIsExist() {
        return isExist;
    }

    public void setIsExist(Integer isExist) {
        this.isExist = isExist;
    }
}
