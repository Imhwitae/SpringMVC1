package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

// Controller 역할
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

    // 고객의 요청이 오면
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";  // 경로. 여기서 WEB-INF는 주소창에 페이지 파일을 직접 쳐서 부르게 하지 않고 싶을 때.
                                                        // 컨트롤러를 항상 거쳐서 가게 하려면 해당 폴더에 넣으면 된다.

        // 서버 내부에서 서로 호출
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);// Controller에서 View로 이동할 때 사용
        dispatcher.forward(request, response);  // 다른 서블릿이나 jsp로 이동하는 기능. 서버 내부에서 다시 호출이 발생.
                                                // 웹브라우저에게 다시 호출하는 redirect가 아님.

        /*
            redirect vs forward
            리다이렉트는 웹브라우저에 응답이 나갔다가 클라이언트가 redirect경로로 다시 요청한다. 따라서 클라이언트가 인지할 수 있고 URL도 변경된다.
            포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.
         */
    }
}
