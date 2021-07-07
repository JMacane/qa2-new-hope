package requesters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.WeatherResponse;
import org.springframework.web.client.RestTemplate;

public class WeatherRequester {
    private final String PREFIX = "https://samples.openweathermap.org/data/2.5/weather?id=";
    private final String POSTFIX = "&appid=b1b15e88fa797225412429c1c50c122a1";

    public WeatherResponse getWeatherData(long id) throws JsonProcessingException {
        String url = PREFIX + id + POSTFIX;

        //We are using spring-web to make quaries and get response (as String)
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForEntity(url, String.class).getBody();

        //We are using Jackson to get objects with data as model from JSON (to convert/map)
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, WeatherResponse.class);
    }

}
