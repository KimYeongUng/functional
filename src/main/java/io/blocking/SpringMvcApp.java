package io.blocking;

import io.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalTime;
import java.util.Objects;

@Slf4j
@SpringBootApplication
public class SpringMvcApp {
    private URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(8080)
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcApp.class,args);
    }

    @Bean
    public RestTemplateBuilder restTemplate(){
        return new RestTemplateBuilder();
    }

    @Bean
    public CommandLineRunner run(){
        return (String ... args) ->{
            log.info("#request time : {}", LocalTime.now());

            for (int i=0;i<5;i++){
                Book book = this.getBook(String.valueOf(i));
                log.info("{} , bookName: {}",LocalTime.now(), Objects.requireNonNull(book).getBookName());
            }
        };
    }

    private Book getBook(String bookId) {
        RestTemplate template = new RestTemplate();

        URI getBookUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri();

        ResponseEntity<Book> res = template.getForEntity(getBookUri,Book.class);
        Book book = res.getBody();

        return book;
    }
}
