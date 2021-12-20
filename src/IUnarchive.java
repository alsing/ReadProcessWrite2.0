import java.io.*;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class IUnarchive {
    static String unarchive(String filename) {
        try(ZipInputStream zin = new ZipInputStream(new FileInputStream(filename))) {
            ZipEntry entry;
            String name;
            long size;
            while((entry = zin.getNextEntry()) != null){
                name = entry.getName();
                size = entry.getSize();
                System.out.printf("File name: %s \t File size: %d \n", name, size);
                byte buffer[] = new byte[(int)size];

                for (int c = zin.read(), i = 0; c != -1 && i < size; c = zin.read(), i++) {
                    buffer[i] = (byte)c;
                }
                zin.closeEntry();
                return new String(buffer);
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }

        return "";
    }
}
