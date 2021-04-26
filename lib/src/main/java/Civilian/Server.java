import com.github.twitch4j.TwitchClient;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.*;


public class Server {
    
    public String streamerPing;
    public String[] streamers;
    public StreamEvent lastStream;
    public StreamListCustom[] streamList;
    public TwitchClient twitchBot;
    public String goingLiveChannel;
    public String goingLiveChannelCommunity;
    public String goingLiveRole;

    public Server() {
            streamerPing = "";
            streamers = new String[]{};
            goingLiveChannel = "";
            goingLiveChannelCommunity = "";
            goingLiveRole = null;
            lastStream = new StreamEvent("0");
    }

    public Server(String serverName, TwitchClient newTwitchBot) {
        twitchBot = newTwitchBot;

        if (serverName.toLowerCase().equals("ins0mniasr")) {
            System.out.println("ins0mnia's world\n");

            streamerPing = "ins0mniasr";

            streamers = new String[]{"ins0mniasr", "sozapidgeon", "eatsdryramen", "colliemonster", "zenonyra", "clark_o_tron", "wasabi__knight", "mistertoxic1", "sylvienukie", "paum_is_me", "tsira_kura", "evorder", "honeybirdie", "thehollowmc", "aquamaster_", "kounterkitty", "cerpintext", "sourcreamsupreme", "jenkar12"};
            goingLiveChannel = "731702663884046408";
            goingLiveChannelCommunity = "731702663884046408";
            goingLiveRole = "748788513872412702";
            makeStreamers(streamers);
            System.out.println("");
            lastStream = new StreamEvent("0");

        } else if (serverName.toLowerCase().equals("tsira_kura")) {
            System.out.println("tsira's nest\n");

            streamerPing = "tsira_kura";

            streamers = new String[]{"tsira_kura"};
            goingLiveChannel = "755572086436790323";
            goingLiveChannelCommunity = "";
            goingLiveRole = "833707149535346774";
            makeStreamers(streamers);
            System.out.println("");
            lastStream = new StreamEvent("0");
        }
    }

    private void makeStreamers(String[] streamers) {
        streamList = new StreamListCustom[streamers.length];

        for (int i = 0; i < streamers.length; i++) {
            streamList[i] = new StreamListCustom(streamers[i], 0);
            twitchBot.getClientHelper().enableStreamEventListener(streamList[i].getName());
            System.out.println("Stream Listener started for: " + streamList[i].getName());
        }
    }

    public boolean doubleCheck(StreamEvent stream, long streamPingDelay) {
        boolean check = false;
        for (int i = 0; i < streamList.length; i++) {
            if (stream.getName().toLowerCase().equals(streamList[i].getName())) {
                if (!(stream.getTime()-lastStream.getTime() < streamPingDelay)) {
                    check = true;
                }
                i = streamList.length;
            }
        }
        return check;
    }

}
