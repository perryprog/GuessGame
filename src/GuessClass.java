/**
 * Created by Perry on 6/11/15.
 * A simple guessing game
 */


import java.util.InputMismatchException;
import java.util.Random;// my imports
import java.util.Scanner;

public class GuessClass {

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

    private static int number_to = 10;
    private static Boolean[] hasTyped = new Boolean[number_to];

    private static int target;// the number you will try to get to
    private static int current_guess = 1;// the  number you have just typed
    private static int rand_message;// for the random message you see
    private static int times_guessed = 0;// the number of times you guessed
    private static int not_number;// for medium mode
    private static int insane_points = 0;

    private static boolean higher;// for if the target is higher then what you guessed

    private static long start;// timer
    private static long stop;// timer
    private static long total_mill;// timer
    private static long total_sec;// timer

    private static String start_string;// for typing start at the beginning
    private static String difficulty;// if your mode is hard

    private static Scanner user_input;


    private static void makeFalse() {

        for (int i = 1; i < number_to; i++) {

            hasTyped[i] = false;

        }
    }

    private static void checkNumber() {

        boolean is_number = false;// so you don't type text
        boolean isInBounds = false;// so you don't go out of bounds 

        while (!is_number) {

            System.out.print("I am thinking of a number 1 through " + number_to + "... Can you guess it?");// first message you see
            System.out.println();// new line

            try {

                while (!isInBounds) {
                    try {

                        current_guess = user_input.nextInt();// sets your guess to what you type
                        is_number = true;
                        isInBounds = true;

                    } catch (ArrayIndexOutOfBoundsException x) {

                        if (current_guess <= 1 && current_guess >= number_to) {

                            System.out.println("Please only type numbers one through " + number_to + "!");

                        }

                    }

                }

            }
            catch (InputMismatchException x) {
                System.out.println("Please only type numbers!");
                user_input = new Scanner(System.in);// configuring imports
                is_number = false;
            }
        }

    }

    private static void checkToNumber() {

        boolean isCheckNumber = false;

        while (!isCheckNumber) {

            try {

                System.out.println("Please type a number to guess in between that and 1.");
                number_to = user_input.nextInt();
                isCheckNumber = true;

                hasTyped = new Boolean[number_to];

            } catch (InputMismatchException x) {

                System.out.println("Only Type text, please.");

            }

        }

    }

    public static void main(String[] args) {

        while (true) {// loop so you don't have to restart program

            user_input = new Scanner(System.in);// configuring imports
            Random rn = new Random();

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

            checkToNumber();

            makeFalse();// function for making boolean array false (line 11)

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

            checkNumber();

            start = System.currentTimeMillis();// "starts" the timer

            target = rn.nextInt(number_to) + 1;// the random target

            while (current_guess != target) { // loops until you guess right - skips it if you guess the right number the first timer

                not_number = rn.nextInt(number_to) + 1;// for medium difficulty

                while (not_number == current_guess || not_number == target) {// makes sure not number is not repetitive

                    not_number = rn.nextInt(number_to) + 1;// resets number if so

                }
                if (difficulty.equals("medium")) {
                    not_number--;//so we don't crash :/
                    hasTyped[not_number] = true;
                }

                rand_message = rn.nextInt(messagesHigher.length); // gets the next random message

                higher = current_guess < target; // sets higher to true if target is higher

                if (difficulty.equals("medium")) {
                    current_guess--;
                    hasTyped[current_guess] = true;
                }
                if (hasTyped[current_guess]) { // looks if you are insane (I'm not kidding)
                    System.out.println("The definition of insanity is doing the same thing, but expecting a different result.");
                    insane_points++;
                }
                current_guess++;

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

                checkNumber();

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

        }

    }

}

