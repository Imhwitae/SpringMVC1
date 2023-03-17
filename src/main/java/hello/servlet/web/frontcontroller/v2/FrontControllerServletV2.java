package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// /front-controller/v2/members/new-form
@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")  // /front-controller/v1을 포함한 모든 하위 요청은
                                                                                        // 이 서블릿에서 받아들인다.
public class FrontControllerServletV2 extends HttpServlet {

    private Map<String, ControllerV2> controllerMap = new HashMap<>();

    public FrontControllerServletV2() {
        // key가 요청이 되면, value를 실행
        controllerMap.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMap.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMap.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        // /front-controller/v1/members
        String requestURI = request.getRequestURI();// 주소 값을 그대로 받을 수 있다.

//        ControllerV1 controller = new MemberListControllerV1();  정확히는 new가 아닌 주소값이 들어온다.
        // 이렇게 되면 인터페이스 controller에는 구현한 객체가 들어가게되고(부모는 자식을 다 받을 수 있다) 이 값을 받아서 전달한다.
        ControllerV2 controller = controllerMap.get(requestURI);  // 인터페이스로 받으면 코드를 일관성있게 사용할 수 있다.
        // 값이 없다면
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);  // 404
            return;
        }

        // new MyView("/WEB-INF/views/new-form.jsp);
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
