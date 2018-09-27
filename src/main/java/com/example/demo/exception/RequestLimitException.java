package com.example.demo.exception;

/**
 * <p>创建时间: 2018/9/23 9:32 </p>
 *
 * @author shimengqiang
 * @version v1.0
 */
public class RequestLimitException extends RuntimeException{

        public RequestLimitException() {
            super("HTTP请求超出设定的限制");
        }

        public RequestLimitException(String message) {
            super(message);
        }
}
