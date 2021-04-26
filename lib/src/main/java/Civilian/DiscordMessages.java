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
		String[] msgArgs = msg.split(" ", 2);
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
					
                    // System.out.println(guildColour + "[" + event.getGuild().getName() + ": " + event.getChannel().getName() + "] " + ANSI_RESET  + author.getName() + ": " + msg);
				}

				// COMMANDS
				if (msgArgs[0].equals("!help")) {
                    channel.sendMessage("This is a help command").queue();
                
				} else if (msgArgs[0].equals("!stream")) {
					String[] msgText = msgArgs[1].split(" ", 2);
					if (msgText.length > 1) {
						message.delete().queue();
						TwitchManager.searchStream(msgText[0].toLowerCase(), msgText[1], event); 
					}
					
				// channel.sendMessage("Stream command used").queue();
				} else if (msgArgs[0].equals("!livestream")) {
					String[] msgText = msgArgs[1].split(" ", 2);
					if (msgText.length > 1) {
						message.delete().queue();
						TwitchManager.searchStream(msgText[0].toLowerCase(), msgText[1], event);
					}
				}
			}
		}
	}

	public static void postTwitchSearch(EmbedBuilder streamEmbed, String searchText, MessageReceivedEvent searchEvent) {
		String goingLiveChannel = searchEvent.getChannel().getId();
        
		Civilian.jda.getTextChannelById(goingLiveChannel).sendMessage(searchText).embed(streamEmbed.build()).queue();
		streamEmbed.clear();
	}

    public static void postTwitchLive(EmbedBuilder goingLiveEmbed, String streamName, String goingLiveText, Server server) {
		
		if (streamName.equals(server.streamerPing)) {
            goingLiveText = goingLiveText + " <@&" + server.goingLiveRole + ">";
        }

        Civilian.jda.getTextChannelById(server.goingLiveChannel).sendMessage(goingLiveText).embed(goingLiveEmbed.build()).queue();
		goingLiveEmbed.clear();
	}




	// public static void PostTwitchLive(EmbedBuilder goingLiveEmbed, String streamName) {
    //     String goingLiveNow = streamName + " is going live now";
    //     if (streamName.toLowerCase().equals("ins0mniasr")) {
    //         goingLiveNow = goingLiveNow + " <@&748788513872412702>";
    //     }
		
	// 	Civilian.jda.getTextChannelById("731702663884046408").sendMessage(goingLiveNow).embed(goingLiveEmbed.build()).queue();
    //     // Civilian.jda.getTextChannelById("788926831948464148").sendMessage(goingLiveNow).embed(goingLiveEmbed.build()).queue();
	// 	goingLiveEmbed.clear();
	// }
}
