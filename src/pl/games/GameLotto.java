package pl.games;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GameLotto {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int[] numbs = new int[6];

        System.out.println("Enter 6 different numbers from 1 to 49");

        boolean isSixNumbs = false;
        int index = 0;
        while (!isSixNumbs) {
            int num = getInt(sc);
            if (checkNumber(numbs, num)) {
                numbs[index] = num;
                index++;
                if (index == 6) {
                    isSixNumbs = true;
                }
            }
        }

        Arrays.sort(numbs);
        System.out.println("Your numbers: " + Arrays.toString(numbs));

        int[] drawnNumbs = new int[6];
        while (index > 0) {
            int randomNum = new Random().nextInt(48) + 1;
            drawnNumbs[index - 1] = randomNum;
            index--;
        }

        int amountOfNumbs = 0;
        for (int i = 0; i < numbs.length; i++) {
            for (int j = 0; j < drawnNumbs.length; j++) {
                if (numbs[i] == drawnNumbs[j])
                    amountOfNumbs++;
            }
        }

        System.out.println("Drawn numbers " + Arrays.toString(drawnNumbs));
        System.out.println("Your hits: " + amountOfNumbs);

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

    static  int getInt(Scanner sc) {
        while (!sc.hasNextInt()) {
            sc.next();
            System.out.println("The value entered must be a number");
        }
        return sc.nextInt();
    }

}
