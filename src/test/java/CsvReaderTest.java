import file.CsvReader;
import file.ModelValidator;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;

public class CsvReaderTest {

    private CsvReader reader;

    @Before
    public void setup() {
        ModelValidator validator = new ModelValidator();
        reader = new CsvReader(validator);
    }

    @Test
    public void shouldReadAllValidRecords() {
        var result = reader.read(getTestStream("test.csv"));

        assertEquals("Only one record should be parsed", 1, result.size());
        var record = result.get(0);
        assertEquals("DK76J.SKBMIZ@domain.com", record.getEmail());
        assertEquals(2, record.getParam0().size());
        assertEquals(2, record.getParam1().size());
        assertEquals(2, record.getParam2().size());
        assertEquals(2, record.getParam3().size());
        assertEquals(2, record.getParam4().size());
        assertEquals(2, record.getParam5().size());
        assertEquals(2, record.getParam6().size());
        assertEquals(2, record.getParam7().size());
        assertEquals(2, record.getParam8().size());
        assertEquals("ORKV1", record.getParam8().get(0));
    }

    private InputStream getTestStream(String testCase) {
        return getClass().getClassLoader().getResourceAsStream(testCase);
    }
}
