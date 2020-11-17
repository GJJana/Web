package webprog.aud1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class Aud1Application {

    public static void main(String[] args) {
        SpringApplication.run(Aud1Application.class, args);
    }


}
