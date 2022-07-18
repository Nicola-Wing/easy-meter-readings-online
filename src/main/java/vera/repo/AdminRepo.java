package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.Admin;

public interface AdminRepo extends CrudRepository<Admin,Long> {
    Admin findByMailA(String mail);
}
