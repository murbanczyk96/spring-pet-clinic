package urban.springframework.springpetclinic.services.map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import urban.springframework.springpetclinic.model.Owner;

import java.util.Set;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;
    final Long ownerId = 1L;
    final String lastName = "Smith";


    @BeforeEach
    void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());

        ownerMapService.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        Assertions.assertEquals(ownerId, ownerSet.size());

    }

    @Test
    void findById() {
    }

    @Test
    void saveExistingId() {
        long id = 2L;
        Owner owner2 = Owner.builder().id(id).build();
        Owner savedOwner = ownerMapService.save(owner2);
        Assertions.assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNoId(){
        Owner savedOwner = ownerMapService.save(Owner.builder().build());
        Assertions.assertNotNull(savedOwner);
        Assertions.assertNotNull(savedOwner.getId());

    }

    @Test
    void delete() {
        ownerMapService.delete(ownerMapService.findById(ownerId));
        Assertions.assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void deleteById() {

        ownerMapService.deleteById(ownerId);
        Assertions.assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void findByLastName() {
        Owner smith = ownerMapService.findByLastName(lastName);
        Assertions.assertNotNull(smith);
        Assertions.assertEquals(ownerId, smith.getId());

    }

    @Test
    void findByLastNameNotFound() {
        Owner smith = ownerMapService.findByLastName("foo");
        Assertions.assertNull(smith);


    }
}