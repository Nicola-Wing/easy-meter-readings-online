package vera.repo;

import org.springframework.data.repository.CrudRepository;
import vera.models.UserAuthorities;

public interface UserAuthoritiesRepo extends CrudRepository<UserAuthorities,Long> {

    UserAuthorities findUserAuthoritiesByAuthority(String authority);
}
