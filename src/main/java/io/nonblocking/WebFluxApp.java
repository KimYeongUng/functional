package io.nonblocking;

import io.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalTime;

@Slf4j
@SpringBootApplication
public class WebFluxApp {
    URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(6060)
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public static void main(String[] args) {
        System.setProperty("reactor.netty.ioWorkerCount","1");
        SpringApplication.run(WebFluxApp.class,args);
    }

    @Bean
    public CommandLineRunner run(){
        return (String... args)->{
          log.info("#startTime: {}", LocalTime.now());
          for (int i=0;i<5;i++){
              this.getBook(String.valueOf(i))
                      .subscribe(book -> log.info("#book : {} {}",book.getBookName(),LocalTime.now()));
          }
        };
    }


    private Mono<Book> getBook(String bookId){
        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri();

        return WebClient.create()
                .get()
                .uri(getBookUri)
                .retrieve()
                .bodyToMono(Book.class);
    }
}