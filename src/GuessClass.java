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

    private static int target = -1;// the number you will try to get to
    private static int current_guess = 1;// the  number you have just typed
    private static int rand_message;// for the random message you see
    private static int times_guessed = 0;// the number of times you guessed
    private static int not_number;// for medium mode
    private static int insane_points = 0;

    private static boolean higher;// for if the target is higher then what you guessed
    private static boolean sanityWork;
    private static boolean easterMode;

    private static long start;// timer
    private static long stop;// timer
    private static long total_mill;// timer
    private static long total_sec;// timer

    private static String start_string;// for typing start at the beginning
    private static String difficulty;// if your mode is hard

    private static String cake1;// not saying
    private static String cake2;
    private static String cake3;
    private static String cake4;
    private static String cake5;

    private static Scanner user_input;


    private static void makeFalse() {// this is for making hasTyped all false

        for (int i = 0; i < number_to; i++) {

            hasTyped[i] = false;

        }
    }

    private static void checkNumber() {// this runs the messages you see

        boolean is_number = false;// so you don't type text

        while (!is_number) {

            System.out.print("I am thinking of a number 1 through " + number_to + "... Can you guess it?");// first message you see
            System.out.println();// new line


            while (!is_number) {
                try {// in case you don't type a number

                    current_guess = user_input.nextInt();// sets your guess to what you type
                    is_number = true;
                    sanityWork = true;

                    higher = current_guess < target; // sets higher to true if target is higher then what you guessed

                    if (current_guess != target) {
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

                    }

                    times_guessed++;// one up to how many guesses you have

                } catch (InputMismatchException x) {
                    System.out.println("Please only type numbers!");
                    user_input = new Scanner(System.in);// reconfiguring imports
                    is_number = false;

                }

            }

        }

    }

    private static void checkTargetNumber() {// this is for typing the the in between and checking it

        boolean isCheckNumber = false;// because this can't go out of bounds we only need to make sure it is a number

        while (!isCheckNumber) {// case in point


            try {// again, to make sure you only type numbers

                System.out.println("Please type a number to guess in between that and 1.");
                number_to = user_input.nextInt();
                isCheckNumber = true;

                hasTyped = new Boolean[number_to];

            } catch (InputMismatchException x) {

                System.out.println("Only Type text, please.");

                user_input = new Scanner(System.in);// reconfiguring imports
                isCheckNumber = false;

            }

        }

    }

    private static void sanityCheck() {// much name

        boolean inBounds = false;

        while (!inBounds) {

            try {


                if (hasTyped[current_guess-1]) { // looks if you are insane (I'm not kidding)
                    System.out.println("The definition of insanity is doing the same thing, but expecting a different result.");
                    insane_points++;
                    inBounds = true;
                    sanityWork = true;
                }
                else break;
            } catch (ArrayIndexOutOfBoundsException x) {// this is what makes sure you don't go out of bounds when typing your guess
                if (current_guess <= 1 || current_guess >= number_to) {// not sure I need this if -_-

                    System.out.println("Please only type numbers 1 through " + number_to + "!");

                    inBounds = true;
                    sanityWork = false;
                }
            }
        }
    }

    private static void stopProgram() {

        if (start_string.equals("stop") && !easterMode) {

            System.out.println("Ending...");

            System.exit(0);

        }
        else {

            System.out.println("Clearing cpu, hard drive, and memory...");
            System.exit(0);

        }

    }

    private static void cakeTest() {
        if (start_string.equals("The")) {

            System.out.println("Then?");
            cake1 = user_input.next();

            if (cake1.equals("cake")) {

                System.out.println("Then?");
                cake2 = user_input.next();

                if (cake2.equals("is")) {

                    System.out.println("Then?");
                    cake3 = user_input.next();

                    if (cake3.equals("not")) {

                        System.out.println("Then?");
                        cake4 = user_input.next();

                        if (cake4.equals("a")) {

                            System.out.println("Then?");
                            cake5 = user_input.next();

                            if (cake5.equals("lie.")) {

                                System.out.println("Nope! It is a lie. And so are you.");
                                System.exit(0);

                            }

                            else {
                                System.out.println("Nope!");
                            }
                        }

                        else {
                            System.out.println("Nope!");
                        }
                    }

                    else {
                        System.out.println("Nope!");
                    }
                }

                else {
                    System.out.println("Nope!");
                }
            }

            else {
                System.out.println("Nope!");
            }

        }
    }


    public static void main(String[] args) {

        while (!easterMode) {// loop so you don't have to restart program

            user_input = new Scanner(System.in);// configuring imports
            Random rn = new Random();

            while (true) {

                System.out.println("Please type hard, medium or easy.");// user prompt
                difficulty = user_input.next();
                if (difficulty.equals("hard") || difficulty.equals("easy") || difficulty.equals("medium") || difficulty.equals("easter")) {// check for if your answer is valid
                    break;

                }

            }

            if (difficulty.equals("easter")) {// I not saying :3

                System.out.println("You win! Party!!!!!! Now go on with your life.");

                easterMode = true;

            }

            checkTargetNumber();
            makeFalse();// function for making boolean array false (line 11)

            while (true) {
                System.out.println("Please type start to begin, or stop to end the program!");// second user prompt
                start_string = user_input.next();
                if (start_string.equals("start") || start_string.equals("stop") || start_string.equals("The")) {// check for valid answer
                    break;
                }
            }

            cakeTest();

            stopProgram();// stops program if you type stop


            start = System.currentTimeMillis();// "starts" the timer
            target = rn.nextInt(number_to) + 1;// the random target

            while (current_guess != target) { // loops until you guess right - skips it if you guess the right number the first timer

                not_number = rn.nextInt(number_to) + 1;// for medium difficulty
                rand_message = rn.nextInt(messagesHigher.length); // gets the next random message

                sanityWork = false;
                while (!sanityWork) {// this does your number guessing and checking :D

                    checkNumber();
                    sanityCheck();

                }

                if (current_guess != target) {// runs if you did not guess right

                    while (not_number == current_guess || not_number == target) {// makes sure not number is not repetitive

                        not_number = rn.nextInt(number_to) + 1;// resets number if so

                    }

                    if (difficulty.equals("medium")) {
                        not_number--;// so we don't crash
                        hasTyped[not_number] = true;
                    }

                    hasTyped[current_guess-1] = true;

                }
            }

            stop = System.currentTimeMillis();// "stops" the timer

            total_mill = stop - start;// figures out the total time it took to run
            total_sec = total_mill / 1000;// does above, in seconds


            if (times_guessed == 1 && !easterMode) {// if you had 1 guess you get a special message

                System.out.println("Nice! You got it! That took you " + total_mill + " milliseconds, or " + total_sec + " seconds! Also, you guessed on your first try! In addition you had " + insane_points + " insane points!");// first try message

            } else if (!easterMode){

                System.out.println("Nice! You got it! That took you " + total_mill + " milliseconds, or " + total_sec + " seconds! Also, you guessed " + times_guessed + " times! In addition you had " + insane_points + " insane points!");// +1 try else message

            }
            else {
                System.out.println("You lost. Much fail. Doge does not like you now.");
            }

        }

    }

}

