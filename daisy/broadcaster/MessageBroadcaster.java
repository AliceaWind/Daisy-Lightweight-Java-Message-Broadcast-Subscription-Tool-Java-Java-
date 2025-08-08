package daisy.broadcaster;

import daisy.message.Message;

public interface MessageBroadcaster {
    void broadcast(Message message);
}
