package overridetech.jdbc.jpa;

import overridetech.jdbc.jpa.model.User;
import overridetech.jdbc.jpa.service.UserService;
import overridetech.jdbc.jpa.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();


        service.saveUser("Иван", "Васильев", (byte) 32);
        service.saveUser("Никита", "Воронин", (byte) 15);
        service.saveUser("Валерий", "Афанасьев", (byte) 20);
        service.saveUser("Елена", "Самсонова", (byte) 31);
        List<User> list = service.getAllUsers();
        for (User human : list) {
            System.out.println(human.toString());
        }
        service.cleanUsersTable();

    }
}
