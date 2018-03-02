package martin.pelcatm.channelmessaging;

import java.util.ArrayList;

/**
 * Created by pelcatm on 29/01/2018.
 */
public class ChannelResponse {
    public ArrayList<Channel> getChannels() {
        return channels;
    }

    public void setChannels(ArrayList<Channel> channels) {
        this.channels = channels;
    }

    public ArrayList<Channel> channels;

}
