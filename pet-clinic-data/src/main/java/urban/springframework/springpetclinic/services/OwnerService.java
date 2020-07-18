package urban.springframework.springpetclinic.services;

import urban.springframework.springpetclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}

