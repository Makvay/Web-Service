package application.empl.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class UserRegistrationRequest {

    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email обязателен")
    private String email;

    @NotBlank(message = "Пароль обязателен")
    @Size(min = 6, message = "Пароль должен быть минимум 6 символов")
    private String password;

    @NotBlank(message = "Подтверждение пароля обязательно")
    private String confirmPassword;


}