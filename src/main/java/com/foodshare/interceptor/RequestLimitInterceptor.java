package com.foodshare.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foodshare.controller.RequestLimit;
import com.foodshare.controller.RequestLimitException;
import com.foodshare.model.ApiResp;
import com.foodshare.model.BaseEnumError;
import com.foodshare.redis.RedisUtil;
import com.foodshare.utils.IPUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.boot.SpringApplication;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

@Aspect
@Component
public class RequestLimitInterceptor {

    private static final Log logger = LogFactory.getLog(SpringApplication.class);
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private ObjectMapper objectMapper;

    @Before("within(@org.springframework.web.bind.annotation.RequestMapping * ) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
        try {
            // 获取 HttpServletRequest
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            HttpServletResponse response = null;
            for (Object arg : args) {
                if (request != null && response != null) break;
                if (arg instanceof HttpServletRequest) {
                    request = (HttpServletRequest) arg;
                } else if (arg instanceof HttpServletResponse) {
                    response = (HttpServletResponse) arg;
                }
            }

            if (request == null || response == null) {
                throw new RequestLimitException("调用方法中缺少参数");
            }

            String ip = IPUtil.getIpByRequest(request);
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);


            if (!redisUtil.hasKey(key)) {//不存在
                redisUtil.executePipelined((RedisCallback<String>) redisConnection -> {
                    byte[] byteKey = key.getBytes(StandardCharsets.UTF_8);
                    redisConnection.incr(byteKey);
                    redisConnection.expire(byteKey, limit.time());
                    return null;
                });
            } else {
                long count = redisUtil.incr(key,1);
                if (count > limit.count()) {
                    logger.info(String.format("用户IP[%s], 访问地址[%s], 超过了限定的次数[%s]", ip, url, limit.count()));
                    response.setContentType("application/json");
                    objectMapper.writeValue(response.getOutputStream(),
                            ApiResp.retFail(BaseEnumError.SERVICE_LIMIT));
//                    throw new RequestLimitException();
                }
            }

        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常", e);
        }
    }

}
