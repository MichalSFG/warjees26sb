package pl.coderslab.warjees26sb.users;

import java.util.List;

public interface UserService {

    AppUser findByEmail(String email);

    void saveUser(AppUser appUser);

    void updateUser(AppUser appUser);

    void updateUsers();

    List<AppUser> findAll();

}
