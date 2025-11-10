package application.empl.Repo;

import application.empl.Entites.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

    boolean existsByEmail(String email);


    User findByEmail(String email);
}
