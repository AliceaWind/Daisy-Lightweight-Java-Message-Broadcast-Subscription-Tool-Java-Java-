package daisy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.ArrayList;

public class Daisy {
    private static final Map<String, List<Daisy>> SUBSCRIBERS_MAP = new ConcurrentHashMap<>();
    private static final String ALL_TOPICS_MARKER = "__ALL_TOPICS__";

    public void setSubscriber(String topic) {
        String key = (topic == null) ? ALL_TOPICS_MARKER : topic;
        SUBSCRIBERS_MAP.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(this);
    }

    public void removeSubscriber(String topic) {
        String key = (topic == null) ? ALL_TOPICS_MARKER : topic;
        List<Daisy> subscribers = SUBSCRIBERS_MAP.get(key);
        if (subscribers != null) {
            subscribers.remove(this);
        }
    }

    /**
     * 广播消息并收集返回结果
     * @return 所有接收者的返回值列表（不含null）
     */
    public List<Object> broadcastWithReply(String topic, Object content) {
        long timestamp = System.currentTimeMillis();
        List<Object> replies = new ArrayList<>();

        // 1. 发送给指定主题订阅者并收集结果
        List<Daisy> topicSubscribers = SUBSCRIBERS_MAP.get(topic);
        if (topicSubscribers != null) {
            for (Daisy subscriber : topicSubscribers) {
                Object reply = subscriber.onMessage(topic, content, timestamp);
                if (reply != null) { // 过滤null值
                    replies.add(reply);
                }
            }
        }

        // 2. 发送给订阅所有主题的接收者并收集结果
        List<Daisy> allSubscribers = SUBSCRIBERS_MAP.get(ALL_TOPICS_MARKER);
        if (allSubscribers != null) {
            for (Daisy subscriber : allSubscribers) {
                Object reply = subscriber.onMessage(topic, content, timestamp);
                if (reply != null) { // 过滤null值
                    replies.add(reply);
                }
            }
        }

        return replies;
    }

    /**
     * 消息处理回调（子类重写并返回结果）
     * @return 处理结果（可返回null）
     */
    protected Object onMessage(String topic, Object content, long timestamp) {
        // 默认返回null，子类根据需要重写
        return null;
    }

    // 保留原有的无返回值广播方法（兼容旧逻辑）
    public void broadcast(String topic, Object content) {
        broadcastWithReply(topic, content); // 内部调用带返回值的方法但忽略结果
    }
}