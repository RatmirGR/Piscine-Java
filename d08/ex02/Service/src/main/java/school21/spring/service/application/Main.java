package school21.spring.service.application;

import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.User;
import school21.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);

        User newUser = new User(6L, "john@company.com");

        System.out.println(usersRepository.findAll());

        for (User user : usersRepository.findAll()) {
            System.out.println(user.getEmail());
        }
        System.out.println("------------------------------------");
        usersRepository.save(newUser);

        for (User user : usersRepository.findAll()) {
            System.out.println(user.getEmail());
        }
        System.out.println("------------------------------------");

        usersRepository.delete(6L);

        for (User user : usersRepository.findAll()) {
            System.out.println(user.getEmail());
        }
        System.out.println("---");

        usersRepository = context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class);

        System.out.println(usersRepository.findAll());
        for (User user : usersRepository.findAll()) {
            System.out.println(user.getEmail());
        }
    }
}
