# Daisy-Lightweight-Java-Message-Broadcast-Subscription-Tool-Java-Java-
A single-file, zero-dependency Java utility for thread-safe message broadcasting and subscription across classes. Just import and use. 
一个零依赖、单文件的 Java 工具类，实现跨类线程安全的消息广播与订阅。下载即用。
ゼロ依存、単一ファイルのJavaユーティリティ。スレッドセーフなクラス間メッセージのブロードキャストと購読を簡単に実装できます。


Daisy is a **single-file, zero-dependency** Java utility class for **thread-safe message broadcasting and subscription** between classes.

Features
- **Single file**: Just download `Daisy.java` and put it into your project.
- **Zero dependencies**: Works with standard Java SE.
- **Thread-safe**: Uses `ConcurrentHashMap` and `CopyOnWriteArrayList`.
- **Null-safe topic handling**: Fixes `null` key `hashCode()` issues.
- **Flexible**: Subscribe to specific topics or all topics.

Usage
```java
import daisy.Daisy;

public class MyClass extends Daisy {
    public MyClass() {
        setSubscriber("myTopic"); // subscribe to topic
    }

    @Override
    public void receive(String topic, Object content, long timestamp) {
        System.out.println("Received on " + topic + ": " + content);
    }

    public static void main(String[] args) {
        MyClass a = new MyClass();
        MyClass b = new MyClass();
        b.broadcast("myTopic", "Hello World!");
    }
}
```


Daisy 是一个单文件、零依赖的 Java 工具类，用于实现跨类的线程安全消息广播与订阅。

特性
单文件：下载 Daisy.java 放进项目即可用。

零依赖：纯 Java SE 实现。

线程安全：基于 ConcurrentHashMap 与 CopyOnWriteArrayList。

空主题安全处理：修复了 null 作为键时的 hashCode() 问题。

灵活订阅：支持订阅指定主题或全部主题。

用法
java
复制
编辑
```java

import daisy.Daisy;

public class 我的类 extends Daisy {
    public 我的类() {
        setSubscriber("我的主题"); // 订阅主题
    }

    @Override
    public void receive(String topic, Object content, long timestamp) {
        System.out.println("在 " + topic + " 收到: " + content);
    }

    public static void main(String[] args) {
        我的类 a = new 我的类();
        我的类 b = new 我的类();
        b.broadcast("我的主题", "你好世界！");
    }
}

```

Daisy は 単一ファイル・ゼロ依存 の Java ユーティリティクラスで、クラス間のスレッドセーフなメッセージブロードキャスト＆購読を実現します。

特徴
単一ファイル：Daisy.java をダウンロードしてプロジェクトに入れるだけ。

ゼロ依存：標準 Java SE のみで動作。

スレッドセーフ：ConcurrentHashMap と CopyOnWriteArrayList を使用。

null トピック安全処理：null キーの hashCode() 問題を修正。

柔軟な購読：特定のトピックまたは全トピックを購読可能。

使用例
java
复制
编辑

```java
import daisy.Daisy;

public class マイクラス extends Daisy {
    public マイクラス() {
        setSubscriber("マイトピック"); // トピック購読
    }

    @Override
    public void receive(String topic, Object content, long timestamp) {
        System.out.println(topic + " で受信: " + content);
    }

    public static void main(String[] args) {
        マイクラス a = new マイクラス();
        マイクラス b = new マイクラス();
        b.broadcast("マイトピック", "こんにちは世界！");
    }
}
```
