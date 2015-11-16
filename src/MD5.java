import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 1 on 15.11.2015.
 */
public class MD5 {
    private String result;

    public String getResult() {
        return result;
    }

    public MD5(String key) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(key.getBytes());

        //System.out.println(key.getBytes());
        byte byteData[] = md.digest();

        //конвертируем байт в шестнадцатеричный формат первым способом
        StringBuffer sb = new StringBuffer();
        for (byte aByteData : byteData) {
            sb.append(Integer.toString((aByteData & 0xff) + 0x100, 16).substring(1));
        }
        result = sb.toString();
    }
}