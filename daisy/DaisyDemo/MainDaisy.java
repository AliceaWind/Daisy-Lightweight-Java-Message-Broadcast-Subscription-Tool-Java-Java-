// daisy/DaisyDemo/MainDaisy.java
package daisy.DaisyDemo;

import daisy.center.MessageCenter;
import daisy.message.Message;
import daisy.message.MessageClass;
import daisy.subscriber.MessageSubscriberClass;

public class MainDaisy {
    public static void main(String[] args) {
        // 1. 获取全局消息中心
        MessageCenter center = MessageCenter.getInstance();

        // 2. 创建订阅者：
        // - 订阅者1：只关注「start」主题
        // - 订阅者2：关注所有主题
        MessageSubscriberClass subscriber1 = new MessageSubscriberClass("start");
        MessageSubscriberClass subscriber2 = new MessageSubscriberClass(null);

        // 3. 注册订阅者到消息中心
        center.registerSubscriber(subscriber1);
        center.registerSubscriber(subscriber2);

        // 4. 广播消息
        Message startMsg = new MessageClass("start", "游戏开始！");
        Message endMsg = new MessageClass("end", "游戏结束！");

        System.out.println("=== 第一次广播：发送「start」消息 ===");
        center.broadcast(startMsg); // 两个订阅者都会收到

        System.out.println("\n=== 第二次广播：发送「end」消息 ===");
        center.broadcast(endMsg); // 只有订阅者2收到

        // 5. 移除订阅者1
        center.unregisterSubscriber(subscriber1);
        System.out.println("\n=== 移除订阅者1后，发送「start」消息 ===");
        center.broadcast(startMsg); // 只有订阅者2收到
    }
}