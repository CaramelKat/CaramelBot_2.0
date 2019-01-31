package com.github.caramelkat;

import com.github.caramelkat.cleverBot.cleverbotResponse;
import com.github.caramelkat.commands.basicCommands;
import com.github.caramelkat.commands.imageCommands;
import com.github.caramelkat.ranks.levelListener;
import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import com.github.caramelkat.commands.userInfoCommand;
import com.github.caramelkat.ranks.leaderboard;
import com.github.caramelkat.ranks.writeFile;

public class caramelBotCore {
    public static String changeLog = "*Fixed the leaderboard doubling peoples levels on a level up event. \n *Added a toggle event for the engie hell command. \n *Updated into command to contain version number and changelog. \n *Added image commands.";





    public static Logger logger = LogManager.getLogger(caramelBotCore.class);
             /*real caramel bot*/
    public static DiscordApi api = new DiscordApiBuilder().setToken("MzY2NDU0OTExODc5MDg2MDgx.DuQhhQ.wKxG96cEEKPknmdRJsytv0PUqgw").login().join();
             /*test server only bot*/
//    public static DiscordApi api = new DiscordApiBuilder().setToken("NTE4NjQyNzQ4MTI3OTY5Mjgw.DuTvVA.Gms6TzN69g0SMDMb7dKDDRqUwoM").login().join();
            /*Caramel testing*/
//    public static DiscordApi api = new DiscordApiBuilder().setToken("NTAxMjE5MDQ0Njk5OTMwNjI0.DuQhOQ.2I8jRa1PTrNMNwUNqx3ReE7JPUI").login().join();

     public static void main(String[] args) {
        // Enable debugging, if no slf4j logger was found
         System.out.println("Version 2.0.3");
         System.out.println("Changelog: \n" + changeLog);
        FallbackLoggerConfiguration.setDebug(true);
        // Print the invite url of the bot
        logger.info("You can invite me by using the following url: " + api.createBotInvite());
         String testing = writeFile.readString();
         int pipeIndex = testing.indexOf("|");
         int i = 1;
         while (pipeIndex != -1) {
             String userName = testing.substring(0, testing.indexOf("|"));
             testing = testing.substring(pipeIndex + 1, testing.length());

             pipeIndex = testing.indexOf("|");
             if (pipeIndex == -1) break;
             String userLvl = testing.substring(0, pipeIndex);
             int lvl = Integer.valueOf(userLvl);
             testing = testing.substring(pipeIndex + 1, testing.length());

             pipeIndex = testing.indexOf("|");
             if (pipeIndex == -1) break;
             String userXP = testing.substring(0, pipeIndex);
             int xp = Integer.valueOf(userXP);
             testing = testing.substring(pipeIndex + 1, testing.length());

             pipeIndex = testing.indexOf("|");
             if (pipeIndex == -1) break;
             String userServer = testing.substring(0, pipeIndex);

             leaderboard.Leaderboard.add(new leaderboard(userName, lvl, xp, userServer));

             i++;
             testing = testing.substring(pipeIndex + 1, testing.length());
             pipeIndex = testing.indexOf("|");
         }
        logger.info(i-1 + " Users have been indexed to the leaderboard.");

        // Add listeners
        CommandHandler handler = new JavacordHandler(api);
        handler.registerCommand(new cleverbotResponse());
        api.addMessageCreateListener(new userInfoCommand());
        api.addMessageCreateListener(new basicCommands());
        api.addMessageCreateListener(new levelListener());
        api.addMessageCreateListener(new imageCommands());

        //api.addMessageCreateListener(new levelListener());
        // Log a message, if the bot joined or left a server
        api.addServerJoinListener(event -> logger.info("Joined server " + event.getServer().getName()));
        api.addServerLeaveListener(event -> logger.info("Left server " + event.getServer().getName()));

    }
}