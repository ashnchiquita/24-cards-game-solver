import java.util.Scanner;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class OutputHandler {
    private static Scanner in = new Scanner(System.in);

    public static void outputTerm(HashSet<String> setStr) {
        System.out.println("\n" + setStr.size() + " solution(s) found");
        for (String ans : setStr) {
            System.out.println(ans);
        }
    }

    public static int outputMethod() {
        return InputHandler.rangedInput(1, 2, "\nWrite the answer to file?\n1. Yes\n2. No");
    }

    public static void writeAnsFile(String[] deck, HashSet<String> setStr, long exec) {
        String filename;
        FileWriter fw = null;
        BufferedWriter buff = null;

        System.out.print("\nFile name (without .txt): ");
        filename = "./test/" + in.nextLine() + ".txt";

        try {
            fw = new FileWriter(filename);
            buff = new BufferedWriter(fw);
            buff.write("Deck: " + Deck.stringDeck(deck));
            buff.newLine();
            buff.newLine();
            buff.write(setStr.size() + " solution(s) found");
            buff.newLine();


            for (String ans : setStr) {
                buff.write(ans);
                buff.newLine();
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
}
