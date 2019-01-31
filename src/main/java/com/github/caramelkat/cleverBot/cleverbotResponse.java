package com.github.caramelkat.cleverBot;

import com.michaelwflaherty.cleverbotapi.CleverBotQuery;
import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;


public class cleverbotResponse implements CommandExecutor {

    @Command(aliases = {"<@518642748127969280>", "<@!501219044699930624>", "*cleverbot", "<@!518642748127969280>", "<@501219044699930624>", "<@366454911879086081>", "<@!366454911879086081>"}, description = "Sends a response from Cleverbot", usage = "*Cleverbot [Text]")
    public String onCleverbot(String[] args) {
        CleverBotQuery bot = new CleverBotQuery("CC58r8OyXdM242APAXd1vfbFNIg", "");
        String text = args[0];
        bot.setPhrase(text);
        try {
            bot.sendRequest();
            String response = bot.getResponse();
            return response;
        } catch (java.io.IOException e) {
            String response = e.getMessage();
            return response;
        }
    }

}