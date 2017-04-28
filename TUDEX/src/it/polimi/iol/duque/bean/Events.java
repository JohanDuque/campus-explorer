package it.polimi.iol.duque.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Duque on 29/04/2017.
 */
@Entity
@IdClass(EventsPK.class)
public class Events {
    private int id;
    private String type;
    private String title;
    private String description;
    private Integer flames;
    private Serializable coordinates;
    private long starttime;
    private long endtime;

    @Basic
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 255)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Id
    @Column(name = "title", nullable = false, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "flames", nullable = true)
    public Integer getFlames() {
        return flames;
    }

    public void setFlames(Integer flames) {
        this.flames = flames;
    }

    @Basic
    @Column(name = "coordinates", nullable = true)
    public Serializable getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Serializable coordinates) {
        this.coordinates = coordinates;
    }

    @Id
    @Column(name = "starttime", nullable = false)
    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    @Id
    @Column(name = "endtime", nullable = false)
    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Events events = (Events) o;

        if (id != events.id) return false;
        if (starttime != events.starttime) return false;
        if (endtime != events.endtime) return false;
        if (type != null ? !type.equals(events.type) : events.type != null) return false;
        if (title != null ? !title.equals(events.title) : events.title != null) return false;
        if (description != null ? !description.equals(events.description) : events.description != null) return false;
        if (flames != null ? !flames.equals(events.flames) : events.flames != null) return false;
        if (coordinates != null ? !coordinates.equals(events.coordinates) : events.coordinates != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (flames != null ? flames.hashCode() : 0);
        result = 31 * result + (coordinates != null ? coordinates.hashCode() : 0);
        result = 31 * result + (int) (starttime ^ (starttime >>> 32));
        result = 31 * result + (int) (endtime ^ (endtime >>> 32));
        return result;
    }
}
