package com.github.caramelkat.ranks;

import com.github.caramelkat.caramelBotCore;
import com.github.caramelkat.ranks.leaderboard;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.*;
import java.io.File;


public class levelListener implements MessageCreateListener {
    public String id;
    public String servericon;
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        event.getServer().ifPresent(server -> id = server.getIdAsString());
        Message message = event.getMessage();
        if (!leaderboard.findUser(message.getAuthor().getIdAsString(),id).equals("User not found")) {
            leaderboard.xpUP(message.getAuthor().getIdAsString(),id);
            if (leaderboard.checkRankUp()) {
                event.getChannel().sendMessage("<@!" + leaderboard.Leaderboard.get(leaderboard.getUserIndex(message.getAuthor().getIdAsString(),id)).getName() + ">" + " has just reached level " + leaderboard.Leaderboard.get(leaderboard.getUserIndex(message.getAuthor().getIdAsString(),id)).getLevel() + "!");
            }
        }
        if (message.getContent().equalsIgnoreCase("*rank join")) {
            event.getServer().ifPresent(server -> id = server.getIdAsString());
            if (leaderboard.findUser(event.getMessageAuthor().getIdAsString(),id).equals("User not found")) {
                leaderboard.addUser(event.getMessageAuthor().getIdAsString(),id);
                event.getChannel().sendMessage(event.getMessageAuthor().getDisplayName() + " has been added to the leaderboard.");
            } else event.getChannel().sendMessage("You are already part of the leaderboard.");

        }
        if (message.getContent().equalsIgnoreCase("*level")) {
            event.getServer().ifPresent(server -> id = server.getIdAsString());
            if (!leaderboard.findUser(event.getMessageAuthor().getIdAsString(),id).equals("User not found")) {
                String userID = event.getMessageAuthor().getIdAsString();
                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle(leaderboard.getNameFromID(userID))
                        .setDescription("Level: " + leaderboard.Leaderboard.get(leaderboard.getUserIndex(userID,id)).getLevel() + " XP: " + leaderboard.Leaderboard.get(leaderboard.getUserIndex(userID,id)).getXP())
                        .setAuthor("User Rank")
                        .setColor(Color.BLUE)
                        .setThumbnail(event.getMessageAuthor().getAvatar());
                event.getChannel().sendMessage(embed);
            } else
                event.getChannel().sendMessage("You are not currently part of the leaderboard, if you would like to join do *rank join.");
        }
        if (message.getContent().equalsIgnoreCase("*rank leave")) {
            event.getChannel().sendMessage("Are you sure you want to leave? Your Level and XP will be lost ***forever!*** Type *rank leave confirm to leave.");
        }
        if (message.getContent().equalsIgnoreCase("*rank leave confirm")) {
            event.getServer().ifPresent(server -> id = server.getIdAsString());
            leaderboard.removeUser(event.getMessageAuthor().getIdAsString(), id);
            event.getChannel().sendMessage(event.getMessageAuthor().getDisplayName() + " has been removed from the leaderboard.");
        }
        if (message.getContent().equalsIgnoreCase("*leaderboard")) {
            event.getServer().ifPresent(server -> id = server.getIdAsString());
           EmbedBuilder leaderbrd = new EmbedBuilder()
                   .setAuthor(event.getServer().toString().substring(9, event.getServer().toString().length()-1) + " Leaderboard")
                   .setDescription(leaderboard.serverRankList(id))
                   .setColor(Color.GREEN);
           event.getChannel().sendMessage(leaderbrd);
        }
        if (message.getContent().equalsIgnoreCase("*leaderboard global")) {
            EmbedBuilder leaderbrd = new EmbedBuilder()
                    .setAuthor("Global Leaderboard", "", "https://cdn.discordapp.com/attachments/377201253517885442/382725697329954819/112117_Caramel.png")
                    .setDescription(leaderboard.rankSort())
                    .setColor(Color.GREEN);
            event.getChannel().sendMessage(leaderbrd);
        }
        if (message.getContent().equalsIgnoreCase("*server")) {
            event.getServer().ifPresent(server -> event.getChannel().sendMessage(server.getIdAsString()));
            event.getServer().ifPresent(server -> id = server.getIdAsString());
           // event.getChannel().sendMessage("" + id.length());
        }
        if (message.getContent().equalsIgnoreCase("*rank save")) {
            if (message.getAuthor().isBotOwner()) {
                leaderboard.saveAll();
                event.getChannel().sendMessage("leaderboard has been saved");
            }
        }
    }
}
