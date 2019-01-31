package com.github.caramelkat.commands;

import com.github.caramelkat.caramelBotCore;
import com.vdurmont.emoji.EmojiParser;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;
import org.javacord.api.entity.activity.Activity;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

import java.awt.*;

import static com.github.caramelkat.caramelBotCore.changeLog;

public class basicCommands implements MessageCreateListener {

    /*
     * This command can be used to display information about the user who used the command.
     * It's a good example for the MessageAuthor, MessageBuilder and ExceptionLogger class.
     */
    boolean engieHell = true;
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        Message message = event.getMessage();
        String engie = event.getMessage().getAuthor().getIdAsString();
        String id = "166009921324711947";
        String survival = "538113806459600917";
        String creative = "538113834104258570";
        String adventure = "538113864424751157";
        String spectator = "538113894284263434";
        if (engie.equals(id) && engieHell) {
            message.addReaction(":Engie_2:508820224263454730");
        }
            if (event.getMessageContent().equalsIgnoreCase("*engie hell true") && (event.getMessageAuthor().isBotOwner() || event.getMessageAuthor().isServerAdmin())) {
                engieHell = true;
                event.getChannel().sendMessage("Activated Engies Hell. Your welcome");
            }
            if (event.getMessageContent().equalsIgnoreCase("*engie hell false") && (event.getMessageAuthor().isBotOwner() || event.getMessageAuthor().isServerAdmin())) {
            engieHell = false;
            event.getChannel().sendMessage("Deactivated Engies Hell. Can we get a rip in the chat?");
        }
            if (event.getMessage().getContent().equalsIgnoreCase("/gamemode survival")) {
            event.getMessage().getChannel().sendMessage("Gamemode changed to Survival.");
            if (event.getMessage().getServer().toString().equalsIgnoreCase("Optional[Caramel Bot Testing]")) {
                event.getServer().ifPresent(server ->
                        event.getMessageAuthor().asUser().ifPresent(user ->
                                server.getRoleById(spectator).ifPresent(user::removeRole)));
                event.getServer().ifPresent(server ->
                        event.getMessageAuthor().asUser().ifPresent(user ->
                                server.getRoleById(survival).ifPresent(user::addRole)));
                event.getServer().ifPresent(server ->
                        event.getMessageAuthor().asUser().ifPresent(user ->
                                server.getRoleById(creative).ifPresent(user::removeRole)));
                event.getServer().ifPresent(server ->
                        event.getMessageAuthor().asUser().ifPresent(user ->
                                server.getRoleById(adventure).ifPresent(user::removeRole)));
            }
        }
            if (event.getMessage().getContent().equalsIgnoreCase("/gamemode creative")) {
                event.getMessage().getChannel().sendMessage("Gamemode changed to Creative.");
                if (event.getMessage().getServer().toString().equalsIgnoreCase("Optional[Caramel Bot Testing]")) {
                    event.getServer().ifPresent(server ->
                            event.getMessageAuthor().asUser().ifPresent(user ->
                                    server.getRoleById(spectator).ifPresent(user::removeRole)));
                    event.getServer().ifPresent(server ->
                            event.getMessageAuthor().asUser().ifPresent(user ->
                                    server.getRoleById(survival).ifPresent(user::removeRole)));
                    event.getServer().ifPresent(server ->
                            event.getMessageAuthor().asUser().ifPresent(user ->
                                    server.getRoleById(creative).ifPresent(user::addRole)));
                    event.getServer().ifPresent(server ->
                            event.getMessageAuthor().asUser().ifPresent(user ->
                                    server.getRoleById(adventure).ifPresent(user::removeRole)));
                }
            }
            if (event.getMessage().getContent().equalsIgnoreCase("/gamemode adventure")) {
                    event.getMessage().getChannel().sendMessage("Gamemode changed to Adventure.");
                    if (event.getMessage().getServer().toString().equalsIgnoreCase("Optional[Caramel Bot Testing]")) {
                        event.getServer().ifPresent(server ->
                                event.getMessageAuthor().asUser().ifPresent(user ->
                                        server.getRoleById(spectator).ifPresent(user::removeRole)));
                        event.getServer().ifPresent(server ->
                                event.getMessageAuthor().asUser().ifPresent(user ->
                                        server.getRoleById(survival).ifPresent(user::removeRole)));
                        event.getServer().ifPresent(server ->
                                event.getMessageAuthor().asUser().ifPresent(user ->
                                        server.getRoleById(creative).ifPresent(user::removeRole)));
                        event.getServer().ifPresent(server ->
                                event.getMessageAuthor().asUser().ifPresent(user ->
                                        server.getRoleById(adventure).ifPresent(user::addRole)));
                    }
                }
            if (event.getMessage().getContent().equalsIgnoreCase("/gamemode spectator")) {
                        event.getMessage().getChannel().sendMessage("Gamemode changed to Spectator.");
                        if (event.getMessage().getServer().toString().equalsIgnoreCase("Optional[Caramel Bot Testing]")) {
                            event.getServer().ifPresent(server ->
                                    event.getMessageAuthor().asUser().ifPresent(user ->
                                            server.getRoleById(spectator).ifPresent(user::addRole)));
                            event.getServer().ifPresent(server ->
                                    event.getMessageAuthor().asUser().ifPresent(user ->
                                            server.getRoleById(survival).ifPresent(user::removeRole)));
                            event.getServer().ifPresent(server ->
                                    event.getMessageAuthor().asUser().ifPresent(user ->
                                            server.getRoleById(creative).ifPresent(user::removeRole)));
                            event.getServer().ifPresent(server ->
                                    event.getMessageAuthor().asUser().ifPresent(user ->
                                            server.getRoleById(adventure).ifPresent(user::removeRole)));
                        }
                    }
            if (event.getMessage().getContent().equalsIgnoreCase("*name")) {
                        String userid = event.getMessage().getAuthor().getIdAsString();
                        event.getChannel().sendMessage(userid);
                        String nameid = caramelBotCore.api.getCachedUserById(userid).toString();
                        event.getChannel().sendMessage(nameid.substring(9,nameid.length()-1));

                    }
            if (event.getMessageContent().equalsIgnoreCase("*Help")) {
                String helpFile = "*Rank Join | Join the Leaderboard" +
                                  "\n*Rank Leave | Leaves the Leaderboard" +
                                  "\n*level | Displays current user rank information" +
                                  "\n*leaderboard | Displays the leaderboard of the current server" +
                                  "\n*leaderboard global| Displays the global leaderboard" +
                                  "\n*clevelbot {userinput} | Asks Cleverbot Something" +
                                  "\n*help | What your reading right now" +
                                  "\n*info | View information about the bot" +
                                  "\n*help image | Shows information about image search commands";
                EmbedBuilder help = new EmbedBuilder()
                        .setAuthor("Help-CaramelBot 2.0")
                        .setColor(Color.cyan)
                        .setDescription(helpFile);
                event.getChannel().sendMessage(help);
            }
            if (event.getMessageContent().equalsIgnoreCase("*info")) {
                EmbedBuilder info = new EmbedBuilder()
                        .setAuthor("Click here to invite bot to server", "https://discordapp.com/oauth2/authorize?client_id=366454911879086081&scope=bot&permissions=0", "https://cdn.discordapp.com/avatars/366454911879086081/31ace41851e7a0f93a8cfbfd67fc8fda.webp")
                        .setTitle("CaramelBot Information")
                        .setDescription("This s a instance of CaramelBot, a Discord bot developed by JadeDaBirb#6508")
                        .addInlineField("Version", "2.0.3")
                        .addField("Change Log", changeLog.replace("\n", " "))
                        .setThumbnail("https://cdn.discordapp.com/avatars/366454911879086081/31ace41851e7a0f93a8cfbfd67fc8fda.webp")
                        .addInlineField("Libraries Used:", "Javacord, Logger, michaelwflaherty's Cleverbot API, sdcf4j");
                event.getChannel().sendMessage(info);
            }
            if (event.getMessageContent().equals("*invite")) event.getChannel().sendMessage("https://discordapp.com/oauth2/authorize?client_id=366454911879086081&scope=bot&permissions=0");
    }
}