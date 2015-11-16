import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ReadFromFile read = new ReadFromFile();

        System.out.println(read.getKey());
        System.out.println(read.getText());

        try {
            MD5 m = new MD5(read.getKey());
            AesCrypto crypto =new AesCrypto(m.getResult(),read.getText());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }
}
