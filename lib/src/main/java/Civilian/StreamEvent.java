import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.helix.domain.User;
import com.github.twitch4j.helix.domain.Stream;
import com.github.twitch4j.events.ChannelGoLiveEvent;

import java.util.Collections;

public class StreamEvent {
    
    private String streamName;
    private String streamUserPictureUrl;
    private String streamTitle;
    private String streamGame;
    private String streamUrl;
    private User streamUser;
    private static TwitchClient bot;
    private long streamTime;

    public StreamEvent(Stream stream, TwitchClient twitchBot) {
        bot = twitchBot;
        setTime();
        setName(stream);
        setUsePictureUrl();
        setTitle(stream);
        setGame(stream);
        setUser();
    }

    public StreamEvent(ChannelGoLiveEvent event, TwitchClient twitchBot) {

        bot = twitchBot;
        setTime();
        setName(event);
        setUsePictureUrl();
        setTitle(event);
        setGame(event);
        setUser();

    }

    public StreamEvent(String check) {
        
        if (check.equals("0")) {
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

    public void setName(Stream stream) {
        streamName = stream.getUserName();
        setUrl(streamName);
    }

    public void setUsePictureUrl() {
        streamUserPictureUrl = searchUser(getName()).getProfileImageUrl();
    }

    public void setUsePictureUrl(String pictureUrl) {
        streamUserPictureUrl = pictureUrl;
        // its broken I just need to catch a null
    }

    public void setTitle(ChannelGoLiveEvent event) {
        streamTitle = event.getStream().getTitle();
    }

    public void setTitle(Stream stream) {
        streamTitle = stream.getTitle();
    }

    public void setGame(ChannelGoLiveEvent event) {
        streamGame = event.getStream().getGameName();
    }

    public void setGame(Stream stream) {
        streamGame = stream.getGameName();
    }

    public void setUrl(String streamName) {
        streamUrl = ("https://www.twitch.tv/" + streamName);
    }

    public void setUser() {
        streamUser = searchUser(getName());
    }

    private User searchUser(String streamUsername) {
        return bot.getHelix().getUsers(null, null, Collections.singletonList(streamUsername)).execute().getUsers().get(0);
    }

    public String checkString() {
        return (getName() + getTitle() + getGame() + getUser());
    }
}