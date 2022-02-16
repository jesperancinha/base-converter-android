package org.jesperancinha.universitybaseconverter.helpers;

/**
 * Created by filipesj on 5/03/14.
 */
public class Converter {

    public static String getDecToBase(int number, int base) {

        StringBuilder sb = new StringBuilder();
        if (number == 0) {
            sb.append(0);
        } else {
            while (number > 0) {
                int remainder = number % base;
                if (number < base) {
                    sb.append(getChar(number));
                } else {

                    sb.append(getChar(remainder));

                }
                number /= base;

            }
        }

        return sb.reverse().toString();
    }


    public static int getBaseToDeC(String number, int base) {
        number = number.trim();
        int result = 0;
        if (number.equals("0")) {
            result = 0;
        } else {
            int j = 0;
            for (int i = number.length() - 1; i >= 0; i--) {
                int digit = getInt(number.substring(i, i + 1));

                int powNumber = (int) Math.pow(base, j++);

                result += digit * powNumber;
            }
        }

        return result;
    }

    private static int getInt(String charString) {

        char a = charString.charAt(0);
        int converted = 0;
        if ((int) a <= 57) {
            converted = (int) a - 48;
        } else {
            converted = (int) a + 10 - 65;
        }
        return converted;
    }


    public static char getChar(int number) {

        char a;
        if (number <= 9) {
            a = (char) (number + 48);
        } else {
            a = (char) (number - 10 + 65);
        }
        return a;
    }
}
