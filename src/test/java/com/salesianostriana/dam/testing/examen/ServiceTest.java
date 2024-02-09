package com.salesianostriana.dam.testing.examen;

import com.salesianostriana.dam.testing.examen.model.DatoMeteorologico;
import com.salesianostriana.dam.testing.examen.model.DatoMeterologicoPK;
import com.salesianostriana.dam.testing.examen.service.ServicioMeteorologico;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    RepositoryTestTemplate repositoryTestTemplate;

    @InjectMocks
    ServicioMeteorologico servicioMeteorologico;

    @Test
    void  obtenerTodosTest(){
        LocalDate localDate = LocalDate.now();
        DatoMeterologicoPK d1pk = new DatoMeterologicoPK("Sevilla", localDate);


        DatoMeteorologico d1 = new DatoMeteorologico();
        d1.setId(d1pk);
        d1.setPrecipitacion(2.5);


    }
}
