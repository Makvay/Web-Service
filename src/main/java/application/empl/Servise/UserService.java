package application.empl.Servise;

import application.empl.DTO.UserRegistrationRequest;
import application.empl.Entites.User;
import application.empl.Repo.UserRepository;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Проверка почты
    public boolean existByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return  user != null;

    }



    // для входа пароль
    public User findByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }




    // Регистрация
    public User registerUser(UserRegistrationRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // пока без шифрования
        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }







}
