package daisy.center;

import daisy.broadcaster.MessageBroadcasterClass;
import daisy.message.Message;
import daisy.subscriber.MessageSubscriber;

public class MessageCenter {
    private static MessageCenter instance;
    private final MessageBroadcasterClass broadcaster;

    private MessageCenter() {
        broadcaster = new MessageBroadcasterClass();
    }

    public static synchronized MessageCenter getInstance() {
        if (instance == null) {
            instance = new MessageCenter();
        }
        return instance;
    }

    public void registerSubscriber(MessageSubscriber subscriber) {
        broadcaster.addSubscriber(subscriber);
    }

    public void unregisterSubscriber(MessageSubscriber subscriber) {
        broadcaster.removeSubscriber(subscriber);
    }

    public void broadcast(Message message) {
        broadcaster.broadcast(message);
    }
}