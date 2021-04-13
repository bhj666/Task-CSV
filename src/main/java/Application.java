import file.CsvReader;
import file.FileEncryptor;
import file.ModelValidator;

public class Application {

    public static void main(String[] args) {
        FileEncryptor encryptor = new FileEncryptor();
        ModelValidator validator = new ModelValidator();
        CsvReader reader = new CsvReader(validator);
        Sender sender = new Sender();
        sender.sendChunks(reader.read(encryptor.unpack()), 100);
    }
}
