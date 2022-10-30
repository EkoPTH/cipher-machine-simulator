/*
   SG-41 Simulator
   Written by George Lasry, February 2021
   Based on the paper by Klaus Kopacz and Paul Reuvers.
   https://www.cryptomuseum.com/crypto/sg41/index.htm

   First, rename Sg41.rename_to_jar to Sg41.jar (is provided with suffix *.rename_to_jar since most email systems reject jar (*.jar) files).
   You need to make sure you have Java installed on you computer (Windows https://www.java.com/en/download/help/windows_manual_download.html,
   or Linux - https://www.java.com/en/download/help/linux_x64_install.html. To check that you have java properly installed, from command line: java -version).


    Usage:
    To encipher/decipher using a given key:
        java -jar Sg41.jar 0001101011000100010001101|0110100100001011100101100|11001001000100100100010|01001000111010001110010|001001001010000101011010|011001100110001011010100 ABCD0100 SCHLUESSELGERAETVIEREINSWANDERER
    To encipher/decipher and print all the steps:
        java -jar Sg41.jar 0001101011000100010001101|0110100100001011100101100|11001001000100100100010|01001000111010001110010|001001001010000101011010|011001100110001011010100 ABCD0100 SCHLUESSELGERAETVIEREINSWANDERER DETAILS
    To encipher using a random key:
        java -jar Sg41.jar SCHLUESSELGERAETVIEREINSWANDERER


 */
import java.util.Arrays;
import java.util.Random;

public class Sg41 {
    private static final int WHEELS = 6;
    private static final int W1 = 0;
    private static final int W2 = W1 + 1;
    private static final int W3 = W2 + 1;
    private static final int W4 = W3 + 1;
    private static final int W5 = W4 + 1;
    private static final int W6 = W5 + 1;
    private static final int[] WHEEL_SIZES = {25, 25, 23, 23, 24, 24};
    private static final int[] KICK = {1, 2, 4, 8, 10, 0};
    private static final int T_TO_KICK = 13;
    private static final int WINDOW_TO_KICK = 8;
    private static final int[] T_OFFSETS = {WHEEL_SIZES[W1] - T_TO_KICK, WHEEL_SIZES[W2] - T_TO_KICK, WHEEL_SIZES[W3] - T_TO_KICK,
            WHEEL_SIZES[W4] - T_TO_KICK, WHEEL_SIZES[W5] - T_TO_KICK, WHEEL_SIZES[W6] - T_TO_KICK, };

    private static int[] ALPHABET = alphabet("KAPEUYLCOSGVJWBTMXNQZDIFHR");
    private static int[] ALPHABET_INVERSE = inverseAlphabet(ALPHABET);

    private static String[][] WINDOWS = {
            "A:B:C:D:E:F:G:H:I:K:L:M:N:O:P:Q:R:S:T:U:V:W:X:Y:Z".split(":"),
            "A:B:C:D:E:F:G:H:I:K:L:M:N:O:P:Q:R:S:T:U:V:W:X:Y:Z".split(":"),
            "A:B:C:D:E:F:G:H:I:K:L:M:N:O:P:Q:R:S:T:U:V:W:X".split(":"),
            "A:B:C:D:E:F:G:H:I:K:L:M:N:O:P:Q:R:S:T:U:V:W:X".split(":"),
            "01:02:03:04:05:06:07:08:09:10:11:12:13:14:15:16:17:18:19:20:21:22:23:24".split(":"),
            "00:02:05:07:10:12:15:17:20:22:25:27:30:32:35:37:40:42:45:47:50:52:55:57:".split(":"),
    };
    private boolean[][] wheelPins = {
            new boolean[WHEEL_SIZES[0]],
            new boolean[WHEEL_SIZES[1]],
            new boolean[WHEEL_SIZES[2]],
            new boolean[WHEEL_SIZES[3]],
            new boolean[WHEEL_SIZES[4]],
            new boolean[WHEEL_SIZES[5]],
    };
    private int[] positions = new int[WHEELS];
    private int[] actualPositions = new int[WHEELS];


    // Public
    Sg41(){
        for (int w = 0; w < WHEELS; w++) {
            Arrays.fill(wheelPins[w], false);
        }
        setPositions("AAAA0100");
    }

    public String encrypt(String input) {

        String output = "";

        resetActualPositions();
        for (int i = 0; i < input.length(); i++) {
            if (t(W6)) {
                advanceTwoSteps();
            }
            output += (char) ('A' + encrypt(input.charAt(i) - 'A'));
            advanceTwoSteps();
        }
        return output;
    }

    public void encryptDetails(String input) {

        String output = "";

        resetActualPositions();
        for (int i = 0; i < input.length(); i++) {
            System.out.printf("%s ", actualPositionsString());
            if (t(W6)) {
                advanceTwoSteps();
            }
            output += (char) ('A' + encrypt(input.charAt(i) - 'A'));
            System.out.printf("%c => %c\n", input.charAt(i), output.charAt(i));

            advanceTwoSteps();
        }
        System.out.println(actualPositionsString());
        System.out.println(output);
    }

    public void ramdom(){
        Random r1 = new Random();
        for (int w1 = 0; w1 < WHEELS; w1++) {
            positions[w1] = r1.nextInt(WHEEL_SIZES[w1]);
        }
        Random r = new Random();
        for (int w = 0; w < WHEELS; w++) {
            for (int i = 0; i < WHEEL_SIZES[w]; i++) {
                wheelPins[w][i] = r.nextBoolean();
            }
        }
    }

    public String wheelPinsString() {
        String s = "";
        for (int w = 0; w < WHEELS; w++) {
            if (w > 0) {
                s += "|";
            }
            for (int i = 0; i < wheelPins[w].length; i++) {
                s += (wheelPins[w][i] ? '1' : '0');
            }
        }
        return s;
    }

