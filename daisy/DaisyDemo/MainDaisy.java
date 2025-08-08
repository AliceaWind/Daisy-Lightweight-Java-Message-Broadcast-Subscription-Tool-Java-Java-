// daisy/DaisyDemo/MainDaisy.java
package daisy.DaisyDemo;

import daisy.center.MessageCenter;
import daisy.message.Message;
import daisy.message.MessageClass;
import daisy.subscriber.MessageSubscriberClass;

public class MainDaisy {
    public static void main(String[] args) {
        MessageCenter center = MessageCenter.getInstance();


        MessageSubscriberClass subscriber1 = new MessageSubscriberClass("start");
        MessageSubscriberClass subscriber2 = new MessageSubscriberClass(null);

        center.registerSubscriber(subscriber1);
        center.registerSubscriber(subscriber2);

        Message startMsg = new MessageClass("start", "游戏开始！");
        Message endMsg = new MessageClass("end", "游戏结束！");

        System.out.println("=== 第一次广播：发送「start」消息 ===");
        center.broadcast(startMsg);

        System.out.println("\n=== 第二次广播：发送「end」消息 ===");
        center.broadcast(endMsg);

        center.unregisterSubscriber(subscriber1);
        System.out.println("\n=== 移除订阅者1后，发送「start」消息 ===");
        center.broadcast(startMsg);
    }
}