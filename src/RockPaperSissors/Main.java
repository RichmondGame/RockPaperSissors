package RockPaperSissors;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    static int playerWins;
    static int computerWins;

    public static void main(String[] arg) throws InterruptedException {
        playerWins = 0;
        computerWins = 0;
        Hands.Paper.toString();

        while (playerWins < 3 && computerWins < 3 ) {
            playRound();
        }
        if (playerWins > computerWins) {
            System.out.println("You've won the game!");
        } else {
            System.out.println("You've lost the game.");
        }
        System.out.println("Play Again? Y/N");
        Scanner scanner = new Scanner(System.in);
        try {
            if (scanner.nextLine().equals("y")) {
                main(null);
            }
        } catch (InputMismatchException e) {//not empty
            }
    }

    private static void playRound() throws InterruptedException {
        int playersHand;
        int computersHand = getRandomNumber();

        System.out.println("What will you throw?: \n1.Rock \n2.Paper \n3.Scissors");

        Scanner scanner = new Scanner(System.in);
        playersHand = validateHand(scanner);

        if (playersHand !=  -1) {
            printRPSS();
            System.out.println(findEnumHand(playersHand) + " vs " + findEnumHand(computersHand));
            System.out.println(findWinner(playersHand, computersHand) + "\n");
        }
    }

    private static void printRPSS() throws InterruptedException {
        System.out.print("Rock!");
        Thread.sleep(400);
        System.out.print(" Paper");
        Thread.sleep(400);
        System.out.print(" Scissors");
        Thread.sleep(400);
        System.out.println(" SHOOT!");
        Thread.sleep(400);
    }

    private static int validateHand(Scanner scanner) {
        String input = scanner.nextLine();
        try {
            int i = Integer.parseInt(input);
            if (i >= 1 && i <= 3) {
                return i - 1; // -1 to match the enum values
            }else {
                System.out.println("Please try again. (1-3)");
            }
        }catch (InputMismatchException | NumberFormatException e) {
            if (input.equals("q")) {
                System.exit(0);
            }
            System.out.println("Please try again. (1-3)");
        }

        return -1;
    }

    private static String findWinner(int playersHand, int computersHand) {
        String tie = "Tie";
        String win = "You Win!";
        String lose = "You Lose";
        String tryAgain = "Please try again. (1-3)";
        int[] availableChoices = new int[] {0,1,2};
        boolean isValidChoice = Arrays.stream(availableChoices).anyMatch(i -> i == playersHand);

        if (!isValidChoice) {
            return tryAgain;
        }
        if (playersHand == computersHand) {
            return tie;
        }
        if (playersHand == 0) {
            if (computersHand == 1) {
                computerWins++;
                return lose;
            } else {
                playerWins++;
                return win;
            }
        }
        if (playersHand == 1) {
            if (computersHand == 2) {
                computerWins++;
                return lose;
            } else {
                playerWins++;
                return win;
            }
        }
        if (playersHand == 2) {
            if (computersHand == 0) {
                computerWins++;
                return lose;
            } else {
                playerWins++;
                return win;
            }
        }
        return null;
    }

    private static int getRandomNumber() {
        Random random = new Random();
        return random.nextInt(3);
    }

    private static String findEnumHand(int hand) {
        for (Hands h: Hands.values()) {
                if (hand == h.ordinal()) {
                    return h.toString();
                }
        }
        return null;
    }
}
