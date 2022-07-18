package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.User;


public interface UserRepo extends CrudRepository<User,Long> {

    User findByUsername(String name);

    User findAllById(Long id);
    User findByMailU(String mail);
}
