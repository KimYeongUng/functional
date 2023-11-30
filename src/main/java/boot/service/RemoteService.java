package boot.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RemoteService {
    @RestController
    public class MyController {

        @GetMapping("/remote-service")
        public String rest(int idx){
            return "Hello"+idx;
        }
    }


    public static void main(String[] args) {
        System.setProperty("SERVER_PORT","8081");
        SpringApplication.run(RemoteService.class,args);
    }
}
