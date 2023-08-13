package flux;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
public class ColdHotSeq {
    public static void main(String[] args) throws InterruptedException {
        URI uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("worldtimeapi.org")
                .port(80)
                .path("/api/timezone/Asia/Seoul")
                .build()
                .encode()
                .toUri();
        
        Mono<String> mono = getWordTime(uri).cache();
        mono.subscribe(date -> log.info("#datetime1 : {}",date));
        Thread.sleep(2000);
        mono.subscribe(date->log.info("datetime2: {}",date));

        Thread.sleep(2000);
    }

    private static Mono<String> getWordTime(URI uri) {
        return WebClient
                .create()
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(String.class)
                .map(res->{
                    DocumentContext context = JsonPath.parse(res);
                    return context.read("$.datetime");
                });
    }
}
