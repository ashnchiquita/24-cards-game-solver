import java.util.HashSet;

public class Deck {
    /* Mengurus proses pada deck */
    private static final char[] opList = {'+','-','*','/'};
    private static double[] arrNum3 = new double[3];
    private static double[] arrNum2 = new double[2];
    private static double[] arrNum1 = new double[1];
    private static String[] arrStr3 = new String[3];
    private static String[] arrStr2 = new String[2];
    private static String[] arrStr1 = new String[1];

    private static double opSwitch(double a, double b, int op) {
        /* Mengembalikan nilai 'a op b', op menunjukkan indeks pada array opList 
         * Prekondisi: tidak ada pembagian dengan nol */
        switch (op) {
            case 0:
                return a + b;
            case 1:
                return a - b;
            case 2:
                return a * b;
            default: // case 3
                return a / b;
        }
    }

    private static String createExp(String exp1, String exp2, int op, boolean last) {
        /* Mengembalikan string "a op b" atau "(a op b)", op menunjukkan indeks pada array opList 
         * Prekondisi: tidak ada pembagian dengan nol */
        String exp = exp1 + " " + opList[op] + " " + exp2;
        if (!last) {
            exp = "(" + exp + ")";
        }
        return exp;
    }

    public static String stringDeck(String[] deck) {
        /* Mengubah array deck menjadi satu string, tiap kartu dipisahkan oleh spasi */
        String str = "";
        for (int i = 0; i < 3; i++) {
            str += (deck[i] + " ");
        }
        str += deck[3];
        return str;
    }

    private static void copyExc(int level, double[] precNum, String[] precStr, double[] newNum, String[] newStr, int i, int j, int op, boolean last) {
        /* Meng-copy isi array prec ke new, dengan menghapus nilai yang dioperasikan dan menambahkan nilai hasil operasi */
        int ctr = 0;
        if (level > 2) {
            for (int k = 0; k < level; k++) {
                if (k != i && k != j) {
                    newNum[ctr] = precNum[k];
                    newStr[ctr] = precStr[k];
                    ctr++;
                }
            }
        }
        newNum[ctr] = opSwitch(precNum[i], precNum[j], op);
        newStr[ctr] = createExp(precStr[i], precStr[j], op, last);
    }

    public static void solve(int level, HashSet<String> setStr, double[] precNum, String[] precStr) {
        /* Meng-solve deck dan menyimpan jawabannya di set */
        boolean last;
        double[] newNum;
        String[] newStr;

        if (level == 1) {
            if (precNum[0] >= 23.99999999 && precNum[0] <= 24.00000001) {
                setStr.add(precStr[0]);
            }
        } else {
            for (int i = 0; i < level; i++) {
                for (int j = 0; j < level; j++) {
                    if (i != j) {
                        for (int op = 0; op < 4; op++) {
                            if (!(op == 4 && precNum[j] == 0)) {
                                last = false;
                                switch (level) {
                                    case 2:
                                        last = true;
                                        newNum = arrNum1;
                                        newStr = arrStr1;
                                        break;
                                    case 3:
                                        newNum = arrNum2;
                                        newStr = arrStr2;
                                        break;
                                    default: // case 4
                                        newNum = arrNum3;
                                        newStr = arrStr3;
                                        break;
                                }
                                copyExc(level, precNum, precStr, newNum, newStr, i, j, op, last);
                                solve(level - 1, setStr, newNum, newStr);
                            }
                        }
                    }
                }
            }                   
        }
    }
}
