package com.Mastermind;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

/*
 * Developed and programmed by Clement Boiteux
 * Project started on 2/28/2021
 * Future updates: Implement a GUI, new gamemodes and features
 */

public class Mastermind {

    private Token[] tokens = new Token[4];
    private Token[] guess = new Token[4];
    private final String[] availableColors = {"Red", "Blue", "Green", "Yellow", "Black", "White"};
    private ArrayList<String> indicators = new ArrayList();
    private int tries = 0;
    Scanner scanner = new Scanner(System.in);

    public void chooseRandomColors() {
        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = new Token(availableColors[(int) (Math.random() * availableColors.length)], i);
        }
    }

    public void revealColors() {
        for (Token token : tokens) {
            System.out.println(token);
        } // Cheat code
        System.out.println();
    }

    public void selectColors() {
        showAvailableColors();
        System.out.println("Please choose your " + tokens.length + " colors: ");
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
        for (Token token : tokens) {
            if (token == null) {
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
        for (Token token : guess) {
            if (token == null) {
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
        System.out.println("1. Play again");
        System.out.println("2. Quit game");
        int choice = scanner.nextInt();
        if (choice == 1) {
            startGame();
        } else if (choice == 2) {
            System.out.println("Have a nice day! Thanks for playing!");
        } else {
            System.out.println("Invalid option. Please enter again.");
            endMenu();
        }
    }


    // In development
    public ArrayList<String> markIndicators() {
//      boolean realIsDouble = false, guessIsDouble = false, realIsTriple = false, guessIsTriple = false, realIsQuadruple = false, guessIsQuadruple = false;
        ArrayList<Integer> iIndices = new ArrayList();
        ArrayList<Integer> jIndices = new ArrayList();

        int all = 0, colorRight = 0, none = 0;
        for (int i = 0; i < tokens.length; i++) {
            for (int j = i; j < guess.length; j++) {
                Token real = tokens[i], guessed = guess[j];
                if (real.equals(guessed)) {
                    all++;
                }
            }
        }

        for (int i = 0; i < iIndices.size(); i++) {
            System.out.print(iIndices.get(i) + " ");
            System.out.print(jIndices.get(i));
            System.out.println();
        }

//        for (int i = 0; i < iIndices.size(); i++) {
//            for (int j = 1; j < iIndices.size(); j++) {
//                if (iIndices.get(i) == iIndices.get(j)) {
//                    realIsDouble = true;
//                }
//
//                if (iIndices.get(i) == iIndices.get(j) && realIsDouble) {
//                    realIsTriple = true;
//                    realIsDouble = false;
//                }
//
//                if (iIndices.get(i) == iIndices.get(j) && realIsTriple) {
//                    realIsQuadruple = true;
//                    realIsTriple = false;
//                }
//            }
//        }

//        if (realIsDouble) {
//            for (int i = 0; i < 4; i++)
//        } else if (realIsTriple) {
//
//        } else if (realIsQuadruple) {
//
//        } else {
//
//        }

        return indicators;
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


    public void showMenu() {
        System.out.println("Welcome to Clement's Mastermind program!");
        System.out.println("Select an option:");
        System.out.println("1. New Game");
        System.out.println("2. Credits");
        System.out.println("3. How to Play");
        System.out.println("4. Exit");
        int choice = scanner.nextInt();

        if (choice == 1) {
            startGame();
        } else if (choice == 2) {
            showCredits();
        } else if (choice == 3) {
            showTutorial();
        } else if (choice == 4) {
            System.out.println("Have a nice day! Thanks for playing :)");
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