import java.util.Scanner;
import java.util.HashSet;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class OutputHandler {
    /* Mengurus masalah output */
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
        System.out.println("\n _____    __   __               ______   ________   ______    ______   ______");
        System.out.println("/_____/\\ /__/\\/__/\\            /_____/\\ /_______/\\ /_____/\\  /_____/\\ /_____/\\");
        System.out.println("\\:::_:\\ \\\\  \\ \\: \\ \\__  _______\\:::__\\/ \\::: _  \\ \\\\:::_ \\ \\ \\:::_ \\ \\\\::::_\\/_");
        System.out.println("    _\\:\\| \\::\\_\\::\\/_/\\/______/\\\\:\\ \\  __\\::(_)  \\ \\\\:(_) ) )_\\:\\ \\ \\ \\\\:\\/___/\\");
        System.out.println("   /::_/__ \\_:::   __\\/\\__::::\\/ \\:\\ \\/_/\\\\:: __  \\ \\\\: __ `\\ \\\\:\\ \\ \\ \\\\_::._\\:\\");
        System.out.println("   \\:\\____/\\    \\::\\ \\            \\:\\_\\ \\ \\\\:.\\ \\  \\ \\\\ \\ `\\ \\ \\\\:\\/.:| | /____\\:\\");
        System.out.println("    \\_____\\/     \\__\\/             \\_____\\/ \\__\\/\\__\\/ \\_\\/ \\_\\/ \\____/_/ \\_____\\/");
        System.out.println("\n                      - .` * . g a m e   s o l v e r * . - . `");
    }
}