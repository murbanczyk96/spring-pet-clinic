package urban.springframework.springpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import urban.springframework.springpetclinic.model.Speciality;

public interface SpecialityRepository extends CrudRepository<Speciality, Long> {
}
