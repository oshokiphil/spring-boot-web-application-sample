package gt.app.modules.user;

import gt.app.domain.AppUser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

interface UserRepository extends JpaRepository<AppUser, Long> {

    @Override
    @Cacheable("userExistsById")
    boolean existsById(Long id);

    @EntityGraph(attributePaths = {"authorities"})
    @Transactional(readOnly = true)
    @Cacheable("userWithAuthByUsername")
    Optional<AppUser> findOneWithAuthoritiesByUsername(String username);

    @Transactional(readOnly = true)
    @Cacheable("userExistsByUserName")
    boolean existsByUsername(String username);
}
