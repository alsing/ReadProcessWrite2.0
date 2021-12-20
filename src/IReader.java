import javax.print.DocFlavor;
import java.io.FileReader;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class IReader {

    static String read(String name){
        try(FileInputStream fin = new FileInputStream(name)) {
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer, 0, fin.available());
            return new String( buffer , StandardCharsets.UTF_8 );

        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }
}
