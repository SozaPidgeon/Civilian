import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.events.ChannelGoLiveEvent;

import java.util.Collections;

public class Streamer {
    
    private String streamName;
    private String streamUserPictureUrl;
    private String streamTitle;
    private String streamGame;
    private String streamUrl;
    private User streamUser;
    private static TwitchClient bot;
    private long streamTime;

    public Streamer(ChannelGoLiveEvent event, TwitchClient twitchBot) {

        bot = twitchBot;
        setTime();
        setName(event);
        setUsePictureUrl(event);
        setTitle(event);
        setGame(event);
        setUser(event);

    }

    public Streamer(int check) {
        
        if (check == 0) {
            streamName = "";
            streamUserPictureUrl = "";
            streamTitle = "";
            streamGame = "";
            streamUrl = "";
            streamUser = null;  
            bot = null; 
            streamTime = 0;         
        }
    }

    public long getTime() {
        return streamTime;
    }

    public String getName() {
        return streamName;
    }

    public String getUserPictureUrl() {
        return streamUserPictureUrl;
    } 

    public String getTitle() {
        return streamTitle;
    } 

    public String getGame() {
        return streamGame;
    } 

    public String getUrl() {
        return streamUrl;
    } 

    public User getUser() {
        return streamUser;
    } 


    

    public void setTime() {
        streamTime = System.currentTimeMillis();
    }

    public void setName(ChannelGoLiveEvent event) {
        streamName = event.getChannel().getName();
        setUrl(streamName);
    }

    public void setUsePictureUrl(ChannelGoLiveEvent event) {
        streamUserPictureUrl = searchUser(getName()).getProfileImageUrl();
    }

    public void setUsePictureUrl(String pictureUrl) {
        streamUserPictureUrl = pictureUrl;
        // its broken I just need to catch a null
    }

    public void setTitle(ChannelGoLiveEvent event) {
        streamTitle = event.getStream().getTitle();
    }

    public void setGame(ChannelGoLiveEvent event) {
        streamGame = event.getStream().getGameName();
    }

    public void setUrl(String streamName) {
        streamUrl = ("https://www.twitch.tv/" + streamName);
    }

    public void setUser(ChannelGoLiveEvent event) {
        streamUser = searchUser(getName());
    }

    private User searchUser(String streamUsername) {
        return bot.getHelix().getUsers(null, null, Collections.singletonList(streamUsername)).execute().getUsers().get(0);
    }

    public String checkString() {
        return (getName() + getTitle() + getGame() + getUser());
    }
}
