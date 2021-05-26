package com.Mastermind;

import java.util.Scanner;
import java.util.ArrayList;

/*
 * Developed and programmed by Clement Boiteux
 * Project started on 2/28/2021
 * Future updates: Implement a GUI, new gamemodes and features, audio, settings, com.src.game.User Login
 */

public class Mastermind {

    private ArrayList<User> users = new ArrayList();
    private Token[] tokens = new Token[4];
    private Token[] guess = new Token[4];
    private String[] availableColors = {"Red", "Blue", "Green", "Yellow", "Black", "White"};
    private String[] indicators = new String[4];
    private int tries = 0;
    Scanner scanner = new Scanner(System.in);

    public void chooseRandomColors() {
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = new Token(availableColors[(int) (Math.random() * availableColors.length)], i);
        }
    }

    public void revealColors() {
        for (int i = 0; i < tokens.length; i++) {
            System.out.println(tokens[i]);
        } // Cheat code
        System.out.println();
    }

    public void resetHighScores() {
        for (int i = 0; i < users.size(); i++) {
            users.remove(i);
            i--;
        }
        if (users.isEmpty()) {
            System.out.println("High scores list has been reset!\n");
        }
    }

    public void selectColors() {
        showAvailableColors();
        System.out.println("Please choose your 4 colors: ");
        for (int i = 0; i < tokens.length; i++) {
            System.out.println("Color #" + (i + 1) + ":");
            String color = scanner.next();
            if (color.equalsIgnoreCase("red")) {
                tokens[i] = new Token("Red", i);
            } else if (color.equalsIgnoreCase("blue")) {
                tokens[i] = new Token("Blue", i);
            } else if (color.equalsIgnoreCase("yellow")) {
                tokens[i] = new Token("Yellow", i);
            } else if (color.equalsIgnoreCase("black")) {
                tokens[i] = new Token("Black", i);
            } else if (color.equalsIgnoreCase("white")) {
                tokens[i] = new Token("White", i);
            } else if (color.equalsIgnoreCase("green")) {
                tokens[i] = new Token("Green", i);
            }
        }
        System.out.println();
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i] == null) {
                System.out.println("You did not input valid values. Try again.\n");
                selectColors();
            }
        }
    }

    public void startGame() {
        boolean isOptionValid = true;
        System.out.println("Which would you prefer?");
        System.out.println("1. Select your own colors");
        System.out.println("2. Generate random colors");
        int choice = scanner.nextInt();
        if (choice == 1) {
            selectColors();
        } else if (choice == 2) {
            chooseRandomColors();
        } else {
            System.out.println("Invalid option. Select an option again.");
            isOptionValid = false;
            startGame();
            // If this happens, do not start the game
        }
        if (isOptionValid) { // In development
            setGuess();
            tries++;
            checkEquivalency();
        }
    }

    public void setGuess() {
        revealColors();
        showAvailableColors();
        System.out.println("Guess each of the four colors: ");
        for (int i = 0; i < guess.length; i++) {
            System.out.println("Color #" + (i + 1) + ":");
            String color = scanner.next();
            if (color.equalsIgnoreCase("red")) {
                guess[i] = new Token("Red", i);
            } else if (color.equalsIgnoreCase("blue")) {
                guess[i] = new Token("Blue", i);
            } else if (color.equalsIgnoreCase("yellow")) {
                guess[i] = new Token("Yellow", i);
            } else if (color.equalsIgnoreCase("black")) {
                guess[i] = new Token("Black", i);
            } else if (color.equalsIgnoreCase("white")) {
                guess[i] = new Token("White", i);
            } else if (color.equalsIgnoreCase("green")) {
                guess[i] = new Token("Green", i);
            }
        }
        for (int i = 0; i < guess.length; i++) {
            System.out.print(guess[i]);
            if (i < guess.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
        for (int i = 0; i < guess.length; i++) {
            if (guess[i] == null) {
                System.out.println("You did not input valid values. Try again.\n");
                setGuess();
            }
        }
        System.out.println();
    }

    public void checkEquivalency() {
        boolean isEquivalent = false;
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(guess[i])) {
                isEquivalent = true;
            } else if (!tokens[i].equals(guess[i])) {
                isEquivalent = false;
                break;
            }
        }
        if (isEquivalent) {
            endMenu();
        } else {
            markIndicators();
        }
    }

    public void endMenu() {
        System.out.println("Good job! You have solved the code! Would you like to: ");
        System.out.println("1. Be featured on the high scores list");
        System.out.println("2. Play again");
        System.out.println("3. Quit game");
        int choice = scanner.nextInt();
        if (choice == 1) {
            System.out.println("First Name:");
            String name = scanner.next();
            System.out.println("Date completed ([month]/[day]/[year] format)");
            String date = scanner.next();
            users.add(new User(name, date, tries));
        } else if (choice == 2) {
            startGame();
        } else if (choice == 3) {
            System.out.println("Have a nice day! Thank you for playing!");
        } else {
            System.out.println("Invalid option. Please enter again.");
            endMenu();
        }
    }

    public void printUserList() {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(users.get(i));
        }
        if (users.isEmpty()) {
            System.out.println("Either no one has played the game yet or high scores have been cleared.\n");
        }
        System.out.println("Would you like to:");
        System.out.println("1. Return to Main Menu");
        System.out.println("2. Reset high scores");
        int choice = scanner.nextInt();
        if (choice == 1) {
            showMenu();
        } else if (choice == 2) {
            resetHighScores();
            showMenu();
        } else {
            System.out.println("Invalid option. Please enter again.");
            printUserList();
        }
    }

    // In development
    public void markIndicators() {
        int tokenCount = 0, all = 0, inPos = 0, none = 0;
        for (int i = 0; i < tokens.length-1; i++) {
            for (int j = i + 1; j < tokens.length; j++) {

            }
        }
    }

    public void showAvailableColors() {
        System.out.println("Possible colors:");
        for (int i = 0; i < availableColors.length; i++) {
            System.out.print(availableColors[i]);
            if (i < availableColors.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public void showCredits() {
        System.out.println("Developed by: Clement Boiteux on February 28, 2021");
        System.out.println("Language: Java");
        System.out.println("Inspired by the Mastermind game\n");
        System.out.println("Type and enter any key to return to the main menu."); // Will reimplement this with KeyListener once I know how to do it
        scanner.next();
        showMenu();
    }

    public void showTutorial() {
        System.out.println("Mastermind is a game intended for 2 players. One player will assume the role of the codemaker and creates the code using 4 colored tokens. After the code has been made, the other player will assume the role of the codebreaker and will try to guess the code."); // In development
        System.out.println("There are six colors to choose from: Red, Blue, Green, Yellow, White, and Black. You can select four tokens of any color to try to determine the code. Doubling colors is allowed.");
        System.out.println("After you make your first guess, the codemaker will use indicators to mark if a color was correct, if a color and its position in the code was correct, or neither. A white indicator means that a color was correct but not in the right position. A black indicator will indicate that both a token's color and position is correct. If neither are these are true, then one of the indicators will be blank. The indicators do not point at specific tokens.");
        System.out.println("Using this information, the codemaker must use different colors and find patterns with previous guesses to solve the code.");
        System.out.println("Once the code has been solved, the codemaker will reveal the code and the game ends.\n");
        System.out.println("Type and enter any key to return to the main menu."); // Will reimplement this with KeyListener once I know how to do it
        scanner.next();
        showMenu();
    }

    public void aboutMe() {
        System.out.println("Hi! I am Clement Boiteux. I am currently a sophomore in high school. My most favorite subject is math, but I also enjoy any subject that uses a lot of math. I plan to pursue engineering, programming, or finance/economics in the future.");
        System.out.println("Programming Experience: I took AP Computer Science A in my sophomore year of high school and joined Programming Club. I participated in two hackathons so far and programmed in HTML/CSS for both. I actively program in Java and HTML/CSS. I have a little bit of experience with JavaScript, Python, and C++ but don't actively program in these languages.\n");
        System.out.println("Type and enter any key to return to the main menu."); // Will reimplement this with KeyListener once I know how to do it
        scanner.next();
        showMenu();
    }

    public void showMenu() {
        System.out.println("Welcome to Clement's Mastermind program!");
        System.out.println("Select an option:");
        System.out.println("1. New Game");
        System.out.println("2. Credits");
        System.out.println("3. How to Play");
        System.out.println("4. About the developer");
        System.out.println("5. High scores");
        System.out.println("6. Exit");
        int choice = scanner.nextInt();

        if (choice == 1) {
            startGame();
        } else if (choice == 2) {
            showCredits();
        } else if (choice == 3) {
            showTutorial();
        } else if (choice == 4) {
            aboutMe();
        } else if (choice == 5) {
            printUserList();
        } else if (choice == 6) {
            System.out.println("Have a nice day! Thank you for playing!");
        } else {
            System.out.println("Invalid option. Please enter again.");
            showMenu();
        }
    }

    public static void main(String[] args) {
        Mastermind m = new Mastermind();
        m.showMenu();
    }
}