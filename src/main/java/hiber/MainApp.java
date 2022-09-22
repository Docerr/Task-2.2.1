package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.addCar(new Car("Bugati", 32));
      userService.addCar(new Car("Lambo", 30));
      userService.addCar(new Car("Mercedec", 2));
      userService.addCar(new Car("Bmw", 322));

      List<Car> cars = userService.listCars();

      userService.add(new User(cars.get(0),"User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User(cars.get(1),"User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User(cars.get(2),"User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User(cars.get(3),"User4", "Lastname4", "user4@mail.ru"));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println(userService.getUserFromCar("Bugati", 32));

      context.close();
   }
}
