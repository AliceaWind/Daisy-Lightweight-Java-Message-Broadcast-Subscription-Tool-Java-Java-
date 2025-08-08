package daisy.broadcaster;

import daisy.message.Message;
import daisy.subscriber.MessageSubscriber;
import java.util.LinkedList;
import java.util.List;

public class MessageBroadcasterClass implements MessageBroadcaster {
    private final List<MessageSubscriber> subscribers = new LinkedList<>();

    public void addSubscriber(MessageSubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(MessageSubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void broadcast(Message message) {
        for (MessageSubscriber subscriber : subscribers) {
            subscriber.subscribe(message);
        }
    }
}