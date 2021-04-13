package file;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordModel {

    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Email")
    private String email;
    private List<String> param0;
    private List<String> param1;
    private List<String> param2;
    private List<String> param3;
    private List<String> param4;
    private List<String> param5;
    private List<String> param6;
    private List<String> param7;
    private List<String> param8;
}
