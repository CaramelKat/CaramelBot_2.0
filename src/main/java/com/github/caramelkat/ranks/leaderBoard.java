package com.github.caramelkat.ranks;

import com.github.caramelkat.caramelBotCore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
public class leaderboard {
    /*initialized variables*/
    public static int lvlupflag = 0;
    /*initialized variables*/
    private String name;
    private int level;
    private int xp;
    private String server;
    private static Random xpUP = new Random();
    public static ArrayList<leaderboard> Leaderboard = new ArrayList<leaderboard>();

    /*constructors*/
    public leaderboard(String n, int l, int x, String s) {
        this.name = n;
        this.level = l;
        this.xp = x;
        this.server = s;
    }
    public leaderboard(String n) {
        this(n, 0, 0, "");
    }
    public leaderboard(String n, int x) {
        this(n, 0, x, "");
    }
    public leaderboard(String n, String s) {this(n, 0, 0, s);}

    /*Setters & Getters*/
    public String getName() {return name;}
    public void setName(String n) {this.name = n;}
    public int getLevel() {return level;}
    public void setLevel(int l) {this.level = l;}
    public int getXP() {return xp;}
    public void setXP(int x) {this.xp = x;}
    public String getServer() {return server;}
    public void setServer(String s) {this.server = s;}

    /*Get users current rank status*/
    public String getRank() { return "Name: " + this.getName() + " Level: " + this.getLevel() + " XP: " + this.getXP(); }

