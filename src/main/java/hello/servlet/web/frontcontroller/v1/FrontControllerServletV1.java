package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")  // /front-controller/v1을 포함한 모든 하위 요청은
                                                                                        // 이 서블릿에서 받아들인다.
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        // key가 요청이 되면, value를 실행
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        // /front-controller/v1/members
        String requestURI = request.getRequestURI();// 주소 값을 그대로 받을 수 있다.

//        ControllerV1 controller = new MemberListControllerV1();  정확히는 new가 아닌 주소값이 들어온다.
        // 이렇게 되면 인터페이스 controller에는 구현한 객체가 들어가게되고(부모는 자식을 다 받을 수 있다) 이 값을 받아서 전달한다.
        ControllerV1 controller = controllerMap.get(requestURI);  // 인터페이스로 받으면 코드를 일관성있게 사용할 수 있다.
        // 값이 없다면
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 404
            return;
        }

        // 값이 있다면
        controller.process(request, response);

    }
}
