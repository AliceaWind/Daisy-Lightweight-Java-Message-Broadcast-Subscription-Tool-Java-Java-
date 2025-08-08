package daisy.message;

public class MessageClass implements Message{



    private String topic;
    private Object content;
    private long timeStamp;

    public MessageClass(String topic, Object content) {
        this.topic = topic;
        this.content = content;
        this.timeStamp = System.currentTimeMillis();
    }

    @Override
    public String getTopic() {
        return topic;
    }

    @Override
    public Object getContent() {
        return content;
    }

    @Override
    public long getTimeStamp() {
        return timeStamp;
    }
}
