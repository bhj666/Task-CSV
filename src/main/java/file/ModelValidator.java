package file;


import java.util.Objects;

public class ModelValidator {

    public boolean isValid(RecordModel recordModel) {
        return validateMail(recordModel.getFirstName(), recordModel.getLastName(), recordModel.getEmail());
    }

    private boolean validateMail(String firstName, String lastName, String mail) {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(mail);
        return mail.equals(String.format("%s.%s@domain.com", firstName, lastName));
    }
}
