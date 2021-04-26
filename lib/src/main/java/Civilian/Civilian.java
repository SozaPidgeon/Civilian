import net.dv8tion.jda.api.JDA;

import javax.security.auth.login.LoginException;

public class Civilian {
	public static JDA jda;

	public static void main(String[] args) {
		System.out.println("");
		try {
			System.out.println("");
			jda = DiscordManager.StartDiscordClient();
			System.out.println("");
			TwitchManager.StartTwitchClient();
			System.out.println("Bot Ready...\n");
		} catch (LoginException | InterruptedException e) {
			e.getMessage();
		}
	}
}
