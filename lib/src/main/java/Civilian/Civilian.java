import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;

public class Civilian {
	public static JDA jda;

	public static void main(String[] args) {
		System.out.println("Civilian's alarm just went off");
		try {
			System.out.println("Don't forget to brush your teeth with Discord");
			jda = DiscordManager.StartDiscordClient();
			System.out.println("Please reply to Twitch's good morning text");
			TwitchManager.StartTwitchClient();
		} catch (LoginException | InterruptedException e) {
			e.getMessage();
		}
	}
}
