import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.*;
import net.dv8tion.jda.api.EmbedBuilder;


public class DiscordMessages extends ListenerAdapter {
	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		JDA jda = event.getJDA();
		User author = event.getAuthor();
		Message message = event.getMessage();

		String msg = message.getContentDisplay();

		if (author.isBot() == true) {
			return;
		} else {
			if (event.isFromType(ChannelType.TEXT)) {
				// DEFAULT FUNCTIONS
				if (event.getChannel().getId().equals("820279211704057927")) {
				} else {
					System.out.println("[" + event.getGuild().getName() + ": " + event.getChannel().getName() + "] " + author.getName() + ": " + msg);
				}
				// COMMANDS
			}
		}
	}

	public static void PostTwitchLive(EmbedBuilder embed) {
		Civilian.jda.getTextChannelById("731702663884046408").sendMessage("Ins0mnia is going live now <@&748788513872412702>").queue();
		Civilian.jda.getTextChannelById("731702663884046408").sendMessage(embed.build()).queue();
		embed.clear();
	}

	public static void PostMessage(String message) {
		Civilian.jda.getTextChannelById("731702663884046408").sendMessage(message).queue();
	}
}
