package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
// 생성자에 Autowired가 들어가면서, 필드들을 자동 주입
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger; // request 스코프
    // MyLogger를 주입받는 것이 아니라, MyLogger를 찾을 수 있는 DL할 수 있는 애가 주입됨. 의존관계 주입시점에 주입받아도 됨.
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo") // log-demo 요청이 오면 실행
    @ResponseBody // 뷰 화면 없이 문자를 바로 반환
    // HttpServletRequest로 받으면, 자바에서 제공하는 표준 서블릿 규약에 의한 HTTP request 정보를 받을 수 있따.
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        // 고객이 어떤 URL로 요청했는지 알 수 있다.
        String requestURL = request.getRequestURI().toString();

        // 프록시에 대한 결과가 어떻게 나오는지 확인. 
        System.out.println("myLogger = " + myLogger.getClass());

//        MyLogger myLogger = myLoggerProvider.getObject();

        // request 스코프에 url 정보를 넣는다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        Thread.sleep(1000);  // 1초 기다렸다가 실행
        logDemoService.logic("testId");
        return "OK";
    }
}
