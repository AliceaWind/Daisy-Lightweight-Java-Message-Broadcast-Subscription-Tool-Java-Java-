package daisy;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 消息广播与订阅基类，用户通过继承此类实现跨类消息通信
 * 修复了null主题作为键时的hashCode()错误
 */
public class Daisy {
    // 线程安全的订阅者映射：key为主题，使用特殊标记表示"所有主题"
    private static final Map<String, List<Daisy>> SUBSCRIBERS_MAP = new ConcurrentHashMap<>();
    // 用于表示"订阅所有主题"的特殊标记（替代null）
    private static final String ALL_TOPICS_MARKER = "__ALL_TOPICS__";

    /**
     * 订阅指定主题的消息
     * @param topic 主题名称（null表示订阅所有主题）
     */
    public void setSubscriber(String topic) {
        // 处理"订阅所有主题"的情况，使用特殊标记代替null
        String key = (topic == null) ? ALL_TOPICS_MARKER : topic;
        // 计算并添加订阅者，使用线程安全的列表存储
        SUBSCRIBERS_MAP.computeIfAbsent(key, k -> new CopyOnWriteArrayList<>()).add(this);
    }

    /**
     * 取消订阅指定主题
     * @param topic 主题名称（null表示取消所有主题的订阅）
     */
    public void removeSubscriber(String topic) {
        String key = (topic == null) ? ALL_TOPICS_MARKER : topic;
        List<Daisy> subscribers = SUBSCRIBERS_MAP.get(key);
        if (subscribers != null) {
            subscribers.remove(this);
        }
    }

    /**
     * 广播消息到指定主题
     * @param topic 消息主题
     * @param content 消息内容
     */
    public void broadcast(String topic, Object content) {
        long timestamp = System.currentTimeMillis();

        // 1. 发送给订阅当前主题的订阅者
        List<Daisy> topicSubscribers = SUBSCRIBERS_MAP.get(topic);
        if (topicSubscribers != null) {
            for (Daisy subscriber : topicSubscribers) {
                subscriber.onMessage(topic, content, timestamp);
            }
        }

        // 2. 发送给订阅所有主题的订阅者（使用特殊标记查找）
        List<Daisy> allSubscribers = SUBSCRIBERS_MAP.get(ALL_TOPICS_MARKER);
        if (allSubscribers != null) {
            for (Daisy subscriber : allSubscribers) {
                subscriber.onMessage(topic, content, timestamp);
            }
        }
    }

    /**
     * 消息处理回调方法（子类重写此方法处理收到的消息）
     * @param topic 消息主题
     * @param content 消息内容
     * @param timestamp 消息发送时间戳
     */
    protected void onMessage(String topic, Object content, long timestamp) {
        // 默认空实现，由子类根据需要重写
    }
}
