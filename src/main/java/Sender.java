import file.RecordModel;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Sender {

    RestTemplate restTemplate = new RestTemplateBuilder()
            .rootUri(System.getenv("URI"))
            .build();


    public void sendChunks(List<RecordModel> records, Integer size) {
        for(RecordModel record : records) {
            restTemplate.postForEntity("/", record, Void.class);
        }

        //TODO: seems like List JSON format is not supported

//        StreamSupport.stream(Iterables.partition(records, size).spliterator(), false).forEach(c -> restTemplate.postForEntity("/", c, Void.class));

    }
}
