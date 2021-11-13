package com.craffic.practice.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 只需要定义CustomExceptionHandler，再加上@ControllerAdvice注解即可
 * 当系统启动时，该类就会被扫描到Spring容器中
 * 然后定义uploadException方法，在该方法上添加@ExceptionHandler注解，注解中的MaxUploadSizeExceededException，只是用来处理MaxUploadSizeExceededException的异常，如果想处理所有异常可以替换为Exception
 *
 */

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public void uploadException(MaxUploadSizeExceededException e1, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        writer.write("上传的文件大小超出限制！");
        writer.flush();
        writer.close();
    }

}
