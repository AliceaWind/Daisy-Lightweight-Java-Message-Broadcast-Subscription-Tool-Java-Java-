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

    // 注册订阅者
    public void registerSubscriber(MessageSubscriber subscriber) {
        broadcaster.addSubscriber(subscriber);
    }

    // 取消注册订阅者
    public void unregisterSubscriber(MessageSubscriber subscriber) {
        broadcaster.removeSubscriber(subscriber);
    }

    // 广播消息（所有注册的订阅者都会收到）
    public void broadcast(Message message) {
        broadcaster.broadcast(message);
    }
}