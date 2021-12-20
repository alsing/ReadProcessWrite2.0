import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class IArchive {
    static void archive(String fileExtension, String str) {
        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("resultArchive.zip"))){
            ZipEntry entry = new ZipEntry("resultFile." + fileExtension);
            zout.putNextEntry(entry);
            zout.write(str.getBytes(StandardCharsets.UTF_8));
            zout.closeEntry();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
