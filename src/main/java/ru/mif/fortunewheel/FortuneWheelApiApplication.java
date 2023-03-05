package ru.mif.fortunewheel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.mif.fortunewheel.domain.User;
import ru.mif.fortunewheel.dto.data.UserData;

@SpringBootApplication
public class FortuneWheelApiApplication {

	public static void main(String[] args) {
		var user = new UserData(new User());
//		SpringApplication.run(FortuneWheelApiApplication.class, args);
	}

}
