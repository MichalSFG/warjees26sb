package pl.coderslab.warjees26sb.users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    AppUser findByEmail(String email);

    Optional<AppUser> findById(Long id);

    AppUser getUserById(Long id);

    void saveUser(AppUser appUser);

    void updateUser(AppUser appUser);

    void updateUsers();

    List<AppUser> findAll();

    void detachUserFromRole(Long id);

    void delete(AppUser appUser);

}
