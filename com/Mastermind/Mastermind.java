package com.Mastermind;

import java.util.Scanner;
import java.util.ArrayList;

/*
* Developed and programmed by Clement Boiteux
* Project started on 2/28/2021
* Future updates: Implement a GUI, new gamemodes and features, audio, settings, com.com.Mastermind.Mastermind.User Login
*/

public class Mastermind {

    private String[][] pastGuesses;
    int pastGuessRow = 0, pastGuessCol = 0;
    private ArrayList<User> users = new ArrayList<User>();
    private String[] colors;
    private String[] guess;
    private String[] availableColors = {"Red", "Blue", "Green", "Yellow", "Black", "White"};
    private String[] indicators = {"Black", "White"};
    private int tries = 0;
    Scanner scanner = new Scanner(System.in);

    public Mastermind() {
        pastGuesses = new String[10][4];
        colors = new String[4];
        guess = new String[4];
    }

    public Mastermind(int numPastGuesses, int numColors, int numGuesses) {
        pastGuesses = new String[numPastGuesses][4];
        colors = new String[numColors];
        guess = new String[numGuesses];
    }

    public void getGuessHistory() {
        for (int i = 0; i < pastGuesses.length; i++) {
            for (int j = 0; j < pastGuesses[i].length; i++) {
                System.out.print(pastGuesses[i][j] + " ");
            }
        }
    }

    public void chooseRandomColors() {
        for (int i = 0; i < colors.length; i++) {
            colors[i] = availableColors[(int) (Math.random()*availableColors.length)];
        }
    }

    public void revealColors() {
        for (int i = 0; i < colors.length; i++) {
            System.out.println(colors[i]);
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
        for (int i = 0; i < colors.length; i++) {
            System.out.println("Color #" + (i + 1) + ":");
            String color = scanner.next();
            if (color.equalsIgnoreCase("red")) {
                colors[i] = "Red";
            }
            else if (color.equalsIgnoreCase("blue")) {
                colors[i] = "Blue";
            }
            else if (color.equalsIgnoreCase("yellow")) {
                colors[i] = "Yellow";
            }
            else if (color.equalsIgnoreCase("black")) {
                colors[i] = "Black";
            }
            else if (color.equalsIgnoreCase("white")) {
                colors[i] = "White";
            }
            else if (color.equalsIgnoreCase("green")) {
                colors[i] = "Green";
            }
        }
        System.out.println();
        for (int i = 0; i < colors.length; i++) {
            if (colors[i] == null) {
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
        }
        else if (choice == 2) {
            chooseRandomColors();
        }
        else {
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
                guess[i] = "Red";
            }
            else if (color.equalsIgnoreCase("blue")) {
                guess[i] = "Blue";
            }
            else if (color.equalsIgnoreCase("yellow")) {
                guess[i] = "Yellow";
            }
            else if (color.equalsIgnoreCase("black")) {
                guess[i] = "Black";
            }
            else if (color.equalsIgnoreCase("white")) {
                guess[i] = "White";
            }
            else if (color.equalsIgnoreCase("green")) {
                guess[i] = "Green";
            }
        }

        String s = "";
        for (int i = 0; i < guess.length; i++) {
            s += guess[i];
            if (i < guess.length - 1) {
                s += ", ";
            }
        }
        System.out.println(s);

        for (int i = 0; i < pastGuesses[0].length; i++) {
            pastGuesses[pastGuessRow][i] = guess[i];
        }

        if (pastGuessRow < pastGuesses.length-1) {
            pastGuessRow++;
        }
        else if (pastGuessRow == pastGuesses.length-1) {
            pastGuessCol++;
            pastGuessRow = 0;
        }

        System.out.println();

        for (int i = 0; i < colors.length; i++) {
            if (guess[i] == null) {
                System.out.println("You did not input valid values. Try again.\n");
                setGuess();
            }
        }
        System.out.println();
    }

    public void checkEquivalency() {
        boolean isEquivalent = false;
        if (colors.length == guess.length) { // Should not be a problem, array length doesn't magically change
            for (int i = 0; i < colors.length; i++) {
                if (colors[i].equals(guess[i])) {
                    isEquivalent = true;
                }
                else if (!colors[i].equals(guess[i])) {
                    isEquivalent = false;
                    break;
                }
            }
            if (isEquivalent) {
                endMenu();
            }
            else {
                markIndicators();
                getGuessHistory();
                setGuess();
            }
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
        }
        else if (choice == 2) {
            startGame();
        }
        else if (choice == 3) {
            System.out.println("Have a nice day! Thank you for playing!");
        }
        else {
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
        }
        else if (choice == 2) {
            resetHighScores();
            showMenu();
        }
        else {
            System.out.println("Invalid option. Please enter again.");
            printUserList();
        }
    }

    // In development
    public void markIndicators() {
        int all = 0, colorRight = 0, none = 0;
        int colorsPos = 0, guessPos = 0;
        for (int i = 0; i < colors.length; i++) {

            if (colors[i].equals(guess[i])) {
                all++;
            }
        }
            
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < guess.length; j++) {
                if (colors[i].equals(guess[j])) {
                    colorRight++;
                }
                else if (!(colors[i].equals(guess[j])) && !(i == j)) {
                    none++;
                }
            }                
        } 
        
