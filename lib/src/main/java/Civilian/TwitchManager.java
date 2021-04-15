import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import com.github.twitch4j.helix.domain.User;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Collections;
import java.util.Locale;

public class TwitchManager {
    public static final String twitchId = "32p3en14qg28y227gim6phritbu1la";
    public static final String twitchSecret = "q6z3biu1wbo26lgrczly90thm1ht2g";
    private static final String[] streamer = {"ins0mniasr", "zenonyra", "sozapidgeon", "clark_o_tron", "wasabi__knight", "mistertoxic1", "sylvienukie", "paum_is_me", "tsira_kura", "evorder", "honeybirdie", "thehollowmc", "aquamaster_", "kounterkitty", "cerpintext", "gidge", "manicjamie", "krythomsr"};

    public static final String streamerPing = "ins0mniasr";

    private static long streamPingDelay = 120000;

    public static final Color goingLiveColor = new Color(100, 65, 165);
    public static final Color SomniBlue = new Color(0, 102, 255);

    private static Streamer lastStream;

    private static TwitchClient twitchBot;

    public static void StartTwitchClient() {
        twitchBot = TwitchClientBuilder.builder()
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withClientId(twitchId)
                .withClientSecret(twitchSecret)
                .build();

        for (int i = 0; i < streamer.length; i++) {
            twitchBot.getClientHelper().enableStreamEventListener(streamer[i]);
            System.out.println("Stream Listener started for: " + streamer[i]);
        }

        lastStream = new Streamer(0);

        twitchBot.getEventManager().onEvent(ChannelGoLiveEvent.class, event -> {
        
            Streamer stream = new Streamer(event, twitchBot);

            System.out.println("Stream event triggered for: " + stream.getName());

            System.out.println("stream time: " + stream.getTime() + "\nlast time: " + lastStream.getTime() + "\ndifference: " + (stream.getTime() - lastStream.getTime()));

            if (stream.getTime() - lastStream.getTime() < streamPingDelay) {
            } else {
                startStreamEmbed(stream);
            }
            lastStream = stream;
        });
    }

    private static void startStreamEmbed(Streamer stream) {
        
        System.out.println("Stream started for " + stream.getName() + ": " + stream.getTitle());

        EmbedBuilder goingLive = new EmbedBuilder();
        if (stream.getName().toLowerCase().equals("ins0mniasr")) {
            goingLive.setColor(SomniBlue);
        } else {
            goingLive.setColor(goingLiveColor);
        }
        goingLive.setTitle(stream.getTitle(), stream.getUrl());
        goingLive.setAuthor(stream.getName() + " just went live!", null, stream.getUserPictureUrl());
        goingLive.setThumbnail(stream.getUserPictureUrl());
        goingLive.addField("Game", stream.getGame(), false);

        DiscordMessages.PostTwitchLive(goingLive, stream.getName());
            
        goingLive.clear();

    }
}
