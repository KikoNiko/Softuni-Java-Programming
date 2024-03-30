package bg.softuni.gamestore;

import bg.softuni.gamestore.service.UserService;
import bg.softuni.gamestore.service.dtos.UserLoginDTO;
import bg.softuni.gamestore.service.dtos.UserRegisterDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final UserService userService;

    public CommandLineRunnerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        String output = "";

        while (!input.equals("END")) {
            String[] tokens = input.split("\\|");
            switch (tokens[0]) {
                case "RegisterUser":
                    output = this.userService.regiserUser(
                            new UserRegisterDTO(tokens[1], tokens[2], tokens[3], tokens[4]));
                    break;
                case "LoginUser":
                    output = this.userService.loginUser(new UserLoginDTO(tokens[1], tokens[2]));
                    break;
                case "Logout":
                    output = this.userService.logout();
                    break;
            }

            System.out.println(output);
            input = reader.readLine();
        }
    }
}
