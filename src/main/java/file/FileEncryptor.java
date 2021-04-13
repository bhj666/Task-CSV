package file;


import lombok.SneakyThrows;
import net.lingala.zip4j.ZipFile;

import java.io.FileInputStream;
import java.io.InputStream;

public class FileEncryptor {

    @SneakyThrows
    public InputStream unpack()
    {
        ZipFile zipFile = new ZipFile(System.getenv("FILE_LOCATION") + System.getenv("FILE_TO_DECRYPT_NAME"));

        if (zipFile.isEncrypted())
        {
            zipFile.setPassword(System.getenv("FILE_PASSWORD").toCharArray());
        }

        zipFile.extractAll(System.getenv("FILE_LOCATION"));
        return new FileInputStream(System.getenv("FILE_LOCATION") + System.getenv("FILE_AFTER_DECRYPT_NAME"));
    }
}
