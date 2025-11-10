package application.empl.Controllers;

import application.empl.DTO.UserLoginRequest;
import application.empl.DTO.UserRegistrationRequest;
import application.empl.Entites.User;
import application.empl.Servise.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Binding;
import java.time.LocalDateTime;

@Controller
public class PageController {


    private final UserService userService;

    public PageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping ("/")
    public String getIndex0() {
        return "home";
    }

    @GetMapping ("/home")
    public String getIndex() {
        return "home";
    }

    ////////////////////////////////////////////////////////////////////

    @GetMapping("/user-login")
    public String getLoginPage(Model model) {
        model.addAttribute("loginRequest", new UserLoginRequest());
        return "user-login";
    }


    @PostMapping("/user-login")
    public String postUserLogin(@RequestParam(required = false) String email,
                                @RequestParam(required = false) String username,
                                @RequestParam String password,
                                Model model,
                                HttpSession session) {

        System.out.println("=== LOGIN ATTEMPT ===");
        System.out.println("Email: " + email);
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);

        String userEmail = (email != null) ? email : username;
        System.out.println("Using email: " + userEmail);

        User user = userService.findByEmailAndPassword(userEmail, password);
        System.out.println("Found user: " + user);

        if (user == null) {
            System.out.println("LOGIN FAILED - user not found");
            model.addAttribute("error", "Invalid email or password");
            return "user-login";
        }

        System.out.println("LOGIN SUCCESS - user: " + user.getEmail());
        session.setAttribute("currentUser", user);

        return "redirect:/user-profile";
    }




















    ////////////////////////////////////////////////////////////////////

    @GetMapping("/user-regist")
    public String getUserRegist(Model model) {
        model.addAttribute("registrationRequest", new UserRegistrationRequest());

        return "user-regist";
    }


    @PostMapping("/user-regist")
    public String postUserRegist(@ModelAttribute @Valid UserRegistrationRequest request,
                                 BindingResult result, Model model) {

        // Остаёмся с ошибками на странице дальше не пускаем
        if (result.hasErrors()) {
            return "user-regist";
        }

        // Проверка существования пароля
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("passwordError",  request);
            return "user-regist";
        }
        // Проверка существования почты
        if (userService.existByEmail(request.getEmail())) {
            model.addAttribute("passwordError", "The passwords don't match.");
            model.addAttribute("registrationRequest", request);
            return "user-regist";
        }


        // Регистрируем пользователя и автоматически "логиним" его
        User user = userService.registerUser(request);

        // Сохраняем пользователя в сессии (пока просто в модели)
        model.addAttribute("currentUser", user);






        return "redirect:/user-profile";
    }

















    ////////////////////////////////////////////////////////////////////
    @GetMapping("/user-profile")
    public String getUserProfile(Model model, HttpSession session) {
        // Получаем пользователя из сессии (после входа/регистрации)
        User currentUser = (User) session.getAttribute("currentUser");

        // Если пользователь не авторизован - отправляем на вход
        if (currentUser == null) {
            return "redirect:/user-login";
        }

        // Используем реальные данные пользователя
        model.addAttribute("user", currentUser);
        model.addAttribute("postCount", 0);  // пока заглушка для статистики
        model.addAttribute("likeCount", 0);
        model.addAttribute("commentCount", 0);

        return "user-profile";
    }


















    ////////////////////////////////////////////////////////////////////
    @GetMapping("/settings")
    public String getSettings(Model model) {
        return "settings";
    }


    @GetMapping("/create")
    public String getCreatePage() {
        return "create";
    }




    @GetMapping("/logout")
    public String getLogout() {
        return "redirect:/home";
    }










}
