package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.Worker;

import java.util.List;

public interface WorkerRepo extends CrudRepository<Worker,Long> {
    List<Worker> findAllByOrderByInfoW();
    Worker findByMailW(String mail);
}
