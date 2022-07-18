package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.Indication;

public interface IndicationRepo extends CrudRepository<Indication,Long> {
}
