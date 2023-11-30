package boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/rest")
    public String rest(int idx){
        return "Hello"+idx;
    }
}
