package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.Service;

import java.util.List;

public interface ServiceRepo extends CrudRepository<Service,Long> {
    List<Service> findByWorkerIdW(Long idw);
    List<Service> findAllByOrderByDateS();
}
