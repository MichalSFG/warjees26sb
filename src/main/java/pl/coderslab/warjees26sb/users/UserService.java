package pl.coderslab.warjees26sb.users;

public interface UserService {

    AppUser findByUserName(String name);

    void saveUser(AppUser appUser);
}
