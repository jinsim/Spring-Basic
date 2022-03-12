package web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
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

    @RequestMapping("log-demo") // log-demo 요청이 오면 실행
    @ResponseBody // 뷰 화면 없이 문자를 바로 반환
    // HttpServletRequest로 받으면, 자바에서 제공하는 표준 서블릿 규약에 의한 HTTP request 정보를 받을 수 있따.
    public String logDemo(HttpServletRequest request) {
        // 고객이 어떤 URL로 요청했는지 알 수 있다.
        String requestURL = request.getRequestURI().toString();
        // request 스코프에 url 정보를 넣는다.
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
