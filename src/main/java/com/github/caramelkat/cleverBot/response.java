package com.github.caramelkat.cleverBot;


import com.michaelwflaherty.cleverbotapi.CleverBotQuery;
import java.io.IOException;
import java.util.Scanner;

public class response

{

    public static void main(String[] args)

    {

        Scanner keyboard;

        CleverBotQuery botQuery = new CleverBotQuery("CC58r8OyXdM242APAXd1vfbFNIg", "");

        String input;

        boolean done;



        keyboard = new Scanner(System.in);



        do

        {

            System.out.print("Enter your message: ");

            input = keyboard.nextLine();

            done = input.equals("done");



            if (!done)

            {

                botQuery.setPhrase(input);

                try

                {

                    botQuery.sendRequest();

                    System.out.println("BOT: " + botQuery.getResponse());

                }

                catch (IOException e)

                {

                    System.out.println(e.getLocalizedMessage());

                }

            }

        } while(!done);



    }

}