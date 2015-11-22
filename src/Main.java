import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ReadFromFile read = new ReadFromFile();

        System.out.println(read.getKey());
        System.out.println(read.getText());
        AesCrypto crypto;
        try {
            MD5 m = new MD5(read.getKey());
            crypto =new AesCrypto(m.getResult(),read.getText());
            AesDecrypto decrypto = new AesDecrypto(m.getResult(),crypto.getOut());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
