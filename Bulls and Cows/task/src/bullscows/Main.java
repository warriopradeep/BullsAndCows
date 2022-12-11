package bullscows;

import java.util.*;

public class Main {

    static List<Character> numbers = new ArrayList<>();

    public static void main(String[] args) {
        generateSymbols();
        System.out.println("Input the length of the secret code:");
        int codeLength = getCodeLength();

        System.out.println("Input the number of possible symbols in the code:");
        int uniqueSymbols = getCodeLength();

        if (codeLength <= 0) {
            System.out.println("Error: Code length can't be zero");
            System.exit(1);
        }

        if (uniqueSymbols <= 0) {
            System.out.printf("Error: Number of possible symbols can't be %d.", uniqueSymbols);
            System.exit(1);
        } else if (uniqueSymbols > 36) {
            System.out.println("Error: maximum number of possible symbols in the code is 36 (0-9, a-z).");
            System.exit(1);
        }

        String stars = "*".repeat(codeLength);
        String codeRange;

        if (uniqueSymbols<= 10) {
            codeRange = "(%c-%c)".formatted(numbers.get(0), numbers.get(9));
        } else {
            codeRange = "(0-9, %c-%c)".formatted(numbers.get(10), numbers.get(uniqueSymbols - 1));
        }

        if (codeLength > uniqueSymbols) {
            System.out.printf("Error: can't generate a secret number with a length of %d because there aren't enough unique digits.", codeLength);
            System.exit(1);
        } else {
            System.out.printf("The secret is prepared: %s %s.%n", stars, codeRange);
            System.out.println("Okay, let's start a game!");
            gameLoop(codeLength, uniqueSymbols);
        }


    }

    private static String bullCow(String s, String code) {

        int bull = 0;
        int cow = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != code.charAt(i)) {
                continue;
            }
            bull++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!code.contains(String.valueOf(s.charAt(i)))) {
                continue;
            }
            cow++;
        }

        cow -= bull;

        if (bull == 0 && cow == 0) {
            return "Grade: None";
        } else if (bull == 0) {
            return "Grade: %d cow(s)".formatted(cow);
        } else if (cow == 0) {
            return "Grade: %d bull(s)".formatted(bull);
        } else {
            return "Grade: %d bull(s) and %d cow(s)".formatted(bull, cow);
        }
    }

//    private static StringBuilder getRandomData() {
//        long pseudoRandomNumber = System.nanoTime();
//        StringBuilder s = new StringBuilder(String.valueOf(pseudoRandomNumber));
//        return s.reverse();
//    }

    private static String codeGenerator(int codeLength, int uniqueSymbols) {
        StringBuilder code = new StringBuilder(codeLength);
        StringBuilder randomData = new StringBuilder(getRandomData2(uniqueSymbols));

        int i = 0;
        while (code.length() < codeLength) {

            if (i == randomData.length()) {
                randomData = getRandomData2(uniqueSymbols);
                i = 0;
            }

            char c = randomData.charAt(i);
            if (code.toString().indexOf(c) == -1) {
                code.append(c);
            }

            i++;
        }

        return code.toString();
    }

    private static StringBuilder getRandomData2(int uniqueSymbols) {

        StringBuilder randomNum = new StringBuilder();
        List<Character> subset = numbers.subList(0, uniqueSymbols);
//        System.out.println(subset.toString());
        Collections.shuffle(subset);

        for (Character c : subset) {
            randomNum.append(c);
        }

        return randomNum;
    }

    private static int getCodeLength() {
        Scanner in = new Scanner(System.in);
        if (!in.hasNextInt()) {
            System.out.printf("Error: %s isn't a valid number.", in.nextLine());
            System.exit(1);
        }
        return in.nextInt();
    }

    private static void gameLoop(int codeLength, int uniqueSymbols) {
        String code = codeGenerator(codeLength, uniqueSymbols);
//        System.out.printf("The random secret number is %s.%n", code);

        int turnCounter = 1;

        while (true) {
            System.out.printf("Turn %d:%n", turnCounter);
            String inputGuess = getGuess();
            String result = bullCow(inputGuess, code);
            System.out.println(result);
            if (result.contains("%s bull".formatted(codeLength))) {
                System.out.println("Congratulations! You guessed the secret code.");
                break;
            }
            turnCounter++;
        }
    }

    private static String getGuess() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    private static void generateSymbols() {

        int zero = '0';

        for (int i = 0; i < 10; i++) {
            numbers.add((char) (zero + i));
        }

        int a = 'a';

        for (int i = 0; i < 26; i++) {
            numbers.add((char) (a + i));
        }
    }
}
