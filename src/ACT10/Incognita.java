package ACT10;

public class Incognita {
    static String combination = "58921";

    public void setCombination(String combination) {
        Incognita.combination = combination;
    }

    public String getCombination() {
        return combination;
    }

    public static int coincidentPositionNumbers(String combination) {
        int coincidentPositions = 0;
        for (int i = 0; i < combination.length(); i++) {
            if (combination.charAt(i) == Incognita.combination.charAt(i)) {
                coincidentPositions++;
            }
        }
        return coincidentPositions;
    }

    public static int coincidentNumbers(String combination) {
        int coincidentNumbers = 0;
        for (char c : combination.toCharArray()) {
            if (Incognita.combination.contains(c + "")) {
                coincidentNumbers++;
            }
        }
        return coincidentNumbers;
    }

    public static String evaluate(String combination) {
        if (combination.length() == 5) {
            int coincidentNumbers = Incognita.coincidentNumbers(combination);
            int coincidentPositionNumbers = Incognita.coincidentPositionNumbers(combination);
            return "Correctos: " + coincidentNumbers + ", Posiciones correctas: " + coincidentPositionNumbers + "\n";
        }
        return "";
    }

    public static boolean jackpot(String combination) {
        return coincidentPositionNumbers(combination) == Incognita.combination.length();
    }
}
