import java.util.Base64;

public class IDecoder {
    static String decode(String str) {
        byte[] buffer = Base64.getDecoder().decode(str);
        return new String(buffer);
    }
}
