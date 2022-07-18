package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.PaidUpTariff;

public interface PaidUpTariffsRepo extends CrudRepository<PaidUpTariff,Long> {
}
