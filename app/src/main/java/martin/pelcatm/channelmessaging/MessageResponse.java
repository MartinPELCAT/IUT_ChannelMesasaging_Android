package martin.pelcatm.channelmessaging;

import java.util.ArrayList;

/**
 * Created by pelcatm on 26/02/2018.
 */
public class MessageResponse {
    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<Message> messages;
}