    /***
     * Method to level up the specified user
     * @param n the user to level up
     */
    public static void levelUP(String n, String s) {
        for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
            if (leaderboard.Leaderboard.get(i).getName().equals(n) && leaderboard.Leaderboard.get(i).getServer().equals(s)) {
                int lvl = leaderboard.Leaderboard.get(i).getLevel();
                leaderboard.Leaderboard.get(i).setLevel(lvl+1);
                leaderboard.Leaderboard.get(i).setXP(0);
                caramelBotCore.logger.info(leaderboard.getNameFromID(Leaderboard.get(i).getName()) + " reached level " + leaderboard.Leaderboard.get(i).getLevel() + " in the server " + Leaderboard.get(i).getServer());
                lvlupflag = 1;
                leaderboard.saveAll();

            }
        }
    }

    /***
     * Method to add a random amount of xp between the numbers 15 and 25 to the specified user
     * @param n the user to add xp to
     */
    public static void xpUP(String n, String s) {
        int index = 0;
        for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
            if (leaderboard.Leaderboard.get(i).getName().equals(n) && leaderboard.Leaderboard.get(i).getServer().equals(s)) {
                leaderboard.Leaderboard.get(i).setXP(leaderboard.Leaderboard.get(i).getXP()+xpUP.nextInt(11)+15);
                leaderboard.saveAll();
                index = i;
            }
        }
        leaderboard.rankUP(leaderboard.Leaderboard.get(index).name, leaderboard.Leaderboard.get(index).server);
    }

    /***
     * Method that saves all the current information on the Leaderboard to the text file
     */
    public static void saveAll() {
        for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
            writeFile.writeString(leaderboard.Leaderboard.get(i).getName() + "|" + leaderboard.Leaderboard.get(i).getLevel() + "|" + leaderboard.Leaderboard.get(i).getXP() + "|" + leaderboard.Leaderboard.get(i).getServer());
        }writeFile.saveAndClose();
    }

    /***
     * Method to find a user on the leaderboard
     * @param n Username to search for
     * @return The current ranking information for the requested user, or reports that the user was not found
     */
    public static String findUser(String n, String s) {
        String result = "";
        for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
            if (leaderboard.Leaderboard.get(i).getName().equals(n) && leaderboard.Leaderboard.get(i).getServer().equals(s)) {
                result = leaderboard.Leaderboard.get(i).getRank();
            }
        }
        if (result.length() != 0) {
            return result;
        }
        else return "User not found";
    }

    public static int getUserIndex(String n, String s) {
        int result = -1;
        for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
            if (leaderboard.Leaderboard.get(i).getName().equals(n) && leaderboard.Leaderboard.get(i).getServer().equals(s)) {
                result = i;
            }
        }
        if (result != -1) {
            return result;
        }
        else return -1;
    }

    /***
     * Method that adds a user to the Leaderboard
     * @param n Username to add
     * @return Confirmation that the user was added, or that they were already on the leaderboard
     */
    public static String addUser(String n, String s) {
        if (n.contains("|")) return "ERROR: Username cannot contain the symbol |.";
        if (leaderboard.findUser(n,s).equals("User not found")) {
            leaderboard.Leaderboard.add(new leaderboard(n, 0, 0, s));
            leaderboard.saveAll();
            return n + " has been added to the leaderboard.";
        }else return "User " + n + " is already on the leaderboard.";
    }

    /***
     * Method that removes a user from the leaderboard
     * @param n Username to remove
     * @return Confirmation that the user was removed, or that the user could not be found
     */
    public static String removeUser(String n, String s) {
        String result = "";
        if (!leaderboard.findUser(n,s).equals("User not found")) {
            for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
                if (leaderboard.Leaderboard.get(i).getName().equals(n) && leaderboard.Leaderboard.get(i).getServer().equals(s)) {
                    leaderboard.Leaderboard.remove(i);
                    leaderboard.saveAll();
                    result = "User " + n + " has been removed from the leaderboard.";
                }
            }
        }
        else result = "User " + n + " was not found.";
        return result;
    }

    /***
     * Method that returns all the people and their current stats
     * @return String with all the leaderboard info on new lines
     * @see findUser
     */
    public static String viewAll() {
        String result = "";
        for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
            result += "Name: " + leaderboard.getNameFromID(Leaderboard.get(i).getName()) + " Level: " + Leaderboard.get(i).getLevel() + " XP: " + Leaderboard.get(i).getXP() + "\n";
        }return result;
    }

    /***
     * Method that checks if the provided user is ready to level up, and if so increases their level
     * @param n Username in the Leaderboard to check eligibility for leveling up
     */
    public static void rankUP(String n, String s) {
        int xp = 0;
        int lvl = 0;
        int index = 0;
        for (int i = 0; i < leaderboard.Leaderboard.size(); i++) {
            if (leaderboard.Leaderboard.get(i).getName().equals(n) && leaderboard.Leaderboard.get(i).getServer().equals(s)) {
                xp = leaderboard.Leaderboard.get(i).getXP();
                lvl = leaderboard.Leaderboard.get(i).getLevel();
                index = i;
            }
        }
        int requiredXP = (lvl+1)*100;
        if (xp>=requiredXP) {
            leaderboard.levelUP(leaderboard.Leaderboard.get(index).name, leaderboard.Leaderboard.get(index).server);
            leaderboard.Leaderboard.get(index).setXP(0);
        }
    }
    public static boolean checkRankUp() {
        if (lvlupflag == 1) {
            lvlupflag = 0;
            return true;
        }
        else {
            return false;
        }
    }

    public static String getNameFromID(String n) {
       return caramelBotCore.api.getCachedUserById(n).toString().substring(9, caramelBotCore.api.getCachedUserById(n).toString().length()-1);
    }
    public static String rankSort() {
        String result = "";
        Collections.sort(Leaderboard, leaderboard.rankleaderboard);
        for(leaderboard str: Leaderboard){
            result += "Name: " + leaderboard.getNameFromID(str.getName()) + " Level: " + str.getLevel() + " XP: " + str.getXP() + "\n";
        }
        return result;

    }
    public static String serverRankList(String s) {
        String result = "";
        Collections.sort(Leaderboard, leaderboard.rankleaderboard);
        for(int i = 0; i < Leaderboard.size(); i++){
            if (Leaderboard.get(i).getServer().toString().equals(s))
                result += "Name: " + leaderboard.getNameFromID(Leaderboard.get(i).getName()) + " Level: " + Leaderboard.get(i).getLevel() + " XP: " + Leaderboard.get(i).getXP() + "\n";
        }
        return result;
    }

    public static Comparator<leaderboard> rankleaderboard = new Comparator<leaderboard>() {

        public int compare(leaderboard s1, leaderboard s2) {

            int rollno1 = s1.getLevel();
            int rollno2 = s2.getLevel();

            /*For descending order*/
            return rollno2-rollno1;
        }};

    @Override
    public String toString() {
        return "Name: " + this.getName() + " Level: " + this.getLevel() + " XP: " + this.getXP();
    }



}

