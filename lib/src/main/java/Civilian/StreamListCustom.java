public class StreamListCustom {
    private String name;
    private StreamEvent streamEvent;
    private int startTime;

    public StreamListCustom() {
        setName("");
        setStreamEvent(null);
        setStartTime(0);
    }

    public StreamListCustom(String newName, int newStartTime) {
        setName(newName);
        setStreamEvent();
        setStartTime(newStartTime);
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setStreamEvent(StreamEvent newStreamEvent) {
        streamEvent = newStreamEvent;
    }

    public void setStreamEvent() {
        streamEvent = new StreamEvent("0");
    }

    public void setStartTime(int newStartTime) {
        startTime = newStartTime;
    }

    public String getName() {
        return name;
    }

    public StreamEvent getStreamEvent() {
        return streamEvent;
    }

    public int getStartTime() {
        return startTime;
    }
}