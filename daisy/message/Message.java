package daisy.message;

public interface Message {
    String getTopic();
    Object getContent();
    long getTimeStamp();
}
