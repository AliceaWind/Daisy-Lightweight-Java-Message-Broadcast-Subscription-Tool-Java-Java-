package daisy.subscriber;

import daisy.message.Message;
import java.util.LinkedList;
import java.util.List;

public class MessageSubscriberClass implements MessageSubscriber {
    private final List<Message> receivedMessages = new LinkedList<>();
    private final String subscribedTopic; // 订阅的主题（null表示接收所有）

    // 构造方法：指定订阅的主题
    public MessageSubscriberClass(String subscribedTopic) {
        this.subscribedTopic = subscribedTopic;
    }

    @Override
    public void subscribe(Message message) {
        if (subscribedTopic == null || subscribedTopic.equals(message.getTopic())) {
            receivedMessages.add(message);
            handleMessage(message); // 处理消息的具体逻辑
        }
    }

    private void handleMessage(Message message) {
        System.out.printf("[订阅者] 收到主题「%s」的消息：%s（时间：%d）%n",
                message.getTopic(), message.getContent(), message.getTimeStamp());
    }

    public List<Message> getReceivedMessages() {
        return new LinkedList<>(receivedMessages); // 返回副本，避免外部修改
    }
}