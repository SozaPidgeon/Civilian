import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.EmbedBuilder;


public class DiscordMessages extends ListenerAdapter {
	
    public static final String ANSI_SOMNI = "\033[38;2;34;31;255m";
    public static final String ANSI_RESET = "\u001B[0m";

    @Override
	public void onMessageReceived(MessageReceivedEvent event) {
		JDA jda = event.getJDA();
		User author = event.getAuthor();
		Message message = event.getMessage();
        Guild guild = event.getGuild();
        MessageChannel channel = event.getChannel();
		String msg = message.getContentDisplay();
        String guildColour = "";
        String channelColour = "";
		if (author.isBot() == true) {
			return;
		} else {
			if (event.isFromType(ChannelType.TEXT)) {
				// DEFAULT FUNCTIONS
				if (event.getChannel().getId().equals("820279211704057927")) {
				} else {
                    if (guild.getName().equals("ins0mnia's World")) {
                        guildColour = ANSI_SOMNI;
                    }
					
                    System.out.println(guildColour + "[" + event.getGuild().getName() + ": " + event.getChannel().getName() + "] " + ANSI_RESET  + author.getName() + ": " + msg);
				}
				// COMMANDS
			}
		}
	}

	public static void PostTwitchLive(EmbedBuilder goingLiveEmbed, String streamName) {
        String goingLiveNow = streamName + " is going live now";
        if (streamName.equals("ins0mniasr")) {
            goingLiveNow = goingLiveNow + " <@&748788513872412702>";
        }
		Civilian.jda.getTextChannelById("731702663884046408").sendMessage(goingLiveNow).embed(goingLiveEmbed.build()).queue();
		goingLiveEmbed.clear();
	}

	public static void PostMessage(String message) {
		Civilian.jda.getTextChannelById("731702663884046408").sendMessage(message).queue();
	}
}
