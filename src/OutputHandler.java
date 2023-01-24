import java.util.Scanner;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class OutputHandler {
    private static Scanner in = new Scanner(System.in);

    public static void outputTerm(HashSet<String> setStr) {
        /* Menampilkan jawaban di terminal */
        if (setStr.size() == 0) {
            System.out.println("\nNo solution found");
        } else {
            System.out.println("\n" + setStr.size() + " solution(s) found");
            
            for (String ans : setStr) {
                System.out.println(ans);
            }
        }
    }

    public static int outputMethod() {
        /* Memilih menulis jawaban ke file atau tidak */
        return InputHandler.rangedInput(1, 2, "\nWrite the answer to file?\n1. Yes\n2. No");
    }

    public static void writeAnsFile(String[] deck, HashSet<String> setStr, long exec) {
        /* Menulis jawaban ke file .txt */
        FileWriter fw = null;
        BufferedWriter buff = null;

        System.out.print("\nFile name (without .txt): ");
        String filename = "./test/" + in.nextLine() + ".txt";

        try {
            fw = new FileWriter(filename);
            buff = new BufferedWriter(fw);
            buff.write("Deck: " + Deck.stringDeck(deck));
            buff.newLine();
            buff.newLine();
            if (setStr.size() == 0) {
                buff.write("No solution found");
                buff.newLine();
            } else {
                buff.write(setStr.size() + " solution(s) found");
                buff.newLine();

                for (String ans : setStr) {
                    buff.write(ans);
                    buff.newLine();
                }
            }

            buff.newLine();
            buff.write("Execution time: " + exec + " ms");
            buff.flush();
            buff.close();
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void asciiArt() {

    }
}
