package edu.school21.app;

import edu.school21.models.User;
import edu.school21.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
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
