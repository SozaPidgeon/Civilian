import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.helix.domain.Stream;
import com.github.twitch4j.helix.domain.StreamList;

import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Collections;
import java.util.Locale;
import java.util.Arrays;
import java.util.List;

public class TwitchManager {
    public static final String twitchId = "##########";
    public static final String twitchSecret = "##########";
    
    private static String[] serverList = {"##########"};

    private static long streamPingDelay = 600000;

    public static final Color goingLiveColor = new Color(100, 65, 165);
    public static final Color ########## = new Color(255, 0, 0);

    private static TwitchClient twitchBot;

    public static void StartTwitchClient() {
        twitchBot = TwitchClientBuilder.builder()
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withClientId(twitchId)
                .withClientSecret(twitchSecret)
                .build();

        System.out.println("");

        Server[] server = new Server[serverList.length];
        for (int i = 0; i < serverList.length; i++) {
            server[i] = new Server(serverList[i], twitchBot);
        }

        twitchBot.getEventManager().onEvent(ChannelGoLiveEvent.class, event -> {
        
            boolean streamStart = false;

            StreamEvent stream = new StreamEvent(event, twitchBot);

            System.out.println("Stream event triggered for " + stream.getName());

            for (int i = 0; i < server.length; i++) {
                if (server[i].doubleCheck(stream, streamPingDelay)) {
                    startStreamEmbed(stream, server[i]);
                    server[i].lastStream = stream;
                    streamStart = true;
                }
            }

            if (streamStart) {
                System.out.println(DiscordMessages.ANSI_STREAM_START + "Stream posted for: " + stream.getName() + ": " + stream.getTitle() + DiscordMessages.ANSI_RESET);
            } else {
				System.out.println(DiscordMessages.ANSI_STREAM_DENY + "Stream not posted" + DiscordMessages.ANSI_RESET);
			}
        });
    }

    private static void startStreamEmbed(StreamEvent stream, Server server) {
        String goingLiveText = (stream.getName().toLowerCase() + " is going live now");
        startStreamEmbed(stream, goingLiveText, server);
    }

    private static void startStreamEmbed(StreamEvent stream, String goingLiveText, Server server) {
        EmbedBuilder goingLiveEmbed = makeStreamEmbed(stream, true);
        DiscordMessages.postTwitchLive(goingLiveEmbed, stream.getName(), goingLiveText, server);
        goingLiveEmbed.clear();
    }

    private static EmbedBuilder makeStreamEmbed(StreamEvent stream, boolean liveCheck) {

        String author = stream.getName();
        EmbedBuilder goingLiveEmbed = new EmbedBuilder();
        
        if (stream.getName().toLowerCase().equals("##########")) {
            goingLiveEmbed.setColor(##########);
        } else {
            goingLiveEmbed.setColor(goingLiveColor);
        }
        
        goingLiveEmbed.setTitle(stream.getTitle(), stream.getUrl());
        if (liveCheck == true) {
            author = (author + " just went live!");
        }
        goingLiveEmbed.setAuthor(author, null, stream.getUserPictureUrl());
        goingLiveEmbed.setThumbnail(stream.getUserPictureUrl());
        goingLiveEmbed.addField("Game", stream.getGame(), false);

        return goingLiveEmbed;
    }

    public static void searchStream(String username, String searchText, MessageReceivedEvent event) {
        StreamList resultList = twitchBot.getHelix().getStreams(null, null, null, null, null, null, null, Arrays.asList(username)).execute();

        StreamEvent stream = new StreamEvent(resultList.getStreams().get(0), twitchBot);

        searchStreamEmbed(stream, searchText, event);
    }

    private static void searchStreamEmbed(StreamEvent stream, String searchText, MessageReceivedEvent event) {
        
        EmbedBuilder streamEmbed = makeStreamEmbed(stream, false);

        DiscordMessages.postTwitchSearch(streamEmbed, searchText, event);

        streamEmbed.clear();
    }
}
