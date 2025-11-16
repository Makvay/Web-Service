package application.empl.Repo;

import application.empl.Entites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Long> {




    User findByEmail(String email);
}
