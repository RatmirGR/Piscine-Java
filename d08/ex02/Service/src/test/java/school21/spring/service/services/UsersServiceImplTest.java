package school21.spring.service.services;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import school21.spring.service.config.TestApplicationConfig;
import school21.spring.service.service.UsersService;

import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UsersServiceImplTest {
    @Test
    void testSignUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(TestApplicationConfig.class);
        UsersService service = context.getBean("usersService", UsersService.class);
        assertNotNull(service.signUp("user@company.com"));
    }
}