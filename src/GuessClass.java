/**
 * Created by Perry on 6/11/15.
 */

import java.util.Random;// my imports
import java.util.Scanner;

public class GuessClass {

    private static void makeFalse() {

        for (int i = 0; i < 10; i++) {

            hasTyped[i] = false;

        }
    }
    
    final private static String[] messagesHigher = { // 2 arrays, one if it's lower, one if it's higher
            "No way, it is higher then that!",
            "Really? It is higher then that!",
            "Nope! It is higher then that!"};

    final private static String[] messagesLower = {
            "No way, it is lower then that!",
            "Really? It is lower then that!",
            "Nope! It is lower then that!"};

    final private static String[] hardMessage = {
            "No way!",
            "Really?",
            "Nope!"};

    private static Boolean[] hasTyped = new Boolean[10];



    public static void main(String[] args) {

        while (true) {

            int target = 0;// the number you will try to get to
            int current_guess = 0;// the  number you have just typed
            int rand_message = 0;// for the random message you see
            int times_guessed = 0;// the number of times you guessed

            boolean higher = false;// for if the target is higher then what you guessed

            long start = 0;// timer
            long stop = 0;// timer
            long total_mill = 0;// timer
            long total_sec = 0;// timer

            String start_string = "null";// for typing start at the beginning
            String difficulty = "null";// if your mode is hard

            Scanner user_input = new Scanner(System.in);// configuring imports
            Random rn = new Random();

            makeFalse();

            target = rn.nextInt(10) + 1;// the random target


            while (true) {

                System.out.println("Please type hard for hard or easy for easy");
                difficulty = user_input.next();
                if (difficulty.equals("hard") || difficulty.equals("easy")) {
                    break;
                }

            }
            while (true) {
                System.out.println("Please type \"start\" to begin!");
                start_string = user_input.next();
                if (start_string.equals("start")) {
                    break;
                }
            }

            System.out.print("I am thinking of a number 1 through 10... Can you guess it?");// first message you see
            System.out.println();// new line

            current_guess = user_input.nextInt();// sets your guess to what you type


            if (current_guess >= 1 && current_guess <= 10) {

                start = System.currentTimeMillis();// "starts" the timer

                while (current_guess != target) { // loops until you guess right - skips it if you guess the right number the first timer

                    rand_message = rn.nextInt(messagesHigher.length); // gets the next random message

                    higher = current_guess < target; // sets higher to true if target is higher

                    if (hasTyped[current_guess]) { // looks if you are insane (I'm not kidding)
                        System.out.println("The definition of insanity is doing the same thing, but expecting a different result.");
                    }
                    if (difficulty.equals("easy")) {
                        if (higher) {

                            System.out.println(messagesHigher[rand_message]);

                        } else {

                            System.out.println(messagesLower[rand_message]);

                        }
                    }
                    else {
                        System.out.println(hardMessage[rand_message]);
                    }
                    hasTyped[current_guess] = true;
                    current_guess = user_input.nextInt();// lets you type the next guess


                }
                if (current_guess >= 1 && current_guess <= 10) {
                    System.out.println();
                }

                times_guessed++;// one up to how many guesses you have

                stop = System.currentTimeMillis();// "stops" the timer

                total_mill = stop - start;// figures out the total time it took to run
                total_sec = total_mill / 1000;// does above, in seconds


                if (times_guessed + 1 == 1) {// if you had 1 guess you get a special message

                    System.out.println("Nice! You got it! That took you " + total_mill + " milliseconds, or " + total_sec + " seconds! Also, you guessed on your first try!");// first try message

                } else {

                    times_guessed++;

                    System.out.println("Nice! You got it! That took you " + total_mill + " milliseconds, or " + total_sec + " seconds! Also, you guessed " + times_guessed + " times!");// +1 try else message

                }

            }

            else {
                System.out.println("Please only use numbers 1 through 10!");
            }

        }

    }

}