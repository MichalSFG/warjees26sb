package pl.coderslab.warjees26sb.home.animal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Animal(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal() {
    }

    public static AnimalBuilder builder() {
        return new AnimalBuilder();
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static class AnimalBuilder {
        private Long id;
        private String name;

        AnimalBuilder() {
        }

        public Animal.AnimalBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Animal.AnimalBuilder name(String name) {
            this.name = name;
            return this;
        }

        public Animal build() {
            return new Animal(id, name);
        }

        public String toString() {
            return "Animal.AnimalBuilder(id=" + this.id + ", name=" + this.name + ")";
        }
    }
}
