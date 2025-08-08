package SendingMessage0808;

import daisy.Daisy;
import java.util.List;



public class Sender {
    public void sendModifyCommand() {
        new Thread(() -> {
            try {
                Thread.sleep(1500);
                Daisy daisy = new Daisy();
                List<Object> replies = daisy.broadcastWithReply("modify_test_number", "请求修改testNumber为100");
                for (Object reply : replies) {
                    System.out.println("Sender收到回复：" + reply);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}