package io.nonblocking;

import io.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@RequestMapping("/v1/books")
@RestController
public class WebFluxBranchOfficeController {
    private Map<String, Book> bookMap;

    @Autowired
    public WebFluxBranchOfficeController(Map<String, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{book-id}")
    public Mono<Book> getBook(@PathVariable("book-id")String bookId) throws InterruptedException {
        Thread.sleep(5000L);

        Book book = bookMap.get(bookId);

        return Mono.just(book);
    }
}
