package urban.springframework.springpetclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import urban.springframework.springpetclinic.model.Owner;
import urban.springframework.springpetclinic.model.Pet;
import urban.springframework.springpetclinic.model.PetType;
import urban.springframework.springpetclinic.services.PetService;
import urban.springframework.springpetclinic.services.VisitService;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
class VisitControllerTest {

    @Mock
    VisitService visitService;

    @Mock
    PetService petService;

    @InjectMocks
    VisitController visitController;

    MockMvc mockMvc;

    Pet pet;


    @BeforeEach
    void setUp() {

        when(petService.findById(anyLong()))
                .thenReturn(Pet.builder()
                        .id(1L)
                        .birthDate(LocalDate.of(2000, 12, 12))
                        .name("Alfa")
                        .visits(new HashSet<>())
                        .owner(Owner.builder()
                                .id(1L)
                                .lastName("Doe")
                                .firstName("Joe")
                                .build())
                        .petType(PetType.builder()
                                .name("Dog")
                                .build())
                        .build());

        mockMvc = MockMvcBuilders
                .standaloneSetup(visitController)
                .build();
    }


    @Test
    void initNewVisitForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/owners/1/pets/1/visits/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("pets/createOrUpdateVisitForm"));
    }

    @Test
    void processNewVisitForm() throws Exception {
        mockMvc.perform(post("/owners/1/pets/1/visits/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("visit"));


        verify(visitService).save(any());
    }


}