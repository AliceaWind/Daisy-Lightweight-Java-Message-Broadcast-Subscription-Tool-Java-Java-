# Daisy-Lightweight-Java-Message-Broadcast-Subscription-Tool-Java-Java-
A single-file, zero-dependency Java utility for thread-safe message broadcasting and subscription across classes. Just import and use. 
一个零依赖、单文件的 Java 工具类，实现跨类线程安全的消息广播与订阅。下载即用。
ゼロ依存、単一ファイルのJavaユーティリティ。スレッドセーフなクラス間メッセージのブロードキャストと購読を簡単に実装できます。

If you like Daisy, please ⭐ it on GitHub!


Daisy is a **single-file, zero-dependency** Java utility class for **thread-safe message broadcasting and subscription** between classes.

Features
- **Single file**: Just download `Daisy.java` and put it into your project.
- **Zero dependencies**: Works with standard Java SE.
- **Thread-safe**: Uses `ConcurrentHashMap` and `CopyOnWriteArrayList`.
- **Null-safe topic handling**: Fixes `null` key `hashCode()` issues.
- **Flexible**: Subscribe to specific topics or all topics.




Daisy 是一个单文件、零依赖的 Java 工具类，用于实现跨类的线程安全消息广播与订阅。

特性
单文件：下载 Daisy.java 放进项目即可用。

零依赖：纯 Java SE 实现。

线程安全：基于 ConcurrentHashMap 与 CopyOnWriteArrayList。

空主题安全处理：修复了 null 作为键时的 hashCode() 问题。

灵活订阅：支持订阅指定主题或全部主题。





Daisy は 単一ファイル・ゼロ依存 の Java ユーティリティクラスで、クラス間のスレッドセーフなメッセージブロードキャスト＆購読を実現します。

特徴
単一ファイル：Daisy.java をダウンロードしてプロジェクトに入れるだけ。

ゼロ依存：標準 Java SE のみで動作。

スレッドセーフ：ConcurrentHashMap と CopyOnWriteArrayList を使用。

null トピック安全処理：null キーの hashCode() 問題を修正。

柔軟な購読：特定のトピックまたは全トピックを購読可能。

Usage

```java
import daisy.Daisy;

public class Sender{
    private final Daisy daisy = new Daisy();

    public void sendASentence() throws InterruptedException {
        System.out.println("[Sender] Preparing to send...");
        Thread.sleep(1000); // 模拟耗时
        daisy.broadcast("thiser", "Hello from Sender!");
        System.out.println("[Sender] Message sent!");
    }
}
```



```java
import daisy.Daisy;

public class Reader extends Daisy {
    public void startReading() {
        // 订阅主题 "thiser"
        setSubscriber("thiser");
    }

    // 关键修改：重写 onMessage 方法（而非自定义 receive 方法）
    @Override
    protected void onMessage(String topic, Object content, long timestamp) {
        System.out.println("[Reader] Received on " + topic + ": " + content
                + " (timestamp: " + timestamp + ")");
    }

    public static void main(String[] args) throws InterruptedException {
        // （main 方法代码不变）
        Reader reader = new Reader();
        reader.startReading();

        Sender sender = new Sender();

        Thread senderThread = new Thread(() -> {
            try {
                sender.sendASentence();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        senderThread.start();
        senderThread.join();
    }
}
```
