import java.util.HashSet;

public class Deck {
    private static final char[] opList = {'+','-','*','/'};
    private static double[] cpNum1 = {0.0, 0.0, 0.0};
    private static double[] cpNum2 = {0.0, 0.0};
    private static double[] cpNum3 = {0.0};
    private static String[] cpStr1 = {"X", "X", "X"};
    private static String[] cpStr2 = {"X", "X"};
    private static String[] cpStr3 = {"X"};

    private static double opSwitch(double a, double b, int op) {
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
        String exp = exp1 + " " + opList[op] + " " + exp2;
        if (!last) {
            exp = "(" + exp + ")";
        }
        return exp;
    }

    public static String stringDeck(String[] deck) {
        String str = "";
        for (int i = 0; i < 3; i++) {
            str += (deck[i] + " ");
        }
        str += deck[3];
        return str;
    }

    private static void copyExc(int level, double[] precNum, String[] precStr, double[] newNum, String[] newStr, int i, int j, int op, boolean last) {
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
                                        newNum = cpNum3;
                                        newStr = cpStr3;
                                        break;
                                    case 3:
                                        newNum = cpNum2;
                                        newStr = cpStr2;
                                        break;
                                    default: // case 4
                                        newNum = cpNum1;
                                        newStr = cpStr1;
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
