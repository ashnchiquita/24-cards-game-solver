import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class InputHandler {
    private static final String[] validCards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static Scanner in = new Scanner(System.in);

    /* CHOOSE INPUT METHOD */
    public static int rangedInput(int a, int b, String question) {
        String[] prevAns;
        int ans;

        do {
            System.out.println(question);
            System.out.print("Your answer: ");
            prevAns = in.nextLine().split(" ");

            if (prevAns.length == 1) {
                try {
                    ans = Integer.parseInt(prevAns[0]);
                } catch (NumberFormatException e) {
                    ans = a - 1;
                }
            } else {
                ans = a - 1;
            }

            if (!(ans >= a && ans <= b)) {
                System.out.println("Invalid input, please try again.\n");
            }
        } while (!(ans >= a && ans <= b));

        return ans;
    }

    public static int inputMethod() {
        return rangedInput(1, 2, "\nChoose input method:\n1. Custom Deck\n2. Random Deck");
    }

    public static int contConfirm() {
        return rangedInput(1, 2, "\nContinue?\n1. Yes\n2. No");
    }

    /* READ CUSTOM DECK */
    /* Array member validation */
    private static boolean validArr(String[] arrStr) {
        boolean valid = true;

        if (arrStr.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (!(Arrays.asList(validCards).contains(arrStr[i]))) {
                    valid = false;
                    break;
                }
            }
        } else {
            valid = false;
        }

        return valid;
    }
    /* Function */
    public static void readInput(String[] deck) {
        String[] arrOfStr = in.nextLine().split(" ");

        while (!validArr(arrOfStr)) {
            System.out.println("Invalid input, please try again.");
            arrOfStr = in.nextLine().split(" ");
        }
        
        for (int i = 0; i < 4; i++) {
            deck[i] = arrOfStr[i];
        }
    }

    /* CREATE RANDOM DECK */
    public static void randInput(String[] deck) {
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            deck[i] = validCards[rand.nextInt(13)];
        }
    }

    /* CONVERT DECK (ARRAY OF STRINGS) TO CARDNUM (ARRAY OF DOUBLES) */
    /* Convert a string to a double */
    private static double convertStr(String card) {
        if (card.equals("A")) {
            return 1;
        } else if (card.equals("J")) {
            return 11;
        } else if (card.equals("Q")) {
            return 12;
        } else if (card.equals("K")) {
            return 13;
        } else {
            return Double.parseDouble(card);
        }
    }
    /* Function */
    public static void convertDeck(String[] deck, double[] cardNum) {
        for (int i = 0; i < 4; i++) {
            cardNum[i] = convertStr(deck[i]);
        }
    }
}
