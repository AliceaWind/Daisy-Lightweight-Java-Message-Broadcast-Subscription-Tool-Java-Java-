package daisy.subscriber;

import daisy.message.Message;

public interface MessageSubscriber {
    void subscribe(Message message);
}