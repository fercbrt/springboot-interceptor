package es.fercbrt.springbootinterceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

        private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HandlerMethod method = (HandlerMethod) handler;
            logger.info("LoadingTimeInterceptor: Entering..."+ method.getMethod().getName());
            return true;
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerMethod method = (HandlerMethod) handler;
            logger.info("LoadingTimeInterceptor: Exiting..." + method.getMethod().getName());
        }
}
