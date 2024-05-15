package idk.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

@Component
public class ExceptionHandler extends AbstractHandlerExceptionResolver {


    @Override
    protected ModelAndView doResolveException(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler, Exception ex) {

        ModelAndView modelAndView = new ModelAndView("exception");
        modelAndView.getModel().put("message", ex.getMessage() + " Caused by: " +
                (ex.getCause() != null ? ex.getCause().getMessage() : ex.getMessage()));

        return modelAndView;
    }
}
