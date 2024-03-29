package sessionmanagementredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableRedisHttpSession
@SpringBootApplication
@EnableCaching
public class SessionManagementRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SessionManagementRedisApplication.class, args);
    }

}
