package com.salesianostriana.dam.testing.examen;


import com.salesianostriana.dam.testing.examen.dto.GetDatoMeteoDto;
import com.salesianostriana.dam.testing.examen.model.DatoMeteorologico;
import com.salesianostriana.dam.testing.examen.model.DatoMeterologicoPK;
import com.salesianostriana.dam.testing.examen.service.ServicioMeteorologico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import javax.management.ListenerNotFoundException;
import java.time.LocalDate;
import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles({"integration-test"})
@Sql(value = "patchvariable = insertTestController.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class ControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    ServicioMeteorologico servicioMeteorologico;


    @Test
    void obtenerTodos_200() throws Exception{
        DatoMeterologicoPK datoPk = new DatoMeterologicoPK("Sevilla", LocalDate.of(2024, 06, 9));
        GetDatoMeteoDto dto = new GetDatoMeteoDto("Sevilla", LocalDate.of(2024,06,9), 3.0);
        DatoMeteorologico dato = new DatoMeteorologico(datoPk, 3.0);

        Mockito.when(servicioMeteorologico.obtenerTodos()).thenReturn(List.of(dato));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(objectMapper.writeValueAsString(dato));



    }
}
