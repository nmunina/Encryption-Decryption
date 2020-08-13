package encryptdecrypt;

public class Encryption {
    static char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    static char[] alphabetSmall = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    static char[] alphabetCapitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    public static String encryptUnicode(String data, int key) {
        char[] replacedInput;
        char[] inputToCharArray = data.toCharArray();

        replacedInput = inputToCharArray;

        for (int i = 0; i < inputToCharArray.length; i++) {
            int position = inputToCharArray[i];
            if (position + key < 127) {
                replacedInput[i] = (char) (position + key);
            } else {
                replacedInput[i] = (char) (position + key - 126);
            }
        }

        return String.valueOf(replacedInput);
    }

    public static String decryptUnicode(String data, int key) {
        char[] replacedInput;
        char[] inputToCharArray = data.toCharArray();

        replacedInput = inputToCharArray;
        for (int i = 0; i < inputToCharArray.length; i++) {
            int position = inputToCharArray[i];
            if (position - key < 32) {
                replacedInput[i] = (char) (position - key + (126 - position));
            } else {
                replacedInput[i] = (char) (position - key);
            }
        }

        return String.valueOf(replacedInput);
    }

    // from 'a' to 'z' as the first circle and
    // from 'A' to 'Z' as the second circle i.e.
    // after 'z' comes 'a' and after 'Z" comes 'A'
    // Welcome to hyperskill!    Welcome to hyperskill!
    public static String encryptionShift(String data, int key) {
        char[] replacedInput;
        char[] inputToCharArray = data.toCharArray();

        replacedInput = inputToCharArray;
        for (int i = 0; i < inputToCharArray.length; i++) {
            if (Character.isLowerCase(inputToCharArray[i])) {
                for (int j = 0; j < alphabetSmall.length; j++) {
                    if (inputToCharArray[i] == alphabetSmall[j]) {
                        replacedInput[i] = alphabetSmall[(j + key) % 26];
                        break;
                    }
                }
            } else {
                for (int j = 0; j < alphabetCapitals.length; j++) {
                    if (inputToCharArray[i] == alphabetCapitals[j]) {
                        replacedInput[i] = alphabetCapitals[(j + key) % 26];
                        break;
                    }
                }
            }
        }
/*
        replacedInput = inputToCharArray;
        for (int i = 0; i < inputToCharArray.length; i++) {
            for (int j = 0; j < alphabet.length; j++) {
                if (inputToCharArray[i] == alphabet[j]) {
                    replacedInput[i] = alphabet[(j + key) % 52];
                    break;
                }
            }
        }
*/
        return String.valueOf(replacedInput);

    }

    public static String decryptionShift(String data, int key) {
        char[] replacedInput;
        char[] inputToCharArray = data.toCharArray();

        replacedInput = inputToCharArray;
        for (int i = 0; i < inputToCharArray.length; i++) {
            if (Character.isLowerCase(inputToCharArray[i])) {
                for (int j = 0; j < alphabetSmall.length; j++) {
                    if (inputToCharArray[i] == alphabetSmall[j]) {
                        int pos = j - key;
                        if (j - key < 0) {
                            pos = alphabetSmall.length - key + j;
                        }
                        replacedInput[i] = alphabetSmall[pos];
                        break;
                    }
                }
            } else {
                for (int j = 0; j < alphabetCapitals.length; j++) {
                    if (inputToCharArray[i] == alphabetCapitals[j]) {
                        int pos = j - key;
                        if (j - key < 0) {
                            pos = alphabetCapitals.length - key + j;
                        }
                        replacedInput[i] = alphabetCapitals[pos];
                        break;
                    }
                }
            }
        }

        return String.valueOf(replacedInput);

    }
}
