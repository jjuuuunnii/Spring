package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        System.out.println("name = " + name);
        /**
         * sout을 사용하지 않는 이유
         * 모든 것을 다출력하는데,,, 이러면 라이브에서는 ㅈ댐
         *
         * log에서 {}을 사용하는 이유
         * 레벨을 지정할수 있음!!
         *
         * info log = + name 이렇게 안하는 이유
         * 연산이 일어나서 먼저 갖고 있음, 그래서 메모리랑 등등 먹게됨
         */

        log.trace(" trace log={}",name);
        log.debug(" debug log={}",name);
        log.info(" info log={}",name);
        log.warn(" warn log={}",name);
        log.error(" error log={}", name);

        return "ok";
    }
}
