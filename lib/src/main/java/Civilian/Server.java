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

        if (serverName.toLowerCase().equals("##########")) {
            System.out.println("##########\n");

            streamerPing = "##########";

            streamers = new String[]{"##########"};
            goingLiveChannel = "##########";
            goingLiveChannelCommunity = "##########";
            goingLiveRole = "##########";
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
                System.out.println(DiscordMessages.ANSI_GREY + "new stream time: " + stream.getTime() + "\nold stream time: " + lastStream.getTime() + "\ndifference: " + (stream.getTime()-lastStream.getTime()) + DiscordMessages.ANSI_RESET);
				
				if (!(stream.getTime()-lastStream.getTime() < streamPingDelay)) {
                    					check = true; 
				}
                i = streamList.length;
            }
        }
        return check;
    }

}
