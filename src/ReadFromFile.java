import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by 1 on 15.11.2015.
 */
public class ReadFromFile {
    private String key;
    private String text;
    private final String fileNameKey = new String("key.txt");
    private final String fileNameText = new String("text.txt");
    private final String fileNameCrText = new String("crypto.txt");

    public ReadFromFile() throws FileNotFoundException {
        Read();
    }

    public String getText() {
        return text;
    }

    public String getKey() {
        return key;
    }

    //читаем из файла пароль и текст
    private void Read() throws FileNotFoundException {
            Scanner in = null;
            try {
                in = new Scanner(new File(fileNameKey));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            key = in.nextLine();
            in.close();

            try {
                in = new Scanner(new File(fileNameText));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            text = in.nextLine();
            in.close();
            }

    public String  ReadDec() {
        Scanner in = null;
        String decrypto;
        try {
            in = new Scanner(new File(fileNameCrText));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        decrypto = in.nextLine();
        in.close();
        return decrypto;
    }
}

