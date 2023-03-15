package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // [status-line]
        response.setStatus(HttpServletResponse.SC_OK);  // 200을 상수로 지정한 값
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);  // 400을 상수로 지정한 값

        // [response-header]
        response.setHeader("Content-type", "text/plain;charset=utf-8");  // content 메서드에 작성한 reponse.setContentType,
//                                                                            response.setCharacterEncoding으로 인해 주석처리해도 오류가 안생김
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");  // 캐시를 무효함 등..
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");  // 내가 원하는 임의의 헤더 http 응답으로 이 헤더가 나감

        // [Header 편의 메서드] 위처럼 하나하나 작성하지말고 메서드로 만들어서 깔끔하게 설정할 수 있다.
//        content(response);
//        cookie(response);
        redirect(response);


        // [Message Body]
        PrintWriter writer = response.getWriter();
        writer.print("OK");  // write()와 print() 둘다 문자를 출력할 때 지원하는 기능이다.
    }

    private void content(HttpServletResponse response) {
        // Content-Type: text/plain;charset=utf-8
        // Content-Length: 2
        // response.setHeader("Content-Type", "text/plain;charset=utf-8");

        // 위의 문법과 같은 효과
        response.setContentType("text/plain");  // setContentType 메서드로 "text/plain"을 주면 자동으로 인코딩까지 설정해준다.
        response.setCharacterEncoding("utf-8");
        // response.setContentLength(2);  // 생략시 자동 생성
    }

    private void cookie(HttpServletResponse response) {
        // Set-cookie: myCookie=good; Max-Age=600;  // cookie도 httpHeader에 넣을 수 있다.
        // response.setHeader("set-Cookie", "myCookie=good; Max-Age=600");

        // 위의 문법과 같은 효과
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);  // 600초
        response.addCookie(cookie);
    }

    private void redirect(HttpServletResponse response) throws IOException{
        // Status Code 302 -> Http 상태코드를 302로 만든다
        // Location: /basic/hello-form.html -> 해당 주소로 보낸다.

        // 위의 조건 만들기
        // response.setStatus(HttpServletResponse.SC_FOUND);  // 302
        // response.setHeader("Location", "/basic/hello-form.html");

        // 위와 같은 행동을 코드 한 줄로 가능
        response.sendRedirect("/basic/hello-form.html");
    }
}
