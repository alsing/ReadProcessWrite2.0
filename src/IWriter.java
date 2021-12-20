import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class IWriter {
    static void write (String fileExtension, String str) {
        try(FileOutputStream fos = new FileOutputStream("resultFile." + fileExtension)){
            byte[] buffer = str.getBytes(StandardCharsets.UTF_8);
            fos.write(buffer, 0, buffer.length);
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
