package it.polimi.iol.duque.bean;

import java.io.Serializable;

/**
 * Created by Duque on 30/04/2017.
 */
public class Events implements Serializable {
    private int id;
    private String type;
    private String title;
    private String description;
    private Integer flames;
    private Double latitude;
    private long startTime;
    private long endTime;
    private Double longitude;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFlames() {
        return flames;
    }

    public void setFlames(Integer flames) {
        this.flames = flames;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endtime) {
        this.endTime = endtime;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events events = (Events) o;

        if (id != events.id) return false;
        if (startTime != events.startTime) return false;
        if (endTime != events.endTime) return false;
        if (type != null ? !type.equals(events.type) : events.type != null) return false;
        if (title != null ? !title.equals(events.title) : events.title != null) return false;
        if (description != null ? !description.equals(events.description) : events.description != null) return false;
        if (flames != null ? !flames.equals(events.flames) : events.flames != null) return false;
        if (latitude != null ? !latitude.equals(events.latitude) : events.latitude != null) return false;
        if (longitude != null ? !longitude.equals(events.longitude) : events.longitude != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (flames != null ? flames.hashCode() : 0);
        result = 31 * result + (latitude != null ? latitude.hashCode() : 0);
        result = 31 * result + (int) (startTime ^ (startTime >>> 32));
        result = 31 * result + (int) (endTime ^ (endTime >>> 32));
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }
}
