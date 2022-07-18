package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.Request;

public interface RequestRepo extends CrudRepository<Request,Long> {
}
