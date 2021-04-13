package file;

import lombok.AllArgsConstructor;
import lombok.Getter;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static file.CsvReader.CsvHeaderKey.*;

@RequiredArgsConstructor
public class CsvReader {

    private final ModelValidator modelValidator;

    @SneakyThrows
    public List<RecordModel> read(InputStream file) {
        try(var reader = new InputStreamReader(file, StandardCharsets.UTF_8)) {
            CSVParser parsedFile = CSVFormat.DEFAULT
                    .withHeader(CsvHeaderKey.class)
                    .withDelimiter(';')
                    .withFirstRecordAsHeader()
                    .parse(reader);

            return parsedFile.getRecords()
                    .stream()
                    .map(this::toModel)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    private RecordModel toModel(CSVRecord csvRecord) {
        try {
            var record = RecordModel.builder()
                    .firstName(csvRecord.get(FIRST_NAME.getKey()))
                    .lastName(csvRecord.get(LAST_NAME.getKey()))
                    .email(csvRecord.get(EMAIL.getKey()))
                    .param0(parseList(csvRecord.get(PARAM_0.getKey())))
                    .param1(parseList(csvRecord.get(PARAM_1.getKey())))
                    .param2(parseList(csvRecord.get(PARAM_2.getKey())))
                    .param3(parseList(csvRecord.get(PARAM_3.getKey())))
                    .param4(parseList(csvRecord.get(PARAM_4.getKey())))
                    .param5(parseList(csvRecord.get(PARAM_5.getKey())))
                    .param6(parseList(csvRecord.get(PARAM_6.getKey())))
                    .param7(parseList(csvRecord.get(PARAM_7.getKey())))
                    .param8(parseList(csvRecord.get(PARAM_8.getKey())))
                    .build();
            if (!modelValidator.isValid(record)) {
                return null;
            }
            return record;
        } catch (Exception e) {
            return null;
        }
    }

    private List<String> parseList(String dataCell) {
        if (StringUtils.isBlank(dataCell)) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        boolean readingWord = false;
        for (int i = 0; i < dataCell.length(); i++) {
            if (dataCell.charAt(i) == '\'') {
                if (readingWord) {
                    String newWord = word.toString();
                    word = new StringBuilder();
                    result.add(newWord);
                }
                readingWord = !readingWord;
            } else if (readingWord) {
                word.append(dataCell.charAt(i));
            }
        }
        return result;
    }

    @Getter
    @AllArgsConstructor
    enum CsvHeaderKey {
        FIRST_NAME("FirstName"),
        LAST_NAME("LastName"),
        EMAIL("Email"),
        PARAM_0("param0"),
        PARAM_1("param1"),
        PARAM_2("param2"),
        PARAM_3("param3"),
        PARAM_4("param4"),
        PARAM_5("param5"),
        PARAM_6("param6"),
        PARAM_7("param7"),
        PARAM_8("param8");

        private String key;
    }

}

