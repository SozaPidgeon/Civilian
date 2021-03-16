import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.*;

import javax.security.auth.login.LoginException;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class DiscordManager {
	private static final String token = "ODAxNDA0NzgxMzQ0MTk0NTYx.YAgMXQ.iC_wV2yonJGRpcuqIBagWUdDfl0";
	

	public static JDA StartDiscordClient() throws InterruptedException, LoginException {
		JDA jda = BotBuilder();
		jda.awaitReady();
		return jda;
	}

	public static JDA BotBuilder() throws LoginException {
		JDABuilder builder = JDABuilder.createDefault(token);
		builder.setActivity(Activity.watching("a cutie \uD83D\uDC40"));
		builder.setStatus(OnlineStatus.ONLINE);
		builder.addEventListeners(new DiscordMessages());
		return builder.build();
	}
}
