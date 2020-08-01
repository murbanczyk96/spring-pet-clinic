package urban.springframework.springpetclinic.repositories;

import org.springframework.data.repository.CrudRepository;
import urban.springframework.springpetclinic.model.Vet;

public interface VetRepository extends CrudRepository<Vet, Long> {
}
