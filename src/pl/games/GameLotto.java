package pl.games;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class GameLotto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int maxNumbers = 6;

        System.out.println("Enter " + maxNumbers + " different numbers from 1 to 49");

        int[] selectedNumbers = getSelectedNumbers(sc, maxNumbers);
        int[] drawnNumbs = getRandomNumbers(maxNumbers);
        int[] hitNumbers = getHitNumbers(drawnNumbs, selectedNumbers);

        printScore(selectedNumbers, drawnNumbs, hitNumbers);

    }

    private static void printScore(int[] selectedNumbers, int[] drawnNumbs, int[] hitNumbers) {
        System.out.println("Your numbers: " + Arrays.toString(selectedNumbers));
        System.out.println("Drawn numbers " + Arrays.toString(drawnNumbs));
        System.out.println("Your hits: " + (hitNumbers != null ? hitNumbers.length : 0));
        System.out.println("Hit numbers: " + Arrays.toString(hitNumbers));
    }

    private static int[] getSelectedNumbers(Scanner sc, int maxNumbers) {
        int[] numbs = new int[maxNumbers];
        int[] copyOfSelectedNumbs = new int[0];
        int index = 0;
        while (copyOfSelectedNumbs.length < maxNumbers) {
            int num = getInt(sc);
            if (checkNumber(numbs, num)) {
                numbs[index] = num;
                index++;
                copyOfSelectedNumbs = Arrays.copyOf(numbs, index);
            }
        }
        Arrays.sort(copyOfSelectedNumbs);
        return copyOfSelectedNumbs;
    }

    private static int[] getRandomNumbers(int index) {
        int[] drawnNumbs = new int[index];
        while (index > 0) {
            int randomNum = new Random().nextInt(48) + 1;
            drawnNumbs[index - 1] = randomNum;
            index--;
        }
        return drawnNumbs;
    }

    static boolean checkNumber(int[] arr, int num) {
        for (int item : arr) {
            if (item != num && num > 0 && num < 50) {
                return true;
            }
        }
        System.out.println("The number must be from 1 to 49");
        return false;
    }

    static int getInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("The value entered must be a number");
        }
        return sc.nextInt();
    }

    static int[] getDrawNumbersWithoutHitNumber(int[] drawNumbers, int indexOfNumberToRemove) {
        return IntStream.range(0, drawNumbers.length)
                .filter(index -> index != indexOfNumberToRemove)
                .map(index -> drawNumbers[index])
                .toArray();
    }

    static int[] getHitNumbers(int[] drawnNumbs, int[] selectedNumbers) {
        int amountOfNumbs = 0;
        int[] hitNumbers = new int[6];
        int[] copyHitNumbers = null;
        int[] copyDrawNumbers = drawnNumbs;
        for (int numb : selectedNumbers) {
            for (int j = 0; j < copyDrawNumbers.length; j++) {
                if (numb == copyDrawNumbers[j]) {
                    hitNumbers[amountOfNumbs] = numb;
                    amountOfNumbs++;
                    copyHitNumbers = Arrays.copyOf(hitNumbers, amountOfNumbs);
                    copyDrawNumbers = getDrawNumbersWithoutHitNumber(copyDrawNumbers, j);
                    break;
                }
            }
        }
        return copyHitNumbers;
    }

}