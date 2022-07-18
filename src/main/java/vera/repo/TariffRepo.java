package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.Tariff;

public interface TariffRepo extends CrudRepository<Tariff,Long> {
}
