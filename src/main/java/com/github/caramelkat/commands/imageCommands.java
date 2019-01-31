package com.github.caramelkat.commands;
/*
 * @author:         Carlo Fontanos
 * @author_url:     carlofontanos.com
 *
 * A simple code snippet for parsing JSON data from a URL
 */
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class imageCommands implements MessageCreateListener {

    String blacklist = "-scat%20-cub%20";


    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase("*help images") || event.getMessageContent().equalsIgnoreCase("*help image") || event.getMessageContent().equalsIgnoreCase("*help e621") || event.getMessageContent().equalsIgnoreCase("*help e621")) {
            String helpFile = "*e621 | Returns result from e621.net from provided tags" +
                    "\n*e6 | Returns result from e621.net from provided tags" +
                    "\n*e926 | Returns result from e926.net from provided tags" +
                    "\n*e9 | Returns result from e926.net from provided tags";
            EmbedBuilder help = new EmbedBuilder()
                    .setAuthor("Help-Image Commands")
                    .setColor(Color.CYAN)
                    .setDescription(helpFile);
            event.getChannel().sendMessage(help);
        }
        if (event.getMessageContent().contains("*e621 ")) {
            String tags = event.getMessageContent().substring(6,event.getMessageContent().length()).replace(" ","%20");
            try {
                String url = "https://e621.net/post/index.json?password=d735eee86bdde6fe2dc23b63a4714890&limit=1&tags=order:random%20" + blacklist + tags;
                URL oracle = new URL(url); // URL to Parse
                URLConnection yc = oracle.openConnection();
                yc.addRequestProperty("User-Agent", "CaramelBot Javacord based discord bot created by JadeDaBirb");
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String line = in.readLine();
                String inputLine;
                Boolean completed = false;
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".png") + 4);
                    EmbedBuilder e621 = new EmbedBuilder()
                            .setAuthor("e621 result for " + tags, message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                            .setImage(message)
                            .setColor(Color.blue);
                    event.getChannel().sendMessage(e621);
                    completed = true;
                } catch (StringIndexOutOfBoundsException e) {
                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpeg") + 5);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e621 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpg") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e621 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".gif") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e621 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                // if (line.substring(line.indexOf("file_url")+11, line.indexOf(".png")+4).isEmpty())
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getMessageContent().contains("*e6 ")) {
            String tags = event.getMessageContent().substring(4,event.getMessageContent().length()).replace(" ","%20");

            try {
                String url = "https://e621.net/post/index.json?password=d735eee86bdde6fe2dc23b63a4714890&limit=1&tags=order:random%20" + blacklist + tags;
                URL oracle = new URL(url); // URL to Parse
                //%20order:random
                URLConnection yc = oracle.openConnection();
                yc.addRequestProperty("User-Agent", "CaramelBot Javacord based discord bot created by JadeDaBirb");
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String line = in.readLine();
                String inputLine;
                Boolean completed = false;
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".png") + 4);
                    EmbedBuilder e621 = new EmbedBuilder()
                            .setAuthor("e621 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                            .setImage(message)
                            .setColor(Color.blue);
                    event.getChannel().sendMessage(e621);
                    completed = true;
                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpeg") + 5);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e621 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpg") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e621 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".gif") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e621 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                // if (line.substring(line.indexOf("file_url")+11, line.indexOf(".png")+4).isEmpty())
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getMessageContent().contains("*e926 ")) {
            String tags = event.getMessageContent().substring(6,event.getMessageContent().length()).replace(" ","%20");

            try {
                String url = "https://e926.net/post/index.json?password=d735eee86bdde6fe2dc23b63a4714890&limit=1&tags=order:random%20" + tags;
                URL oracle = new URL(url); // URL to Parse
                //%20order:random
                URLConnection yc = oracle.openConnection();
                yc.addRequestProperty("User-Agent", "CaramelBot Javacord based discord bot created by JadeDaBirb");
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String line = in.readLine();
                String inputLine;
                Boolean completed = false;
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".png") + 4);
                    EmbedBuilder e621 = new EmbedBuilder()
                            .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                            .setImage(message)
                            .setColor(Color.blue);
                    event.getChannel().sendMessage(e621);
                    completed = true;
                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpeg") + 5);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpg") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".gif") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                // if (line.substring(line.indexOf("file_url")+11, line.indexOf(".png")+4).isEmpty())
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (event.getMessageContent().contains("*e9 ")) {
            String tags = event.getMessageContent().substring(4,event.getMessageContent().length()).replace(" ","%20");

            try {
                String url = "https://e926.net/post/index.json?password=d735eee86bdde6fe2dc23b63a4714890&limit=1&tags=order:random%20" + tags;
                URL oracle = new URL(url); // URL to Parse
                //%20order:random
                URLConnection yc = oracle.openConnection();
                yc.addRequestProperty("User-Agent", "CaramelBot Javacord based discord bot created by JadeDaBirb");
                BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
                String line = in.readLine();
                String inputLine;
                Boolean completed = false;
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".png") + 4);
                    EmbedBuilder e621 = new EmbedBuilder()
                            .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                            .setImage(message)
                            .setColor(Color.blue);
                    event.getChannel().sendMessage(e621);
                    completed = true;
                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpeg") + 5);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".jpg") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                try {
                    String message = line.substring(line.indexOf("file_url") + 11, line.indexOf(".gif") + 4);
                    if (!completed) {
                        EmbedBuilder e621 = new EmbedBuilder()
                                .setAuthor("e926 result for " + tags.replace("%20",", "), message, "https://ih0.redbubble.net/image.392550632.6599/sticker,375x360-bg,ffffff.u4.png")
                                .setImage(message)
                                .setColor(Color.blue);
                        event.getChannel().sendMessage(e621);
                        completed = true;
                    }

                } catch (StringIndexOutOfBoundsException e) {

                }
                // if (line.substring(line.indexOf("file_url")+11, line.indexOf(".png")+4).isEmpty())
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}