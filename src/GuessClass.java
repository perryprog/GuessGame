/**
 * Created by Perry on 6/11/15.
 * A simple guessing game
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
            "Nope! It is higher then that!",
            "Wow. You do not know how guess. Higher!"};

    final private static String[] messagesLower = {
            "No way, it is lower then that!",
            "Really? It is lower then that!",
            "Nope! It is lower then that!",
            "Wow. You do not know how to guess. Lower!"};

    final private static String[] hardMessage = {// array for hard mode
            "No way!",
            "Really?",
            "Nope!",
            "Wow. Nope."};

    private static Boolean[] hasTyped = new Boolean[10];

    public static void main(String[] args) {

        while (true) {// loop so you don't have to restart program

            int target;// the number you will try to get to
            int current_guess;// the  number you have just typed
            int rand_message;// for the random message you see
            int times_guessed = 0;// the number of times you guessed
            int not_number;// for medium mode
            int insane_points = 0;

            boolean higher;// for if the target is higher then what you guessed

            long start;// timer
            long stop;// timer
            long total_mill;// timer
            long total_sec;// timer

            String start_string;// for typing start at the beginning
            String difficulty;// if your mode is hard

            Scanner user_input = new Scanner(System.in);// configuring imports
            Random rn = new Random();

            makeFalse();// function for making boolean array false (line 11)

            target = rn.nextInt(10) + 1;// the random target

            while (true) {

                System.out.println("Please type hard, medium or easy.");// user prompt
                difficulty = user_input.next();
                if (difficulty.equals("hard") || difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("easter")) {// check for if your answer is valid
                    break;
                }

            }

            if (difficulty.equals("easter")) {// I not saying. :3

                System.out.println("You win! Party!!!!!!");

                System.exit(0);

            }

            while (true) {
                System.out.println("Please type start to begin, or stop to end the program!");// second user prompt
                start_string = user_input.next();
                if (start_string.equals("start") || start_string.equals("stop")) {// check for valid answer
                    break;
                }
            }

            if (start_string.equals("stop")) {

                System.out.println("Ending...");

                System.exit(0);

            }

            System.out.print("I am thinking of a number 1 through 10... Can you guess it?");// first message you see
            System.out.println();// new line

            current_guess = user_input.nextInt();// sets your guess to what you type

            if (current_guess >= 1 && current_guess <= 10) {

                start = System.currentTimeMillis();// "starts" the timer

                while (current_guess != target) { // loops until you guess right - skips it if you guess the right number the first timer

                    not_number = rn.nextInt(10) + 1;// for medium difficulty

                    while (not_number == current_guess || not_number == target) {// makes sure not number is not repetitive

                        not_number = rn.nextInt(10) + 1;// resets number if so

                    }
                    not_number--;//so we don't crash -_-
                    hasTyped[not_number] = true;


                    rand_message = rn.nextInt(messagesHigher.length); // gets the next random message

                    higher = current_guess < target; // sets higher to true if target is higher

                    current_guess--;
                    if (hasTyped[current_guess]) { // looks if you are insane (I'm not kidding)
                        System.out.println("The definition of insanity is doing the same thing, but expecting a different result.");
                        insane_points++;
                    }
                    if (difficulty.equals("easy")) {// the messages
                        if (higher) {

                            System.out.println(messagesHigher[rand_message]);

                        } else {

                            System.out.println(messagesLower[rand_message]);

                        }
                    } else {
                        if (difficulty.equals("hard")) {

                            System.out.println(hardMessage[rand_message]);

                        } else {

                            hasTyped[not_number] = true;
                            not_number++;
                            System.out.println(hardMessage[rand_message] + " But is also not " + not_number + ".");

                        }

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


                if (times_guessed == 1) {// if you had 1 guess you get a special message

                    System.out.println("Nice! You got it! That took you " + total_mill + " milliseconds, or " + total_sec + " seconds! Also, you guessed on your first try! In addition you had " + insane_points + " insane points!");// first try message

                } else {

                    times_guessed++;

                    System.out.println("Nice! You got it! That took you " + total_mill + " milliseconds, or " + total_sec + " seconds! Also, you guessed " + times_guessed + " times! In addition you had " + insane_points + " insane points!");// +1 try else message

                }

            } else {

                System.out.println("Please only use numbers 1 through 10!");

            }

        }

    }

}