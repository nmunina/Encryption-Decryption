package encryptdecrypt;

import java.io.IOException;


public class Main {
    // testing strings
    static String[] dataArgsEnc = new String[]{"-mode", "enc", "-key", "2", "-data", "aWelcome to hyperskill!", "-alg", "shift"};
    static String[] dataArgsDec = new String[]{"-mode", "dec", "-key", "2", "-data", "cYgneqog vq jArgtumknn!", "-alg", "shift"};
    static String[] inArgsEnc = new String[]{"-mode", "enc", "-key", "5", "-in", "/Users/nmunina/repo/Encryption-Decryption/Encryption-Decryption/task/src/encryptdecrypt/in.txt", "-alg", "unicode"};
    static String[] outArgsDec = new String[]{"-mode", "dec", "-key", "5", "-in", "/Users/nmunina/repo/Encryption-Decryption/Encryption-Decryption/task/src/encryptdecrypt/in.txt", "-out", "/Users/nmunina/repo/Encryption-Decryption/Encryption-Decryption/task/src/encryptdecrypt/out2.txt", "-alg", "unicode"};
    static String[] example1 = new String[]{"-mode", "enc", "-key", "5", "-in", "/Users/nmunina/repo/Encryption-Decryption/Encryption-Decryption/task/src/encryptdecrypt/in.txt", "-out", "/Users/nmunina/repo/Encryption-Decryption/Encryption-Decryption/task/src/encryptdecrypt/out.txt", "-alg", "shift"};


    public static void main(String[] args) throws IOException {

        // initiate this encryption task from the parameters
        CryptingTask task = initiateValues(args);

        CodingProcess process;

        // select will it be encryption or decryption mode
        switch (task.mode) {
            case "enc":
                process = new EncryptProcess(task);
                break;
            case "dec":
                process = new DecryptProcess(task);
                break;
            default:
                process = null;
                break;
        }

        if (task.mode == null) {
            throw new RuntimeException(
                    "Unknown strategy type passed. Please, write to the author of the problem.");
        }

        //encrypt according to the parametrs
        process.code();

        // write the result to the output
        process.output();
    }


    private static CryptingTask initiateValues(String[] args) throws IOException {
        String data = "";
        int key = 0;
        String mode = "enc";
        String in = "";
        String out = "";
        String alg = "shift";

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    alg = args[i + 1];
                    break;
            }
        }
        return new CryptingTask(data, key, mode, alg, in, out);
    }
}

/*

        if (!"".equals(data) && !"".equals(in)) {
            outputDecrypted = decrypt(data, key, mode);
        } else if ("".equals(data)) {
            try {
                outputDecrypted = decrypt(readFileAsString(in), key, mode);
            } catch (IOException e) {
                System.out.println("Error" + e.getMessage());
            }
        } else {
            outputDecrypted = decrypt(data, key, mode);
        }

        if ("".equals(out)) {
            System.out.println(outputDecrypted);
        } else {
            writeToFile(out, outputDecrypted);
        }
    }

    private static void writeToFile(String out, String outputDecrypted) {
        File file = new File(out);

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(outputDecrypted);
        } catch (IOException e) {
            System.out.printf("An Error occurs %s", e.getMessage());
        }
    }

    public static String readFileAsString(String fileName) throws IOException {
            return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    private static String decrypt(String input, int key, String mode) {
        char[] replacedInput;
        char[] inputToCharArray = input.toCharArray();

        replacedInput = inputToCharArray;
        // if mode is "decrypted"
        if (mode.equals("dec")) {
            for (int i = 0; i < inputToCharArray.length; i++) {
                int position = inputToCharArray[i];
                if (position - key < 32) {
                    replacedInput[i] = (char) (position - key + (126 - position));
                } else {
                    replacedInput[i] = (char) (position - key);
                }
            }
        // else encrypt
        } else {
            for (int i = 0; i < inputToCharArray.length; i++) {
                int position = inputToCharArray[i];
                if (position + key < 127) {
                    replacedInput[i] = (char) (position + key);
                } else {
                    replacedInput[i] = (char) (position + key - 126);
                }
            }
        }

        return String.valueOf(replacedInput);
    }

/*
    private static String encrypt(String input, int key) {
        char[] replacedInput;
        char[] inputToCharArray = input.toCharArray();

        replacedInput = inputToCharArray;

        for (int i = 0; i < inputToCharArray.length; i++) {
            //find corresponding char in abc[]
            int position = inputToCharArray[i];
            if (position + key < 127) {
                replacedInput[i] = (char) (position + key);
            } else {
                replacedInput[i] = (char) (position + key - 126);
            }
        }
        return String.valueOf(replacedInput);
    }


    */

