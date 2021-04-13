import file.ModelValidator;
import file.RecordModel;
import junit.framework.TestCase;
import org.junit.Test;

public class ModelValidatorTest {

    private static final String LAST_NAME = "lastName";
    private static final String FIRST_NAME = "firstName";
    private static final String VALID_MAIL = FIRST_NAME + "." + LAST_NAME + "@domain.com";
    private ModelValidator modelValidator = new ModelValidator();

    @Test
    public void shouldBeValid() {
       var result = modelValidator.isValid(RecordModel.builder()
               .lastName(LAST_NAME)
               .firstName(FIRST_NAME)
               .email(VALID_MAIL)
               .build());

       TestCase.assertTrue(result);
    }

    @Test
    public void invalidMail() {
        var result = modelValidator.isValid(RecordModel.builder()
                .lastName(LAST_NAME)
                .firstName(FIRST_NAME)
                .email("invalid" + VALID_MAIL)
                .build());

        TestCase.assertFalse(result);
    }

    @Test(expected = NullPointerException.class)
    public void emptyFirstName() {
        var result = modelValidator.isValid(RecordModel.builder()
                .lastName(LAST_NAME)
                .email(VALID_MAIL)
                .build());
    }

    @Test(expected = NullPointerException.class)
    public void emptyLastName() {
        var result = modelValidator.isValid(RecordModel.builder()
                .firstName(FIRST_NAME)
                .email(VALID_MAIL)
                .build());
    }

    @Test(expected = NullPointerException.class)
    public void emptyMail() {
        var result = modelValidator.isValid(RecordModel.builder()
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .build());
    }
}
