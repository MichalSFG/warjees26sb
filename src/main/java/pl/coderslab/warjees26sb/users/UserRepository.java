package pl.coderslab.warjees26sb.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByEmail(String email);

    AppUser findAppUserById(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from users_roles where app_user_id=?1", nativeQuery = true)
    void deleteByUserId(Long id);

}