        // Index check
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < guess.length; j++) {
                if (colors[i].equals(guess[j])) {
                    System.out.println(i + ", " + j);
                }
            }
        }

        if (all + colorRight + none == 4) {
            System.out.println("Color and position right: " + all);
            System.out.println("Color right but incorrect position: " + colorRight);
            System.out.println("Neither of these: "); // In development
        }
        else {
            System.out.println("An error occurred. The total amount of tokens does not sum up to 4.");
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
        System.out.println("Inspired by the com.Mastermind.Mastermind game\n");
        redirect();
    }

    public void showTutorial() {
        System.out.println("com.Mastermind.Mastermind is a game intended for 2 players. One player will assume the role of the codemaker and creates the code using 4 colored tokens. After the code has been made, the other player will assume the role of the codebreaker and will try to guess the code."); // In development
        System.out.println("There are six colors to choose from: Red, Blue, Green, Yellow, White, and Black. You can select four tokens of any color to try to determine the code. Doubling colors is allowed.");
        System.out.println("After you make your first guess, the codemaker will use indicators to mark if a color was correct, if a color and its position in the code was correct, or neither. A white indicator means that a color was correct but not in the right position. A black indicator will indicate that both a token's color and position is correct. If neither are these are true, then one of the indicators will be blank. The indicators do not point at specific tokens.");
        System.out.println("Using this information, the codemaker must use different colors and find patterns with previous guesses to solve the code.");
        System.out.println("Once the code has been solved, the codemaker will reveal the code and the game ends.\n");
        redirect();
    }

    public void redirect() {
        System.out.println("Type and enter any key to return to the main menu."); // Will reimplement this with KeyListener once I know how to do it
        scanner.next();
        showMenu();
    }

    public void aboutMe() {
        System.out.println("Hi! I am Clement Boiteux. I am currently a sophomore in high school. My most favorite subject is math, but I also enjoy any subject that uses a lot of math. I plan to pursue engineering, programming, or finance/economics in the future.");
        System.out.println("Programming Experience: I took AP Computer Science A in my sophomore year of high school and joined Programming Club. I participated in two hackathons so far and programmed in HTML/CSS for both. I actively program in Java and HTML/CSS. I have a little bit of experience with JavaScript, Python, and C++ but don't actively program in these languages.\n");
        redirect();
    }

    public void showMenu() {
        System.out.println("Welcome to Clement's com.Mastermind.Mastermind program!");
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
        }
        else if (choice == 2) {
            showCredits();
        }
        else if (choice == 3) {
            showTutorial();
        }
        else if (choice == 4) {
            aboutMe();
        }
        else if (choice == 5) {
            printUserList();
        }
        else if (choice == 6) {
            System.out.println("Have a nice day! Thank you for playing!");
        }
        else {
            System.out.println("Invalid option. Please enter again.");
            showMenu();
        }
    }

    public static void main(String[] args) {
        Mastermind m = new Mastermind();
        m.showMenu();
    }
}