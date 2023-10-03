package io.blocking;

import io.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/books")
public class SpringBranchOfficeController {
    private Map<String, Book> bookMap;

    @Autowired
    public SpringBranchOfficeController(Map<String, Book> bookMap) {
        this.bookMap = bookMap;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("{book-id}")
    public ResponseEntity<Book> getBook(@PathVariable("book-id")String bookId) throws InterruptedException {
        Thread.sleep(5000L);

        Book book = bookMap.get(bookId);

        return ResponseEntity.ok(book);
    }
}
