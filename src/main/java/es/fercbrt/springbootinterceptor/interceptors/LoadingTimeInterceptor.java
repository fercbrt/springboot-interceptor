package es.fercbrt.springbootinterceptor.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Random;

@Component("loadingTimeInterceptor")
public class LoadingTimeInterceptor implements HandlerInterceptor {

        private static final Logger logger = LoggerFactory.getLogger(LoadingTimeInterceptor.class);

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            HandlerMethod method = (HandlerMethod) handler;
            logger.info("LoadingTimeInterceptor: Entering..."+ method.getMethod().getName());
            long startTime = System.currentTimeMillis();
            request.setAttribute("startTime", startTime);
            Random random = new Random();
            int delay = random.nextInt(500);
            Thread.sleep(delay);

            if(delay % 2 == 0){
                logger.info("LoadingTimeInterceptor: Request accepted with delay: " + delay + " ms");
                return true;
            }
            else {
                logger.info("LoadingTimeInterceptor: Request rejected with delay: " + delay + " ms");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return false;
            }
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            HandlerMethod method = (HandlerMethod) handler;
            long startTime = (long) request.getAttribute("startTime");
            long endTime = System.currentTimeMillis();
            long loadingTime = endTime - startTime;
            logger.info("LoadingTimeInterceptor: Exiting..."+ method.getMethod().getName() + " - Time: " + loadingTime + " ms");
        }
}
