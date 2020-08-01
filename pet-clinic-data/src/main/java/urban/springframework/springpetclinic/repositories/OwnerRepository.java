package urban.springframework.springpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import urban.springframework.springpetclinic.model.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
}
