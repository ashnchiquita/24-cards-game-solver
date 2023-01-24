import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;

public class InputHandler {
    /* Mengurus masalah input */
    private static final String[] validCards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private static Scanner in = new Scanner(System.in);

    public static int rangedInput(int a, int b, String question) {
        /* Memvalidasi masukan pilihan antara a dan b (inklusif) */
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
        /* Memilih cara memasukkan deck, random atau custom */
        return rangedInput(1, 2, "\nChoose input method:\n1. Custom Deck\n2. Random Deck");
    }

    public static int contConfirm() {
        /* Memilih melanjutkan program atau tidak */
        return rangedInput(1, 2, "\nContinue?\n1. Yes\n2. No");
    }

    private static boolean validArr(String[] arrStr) {
        /* Memvalidasi apakah array merupakan sebuah deck kartu yang valid */
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
    
    public static void readInput(String[] deck) {
        /* Membaca masukan deck custom */
        System.out.print("Insert deck: ");
        String[] arrOfStr = in.nextLine().split(" ");

        while (!validArr(arrOfStr)) {
            System.out.println("Invalid input, please try again.");
            System.out.print("Insert deck: ");
            arrOfStr = in.nextLine().split(" ");
        }
        
        for (int i = 0; i < 4; i++) {
            deck[i] = arrOfStr[i];
        }
    }

    public static void randInput(String[] deck) {
        /* Membuat deck random */
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            deck[i] = validCards[rand.nextInt(13)];
        }
    }

    private static double convertStr(String card) {
        /* Mengubah kartu menjadi nilai angka */
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
    
    public static void convertDeck(String[] deck, double[] cardNum) {
        /* Mengubah setiap kartu dalam deck menjadi nilai angkanya */
        for (int i = 0; i < 4; i++) {
            cardNum[i] = convertStr(deck[i]);
        }
    }
}
