import java.util.HashSet;

public class App {
    /* PROGRAM UTAMA */
    public static void main(String[] args) throws Exception {
        int cmd;

        do {
            mainProg();
            cmd = InputHandler.contConfirm();
        } while (cmd != 2);
    }

    /* 1X SOLVE DECK */
    private static void mainProg() {
        String[] deck = new String[4];
        double[] cards = new double[4];
        HashSet<String> setStr = new HashSet<String>();

        int cmd = InputHandler.inputMethod();
        
        switch (cmd) {
            case 1:
                InputHandler.readInput(deck);
                break;
            default: // case 2
                InputHandler.randInput(deck);
                break;
        }

        System.out.println("\nDeck: " + Deck.stringDeck(deck));
        InputHandler.convertDeck(deck, cards);
        long start = System.currentTimeMillis();
        Deck.solve(4, setStr, cards, deck);
        long exec = System.currentTimeMillis() - start;      

        OutputHandler.outputTerm(setStr);
        System.out.println("\nExecution time: " + exec + " ms");
        
        cmd = OutputHandler.outputMethod();
        
        if (cmd == 1) {
            OutputHandler.writeAnsFile(deck, setStr, exec);
        }
    }
}
