package pl.coderslab.warjees26sb.home.animal;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AnimalRepository extends JpaRepository<Animal, Long> {

    @Query("select a from Animal a where a.name = ?1")
    Animal findByNameBlaBla(String email);
}
