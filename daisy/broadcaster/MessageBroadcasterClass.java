package daisy.broadcaster;

import daisy.message.Message;
import daisy.subscriber.MessageSubscriber;
import java.util.LinkedList;
import java.util.List;

public class MessageBroadcasterClass implements MessageBroadcaster {
    private final List<MessageSubscriber> subscribers = new LinkedList<>();

    // 添加订阅者
    public void addSubscriber(MessageSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    // 移除订阅者
    public void removeSubscriber(MessageSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void broadcast(Message message) {
        // 向所有订阅者发送消息
        for (MessageSubscriber subscriber : subscribers) {
            subscriber.subscribe(message);
        }
    }
}