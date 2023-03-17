package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV2 {

    // ControllerV1과 반환만 다름
    MyView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
