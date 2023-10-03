package io.blocking;

import io.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/v1/books")
public class SpringMvcHeadOfficeController {
    private final RestTemplate template;

    URI baseUri = UriComponentsBuilder.newInstance().scheme("http")
            .host("localhost")
            .port(7070)
            .path("/v1/books")
            .build()
            .encode().toUri();

    public SpringMvcHeadOfficeController(RestTemplate template) {
        this.template = template;
    }

    @GetMapping("/{book-id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Book> getBook(@PathVariable("book-id") String bookId){
        URI bookUri = UriComponentsBuilder.fromUri(baseUri)
                .path("/{book-id}")
                .build()
                .expand(bookId)
                .encode()
                .toUri();

        ResponseEntity<Book> res = template.getForEntity(bookUri,Book.class);
        Book book = res.getBody();

        return ResponseEntity.ok(book);
    }
}