    public String positionsString() {
        String s = "";
        for (int w = 0; w < WHEELS; w++) {
            s += WINDOWS[w][(WHEEL_SIZES[w] + positions[w] - WINDOW_TO_KICK) % WHEEL_SIZES[w]];
        }
        return s;
    }

    public boolean setPositions(String s) {
        if (s.length() != 8) {
            return false;
        }
        String[] wheelPositionString = {
                s.substring(0, 1),
                s.substring(1, 2),
                s.substring(2, 3),
                s.substring(3, 4),
                s.substring(4, 6),
                s.substring(6, 8),
        };
        for (int w = 0; w < WHEELS; w++) {
            int position = -1;
            for (int i = 0; i < WHEEL_SIZES[i]; i++) {
                if (WINDOWS[w][i].equals(wheelPositionString[w])) {
                    position = i;
                    break;
                }
            }
            if (position == -1) {
                return false;
            }
            positions[w] = position + WINDOW_TO_KICK;
        }
        return true;
    }

    public boolean setWheelPins(String s) {
        String[] ws = s.split("[^01]+");
        if (ws.length != 6) {
            return false;
        }
        for (int w = 0; w < WHEELS; w++) {
            if (ws[w].length() != WHEEL_SIZES[w] || !ws[w].matches("[01]+")) {
                return false;
            }
            for (int i = 0; i < ws[w].length(); i++) {
                wheelPins[w][i] = ws[w].charAt(i) == '1';
            }
        }
        return true;
    }

    // Private

    private static int[] alphabet(String s) {
        int[] alphabet = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            alphabet[i] = s.charAt(i) - 'A';
        }
        return alphabet;
    }

    private static int[] inverseAlphabet(int[] alphabet) {
        int[] inverse = new int[alphabet.length];
        for (int i = 0; i < alphabet.length; i++) {
            inverse[alphabet[i]] = i;
        }
        return inverse;
    }

    private void resetActualPositions() {
        System.arraycopy(positions, 0, actualPositions, 0, WHEELS);
    }

    private String actualPositionsString() {
        String s = "";
        for (int w = 0; w < WHEELS; w++) {
            s += WINDOWS[w][(WHEEL_SIZES[w] + actualPositions[w] - WINDOW_TO_KICK) % WHEEL_SIZES[w]];
        }
        return s;
    }

    private boolean t(int w) {
        return wheelPins[w][(actualPositions[w] + T_OFFSETS[w]) % WHEEL_SIZES[w]];
    }

    private boolean c(int w) {
        return wheelPins[w][actualPositions[w]];
    }

    private void advance(int w) {
        actualPositions[w] = (actualPositions[w] + 1) % WHEEL_SIZES[w];
    }

    private void advanceTwoSteps() {
        for (int w = W6 ; w >= W2; w--) {
            if (t(w - 1)) {
                advance(w);
            }
        }
        for (int w = W1; w <=  W6; w++) {
            advance(w);
        }
    }

    private int encrypt(int c) {
        int kick = 0;
        for (int w = W1; w <= W5; w++) {
            if (c(w)) {
                kick += KICK[w];
            }
        }
        if (c(W6)) {
            kick = 25 - kick;
        }

        c = ALPHABET_INVERSE[c];
        c = (25 - c + kick + 26) % 26;
        return ALPHABET[c];
    }

    // Main
    public static void usage(String error) {
        if (error.length() > 0) {
            System.out.printf("Error: %s\n", error);
        }
        System.out.println("Usage: ");
        System.out.println("To encipher/decipher using a given key:");
        System.out.println("  java -jar Sg41.jar 0001101011000100010001101|0110100100001011100101100|11001001000100100100010|01001000111010001110010|001001001010000101011010|011001100110001011010100 ABCD0100 SCHLUESSELGERAETVIEREINSWANDERER");
        System.out.println("To encipher/decipher and print all the steps:");
        System.out.println("  java -jar Sg41.jar 0001101011000100010001101|0110100100001011100101100|11001001000100100100010|01001000111010001110010|001001001010000101011010|011001100110001011010100 ABCD0100 SCHLUESSELGERAETVIEREINSWANDERER DETAILS");
        System.out.println("To encipher using a random key:");
        System.out.println("  java -jar Sg41.jar SCHLUESSELGERAETVIEREINSWANDERER");
    }

    public static void main(String[] args) {

        Sg41 sg41 = new Sg41();
        String text = "";
        boolean details = false;

        if (args.length == 0 || args.length == 2 || args.length > 4) {
            usage("");
            return;
        }

        if (args.length == 1) {
            if (!args[0].toUpperCase().matches("[A-Z]+")) {
                usage("Invalid text to encipher with random key");
                return;
            }
            sg41.ramdom();
            System.out.println("Random settings and starting positions:");
            text = args[0];
        } else {
            if (args.length == 4) {
                if (!args[3].equalsIgnoreCase("DETAILS")) {
                    usage("Last parameter should be DETAILS or omitted");
                    return;
                }
                details = true;
            }
            text = args[2];

            if (!sg41.setWheelPins(args[0])) {
                usage("Invalid wheel pins settings");
                return;
            }

            if (!sg41.setPositions(args[1])) {
                usage("Invalid starting positions");
                return;
            }
        }
        if (!text.toUpperCase().matches("[A-Z]+")) {
            usage("Invalid text to encipher");
        }
        System.out.printf("Wheel settings: %s\n", sg41.wheelPinsString());
        System.out.printf("Starting position: %s\n", sg41.positionsString());
        System.out.printf("Input:  %s\n", text);

        if (details) {
            sg41.encryptDetails(text);
        } else {
            System.out.printf("Output: %s\n", sg41.encrypt(text));
        }

    }
}