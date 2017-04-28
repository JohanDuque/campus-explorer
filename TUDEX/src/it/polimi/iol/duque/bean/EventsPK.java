package it.polimi.iol.duque.bean;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Duque on 29/04/2017.
 */
public class EventsPK implements Serializable {
    private String title;
    private long starttime;
    private long endtime;

    @Column(name = "title", nullable = false, length = 255)
    @Id
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "starttime", nullable = false)
    @Id
    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    @Column(name = "endtime", nullable = false)
    @Id
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

        EventsPK eventsPK = (EventsPK) o;

        if (starttime != eventsPK.starttime) return false;
        if (endtime != eventsPK.endtime) return false;
        if (title != null ? !title.equals(eventsPK.title) : eventsPK.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (int) (starttime ^ (starttime >>> 32));
        result = 31 * result + (int) (endtime ^ (endtime >>> 32));
        return result;
    }
}
