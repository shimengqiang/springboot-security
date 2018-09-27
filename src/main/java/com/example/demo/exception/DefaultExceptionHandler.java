package com.example.demo.exception;

import com.example.demo.controller.RegisterController;
import com.example.demo.vo.MessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>创建时间: 2018/9/27 19:40 </p>
 *
 * @author shimengqiang
 * @version v1.0
 */

@ControllerAdvice
public class DefaultExceptionHandler {

    private final static Logger log = LoggerFactory.getLogger(RegisterController.class);

//    /**
//     * 此方法只处理本类抛出的 RequestLimitException 异常
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(RequestLimitException.class)
//    public ResponseEntity<?>  handleEmployeeNotFoundException(HttpServletRequest req, Exception e){
//        e.printStackTrace();
//
//        MessageVO messageVO = new MessageVO("error",e.getMessage(),"异常信息");
////        ResponseEntity.ok(result);
//
//        return new ResponseEntity(messageVO, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest req, Exception e)  {

        e.printStackTrace();
        MessageVO messageVO = new MessageVO("error",e.getMessage(),"异常信息");
//        ResponseEntity.ok(result);

        return ResponseEntity.ok(messageVO);
    }


}
