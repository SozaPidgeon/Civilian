import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.events.ChannelGoLiveEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;
import java.util.Locale;

public class TwitchManager {
    public static final String twitchId = "32p3en14qg28y227gim6phritbu1la";
    public static final String twitchSecret = "q6z3biu1wbo26lgrczly90thm1ht2g";
    public static final String targetStreamer = "ins0mniasr";
    public static final Color SomniBlue = new Color(0, 102, 255);

    public static void StartTwitchClient() {
        TwitchClient twitchBot = TwitchClientBuilder.builder()
                .withEnableHelix(true)
                .withEnableKraken(true)
                .withClientId(twitchId)
                .withClientSecret(twitchSecret)
                .build();

        twitchBot.getClientHelper().enableStreamEventListener(targetStreamer);
        System.out.println("StreamEventListener active on: " + targetStreamer);
        twitchBot.getEventManager().onEvent(ChannelGoLiveEvent.class, event -> {
//		DiscordMessages.PostMessage("Test for bot");
        	EmbedBuilder goingLive = new EmbedBuilder();
        	goingLive.setColor(SomniBlue);

//            embed=discord.Embed(title=Hollow Knight Al2ba%  and then Nice% Speedruns, url=https://www.twitch.tv/ins0mniasr, color=0x221fff)
//            embed.set_author(name=ins0mniaSR, url=https://www.twitch.tv/ins0mniasr, icon_url=https://images-ext-2.discordapp.net/external/WsMRlkdt8n8CMFR2E8tH-YAt9SNMo5DnDjrIHH7bKpI/https/static-cdn.jtvnw.net/jtv_user_pictures/1e765843-8642-41ef-b62e-19fec401eb46-profile_image-300x300.png)
//            embed.set_thumbnail(url=https://images-ext-2.discordapp.net/external/WsMRlkdt8n8CMFR2E8tH-YAt9SNMo5DnDjrIHH7bKpI/https/static-cdn.jtvnw.net/jtv_user_pictures/1e765843-8642-41ef-b62e-19fec401eb46-profile_image-300x300.png?width=120&height=120)
//            embed.add_field(name=Game, value=Hollow Knight, inline=True)
//            await ctx.send(embed=embed)




            goingLive.setTitle(event.getStream().getTitle(), "https://www.twitch.tv/ins0mniasr");
            goingLive.setAuthor("ins0mniaSR just went live!", "https://images-ext-2.discordapp.net/external/WsMRlkdt8n8CMFR2E8tH-YAt9SNMo5DnDjrIHH7bKpI/https/static-cdn.jtvnw.net/jtv_user_pictures/1e765843-8642-41ef-b62e-19fec401eb46-profile_image-300x300.png");
            goingLive.setThumbnail("https://images-ext-2.discordapp.net/external/WsMRlkdt8n8CMFR2E8tH-YAt9SNMo5DnDjrIHH7bKpI/https/static-cdn.jtvnw.net/jtv_user_pictures/1e765843-8642-41ef-b62e-19fec401eb46-profile_image-300x300.png");
            goingLive.addField("Game", event.getStream().getGameName(), false);

            DiscordMessages.PostTwitchLive(goingLive);
            goingLive.clear();
        });
    }
}
