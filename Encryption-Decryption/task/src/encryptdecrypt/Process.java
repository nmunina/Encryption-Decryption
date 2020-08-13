package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

abstract class CodingProcess {
    CryptingTask task;

    CodingProcess(CryptingTask task) {
        this.task = task;
    }

    abstract void code();
    void output() {
        if ("".equals(this.task.out)) {
            System.out.println(this.task.result);
        } else {
            File file = new File(this.task.out);

            try (FileWriter writer = new FileWriter(file)) {
                writer.write(this.task.result);
            } catch (IOException e) {
                System.out.printf("An Error occurs %s", e.getMessage());
            }
        }
    }

}

class CryptingTask {
    public String data = "";
    public int key = 0;
    public String mode = "enc";
    public  String in;
    public  String out = "";
    public String alg = "shift";
    public String result;

    public CryptingTask(String data, int key, String mode, String alg, String in, String out) throws IOException {
        this.data = data;
        this.key = key;
        this.mode = mode;
        this.alg = alg;
        this.in = in;
        this.out = out;

        if ("".equals(data)) {
            this.data = new String(Files.readAllBytes(Paths.get(this.in)));
        }
    }
}

class EncryptProcess extends CodingProcess {
    EncryptProcess(CryptingTask task) {
        super(task);
    }

    @Override
    public void code() {

        switch(this.task.alg) {
            case "unicode":
                this.task.result = Encryption.encryptUnicode(this.task.data, this.task.key);
                break;
            case "shift":
                this.task.result = Encryption.encryptionShift(this.task.data, this.task.key);
                break;
        }
    }
}

class DecryptProcess extends CodingProcess {
    DecryptProcess(CryptingTask task) {
        super(task);
    }

    @Override
    public void code() {
        switch(this.task.alg) {
            case "unicode":
                this.task.result = Encryption.decryptUnicode(this.task.data, this.task.key);
                break;
            case "shift":
                this.task.result = Encryption.decryptionShift(this.task.data, this.task.key);
                break;
        }
    }
}

