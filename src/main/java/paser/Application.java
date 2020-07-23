package paser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import paser.util.VerifyUtil;

@RestController
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/test")
    public void showBrokerage(@RequestBody Mytest mytest) {
        System.out.println(mytest);
        Mytest mytest1 = new Mytest();
        mytest1.setMima("b7cba872952fb362e6912722eeaf1c5f");
        VerifyUtil<Mytest> verifyUtil = new VerifyUtil<>(mytest1,mytest);
        System.out.println(verifyUtil.interceptCompareAllField(true));
        //===============================================================
        Mytest mytest2 = new Mytest();
        mytest2.setMima("w");
        Mytest mytest3 = new Mytest();
        mytest3.setMima("b7cba872952fb362e6912722eeaf1c5f");
        verifyUtil.replace(mytest2,mytest3);
        System.out.println(verifyUtil.interceptCompareAllField(false));
    }
}
