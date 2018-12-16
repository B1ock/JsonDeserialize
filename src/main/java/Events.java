/**
 * Created by Igor on 15.12.2018.
 */
public class Events {

    private String eventName;
    private String time;
    private long timeMilis;

    public long getTimeMilis() {
        return timeMilis;
    }

    public void setTimeMilis(long timeMilis) {
        this.timeMilis = timeMilis;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getEventName() {

        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
}


