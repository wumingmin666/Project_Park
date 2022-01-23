package com.tjnu.project_park.util;

import java.util.Objects;

/**
 * @param
 * @return
 */
public class ParkToStatue {
    private String parkName;
    private Integer statue;

    @Override
    public String toString() {
        return "ParkToStatue{" +
                "parkName='" + parkName + '\'' +
                ", statue=" + statue +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkToStatue)) return false;
        ParkToStatue that = (ParkToStatue) o;
        return Objects.equals(getParkName(), that.getParkName()) && Objects.equals(getStatue(), that.getStatue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getParkName(), getStatue());
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public Integer getStatue() {
        return statue;
    }

    public void setStatue(Integer statue) {
        this.statue = statue;
    }




}
