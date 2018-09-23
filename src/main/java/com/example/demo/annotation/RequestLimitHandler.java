package com.example.demo.annotation;

import com.example.demo.exception.RequestLimitException;
import com.example.demo.utils.HttpRequestUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/**
 * <p>创建时间: 2018/9/23 9:24 </p>
 *
 * @author shimengqiang
 * @version v1.0
 */

@Aspect
@Component
public class RequestLimitHandler {

    private static final Logger logger = LoggerFactory.getLogger(RequestLimitHandler.class);

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {



        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest) {
                request = (HttpServletRequest) args[i];
                break;
            }
        }
        if (request == null) {
            throw new RequestLimitException("方法中缺失HttpServletRequest参数");
        }
        String ip = HttpRequestUtil.getIpAddress(request);
        String url = request.getRequestURL().toString();
        String key = "req_limit_".concat(url).concat(ip);


        logger.info("ip:{} ,访问:{} , key:{}",ip, url, key);

        long count = redisTemplate.opsForValue().increment(key, 1);
        if (count == 1) {
            redisTemplate.expire(key, limit.time(), TimeUnit.MILLISECONDS);
        }
        if (count > limit.count()) {
            logger.info("用户IP{}访问地址{}超过了限定的次数{}次", ip, url, limit.count());
            if(redisTemplate.delete(key)){
                logger.info("{}从redis中删除成功",key);
            }
            throw new RequestLimitException();
        }


    }














}
