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
    public static final Color SomniBlue = new Color(0, 102, 255);

    public static void StartTwitchClient() {
        TwitchClient twitchBot = TwitchClientBuilder.builder()
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withClientId(twitchId)
                .withClientSecret(twitchSecret)
                .build();

        twitchBot.getClientHelper().enableStreamEventListener("ins0mniasr");
        twitchBot.getClientHelper().enableStreamEventListener("sozapidgeon");
        System.out.println("StreamEventListener active");
        twitchBot.getEventManager().onEvent(ChannelGoLiveEvent.class, event -> {
            
            String streamName = event.getChannel().getName();
            String streamUrl = ("https://www.twitch.tv/" + streamName);
            User streamUser = twitchBot.getHelix().getUsers(null, null, Collections.singletonList(streamName)).execute().getUsers().get(0);
            String streamUserPictureUrl = streamUser.getProfileImageUrl();
            String streamTitle = event.getStream().getTitle();
            String streamGame = event.getStream().getGameName();

            System.out.println("Stream started for " + streamName + ": " + streamTitle);

            EmbedBuilder goingLive = new EmbedBuilder();
        	
            goingLive.setColor(SomniBlue);
            goingLive.setTitle(streamTitle, streamUrl);
            goingLive.setAuthor(streamName + " just went live!", null, streamUserPictureUrl);
            goingLive.setThumbnail(streamUserPictureUrl);
            goingLive.addField("Game", streamGame, false);

            DiscordMessages.PostTwitchLive(goingLive, streamName);
            
            goingLive.clear();

        });
    }
}
