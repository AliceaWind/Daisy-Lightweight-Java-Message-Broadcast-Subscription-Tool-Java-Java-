package SendingMessage0808;

import daisy.Daisy;

import java.util.List;

public class Reader extends Daisy {
    private int testNumber = 0; // 私有变量，仅Reader内部可修改

    public Reader() {
        setSubscriber("modify_test_number");
        setSubscriber("check_test_number");
    }

    @Override
    protected Object onMessage(String topic, Object content, long timestamp) {
        if ("modify_test_number".equals(topic)) {
            this.testNumber = 100; // 仅Reader内部修改私有变量
            return "修改完成：testNumber已更新为100";
        }
        else if ("check_test_number".equals(topic)) {
            return testNumber;
        }
        return null;
    }

    public static void main(String[] args) {
        Reader reader = new Reader();
        Sender sender = new Sender();

        sender.sendModifyCommand();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<Object> checkResults = reader.broadcastWithReply("check_test_number", null);
        if (!checkResults.isEmpty()) {
            int currentValue = (Integer) checkResults.get(0);
            System.out.println("最终检查结果：testNumber = " + currentValue);
            System.out.println(currentValue == 100 ? "修改成功" : "修改失败");
        }
    }
}