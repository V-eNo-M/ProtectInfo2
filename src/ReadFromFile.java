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
}

