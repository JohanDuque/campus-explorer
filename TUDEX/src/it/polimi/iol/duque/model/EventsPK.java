package it.polimi.iol.duque.model;

import java.io.Serializable;

/**
 * Created by Duque on 30/04/2017.
 */
public class EventsPK implements Serializable {
    private String title;
    private long starttime;
    private long endtime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getStarttime() {
        return starttime;
    }

    public void setStarttime(long starttime) {
        this.starttime = starttime;
    }

    public long getEndTime() {
        return endtime;
    }

    public void setEndTime(long endtime) {
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
