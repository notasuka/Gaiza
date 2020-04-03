package commandsAdmin;

import java.awt.Color;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import management.BotInfo;
import management.Keywords;

public class AdminHelp 
{
	private String command = "ahelp";
	
	public AdminHelp(DiscordApi getApi)
	{
		DiscordApi adminHelpApi = getApi;
		
		displayAdminHelp(adminHelpApi);
		
		System.out.println("AdminHelp.java loaded!");
	}
	
	public void displayAdminHelp(DiscordApi getApi)
	{
		DiscordApi aHelpApi = getApi;
		
		aHelpApi.addMessageCreateListener(event ->
		{
			String myKey;
			String serverAddress;
			String splitMessage[] = null;
			String messageToGet = "";
			String getMessageSent = "";
			String getUsername = "";
			String getImageUrl = "";
			Icon getUserIcon = null;
			
			if (event.getMessageAuthor().isServerAdmin())
			{
				serverAddress = event.getServer().get().getIdAsString();
				myKey = Keywords.getKey(serverAddress);
				
				getMessageSent = event.getMessageContent();
				splitMessage = getMessageSent.split(" ");
				messageToGet = myKey + command;
				
				if (splitMessage[0].equalsIgnoreCase(messageToGet))
				{
					getUsername = event.getMessageAuthor().getDisplayName().toString();
					getImageUrl = event.getMessageAuthor().getAvatar().getUrl().toString();
					getUserIcon = event.getMessageAuthor().getAvatar();
					
					EmbedBuilder embed = new EmbedBuilder()
							.setAuthor(getUsername, getImageUrl, getUserIcon)
							.setColor(Color.magenta)
							
							.setTitle("Admin Help Panel")
							.setDescription("Admin Commands are currently all prefixed by: " + myKey)
							
							.addInlineField(myKey + "prefix", "Changes the current prefix of the server to whatever is set next")
							
							.setThumbnail(BotInfo.getBotImageStr())
							.setFooter(BotInfo.getBotName(), BotInfo.getBotImage())
							.setTimestampToNow();
					
					event.getChannel().sendMessage(embed);
				}
			}
			
			
			
		});
	}

}
