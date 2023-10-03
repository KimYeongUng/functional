package io.nonblocking;

import io.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;

@Slf4j
@RequestMapping("/v1/books")
@RestController
public class WebFluxHeadOfficeController {
    URI baseuri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port("5050")
            .path("/v1/books")
            .build()
            .encode()
            .toUri();

    @Autowired
    public WebFluxHeadOfficeController() {}

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id") String bookId){
        URI getBookUri = UriComponentsBuilder.fromUri(baseuri)
                .path("{book-id}")
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
