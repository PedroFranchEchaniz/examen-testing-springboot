package com.salesianostriana.dam.testing.examen;

import com.salesianostriana.dam.testing.examen.exception.RepeatedValueException;
import com.salesianostriana.dam.testing.examen.model.DatoMeteorologico;
import com.salesianostriana.dam.testing.examen.model.DatoMeterologicoPK;
import com.salesianostriana.dam.testing.examen.repo.DatoMeteorologicoRepository;
import com.salesianostriana.dam.testing.examen.service.ServicioMeteorologico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ServiceTest {

    @Mock
    DatoMeteorologicoRepository repository;

    @InjectMocks
    ServicioMeteorologico servicioMeteorologico;

    @Test
    void  obtenerTodosTest(){
        LocalDate fecha = LocalDate.of(2024,06,9);
        DatoMeterologicoPK d1pk = new DatoMeterologicoPK("Sevilla", fecha);

        LocalDate localDate1 = LocalDate.of(2024,05,4);
        DatoMeterologicoPK d2pk = new DatoMeterologicoPK("Huelva", localDate1);

        DatoMeteorologico d1 = new DatoMeteorologico();
        d1.setId(d1pk);
        d1.setPrecipitacion(2.5);

        DatoMeteorologico d2 = new DatoMeteorologico();
        d2.setId(d2pk);
        d2.setPrecipitacion(3.0);

        Mockito.when(repository.findAll()).thenReturn(List.of(d1, d2));
        Assertions.assertEquals(2, servicioMeteorologico.obtenerTodos());
    }

    @Test
    void mediaMensualTest(){
        LocalDate localDate = LocalDate.of(2024,01,9);
        DatoMeterologicoPK d1pk = new DatoMeterologicoPK("Sevilla", localDate);

        LocalDate localDate1 = LocalDate.of(2023,05,4);
        DatoMeterologicoPK d2pk = new DatoMeterologicoPK("Huelva", localDate1);

        LocalDate localDate2 = LocalDate.of(2024,01,2);
        DatoMeterologicoPK d3pk = new DatoMeterologicoPK("Sevilla", localDate2);

        DatoMeteorologico d1 = new DatoMeteorologico();
        d1.setId(d1pk);
        d1.setPrecipitacion(3.0);

        DatoMeteorologico d2 = new DatoMeteorologico();
        d2.setId(d2pk);
        d2.setPrecipitacion(3.0);

        DatoMeteorologico d3 = new DatoMeteorologico();
        d2.setId(d3pk);
        d2.setPrecipitacion(3.0);

        Mockito.when(repository.buscarPorPoblacion("Sevilla")).thenReturn(List.of(d1, d3));

        Assertions.assertEquals(3.0, servicioMeteorologico.mediaMensual("Sevilla"));

    }

    @Test
    void insertarTest(){
        LocalDate localDate = LocalDate.of(2024,01,9);
        DatoMeterologicoPK d1pk = new DatoMeterologicoPK("Sevilla", localDate);

        LocalDate localDate1 = LocalDate.of(2023,05,4);
        DatoMeterologicoPK d2pk = new DatoMeterologicoPK("Huelva", localDate1);

        LocalDate localDate2 = LocalDate.of(2024,01,9);
        DatoMeterologicoPK d3pk = new DatoMeterologicoPK("Sevilla", localDate2);

        DatoMeteorologico d1 = new DatoMeteorologico();
        d1.setId(d1pk);
        d1.setPrecipitacion(3.0);

        DatoMeteorologico d2 = new DatoMeteorologico();
        d2.setId(d2pk);
        d2.setPrecipitacion(3.0);

        DatoMeteorologico d3 = new DatoMeteorologico();
        d2.setId(d3pk);
        d2.setPrecipitacion(3.0);

        Mockito.when(repository.existePorFechaPoblacion(LocalDate.now(), "sevilla")).thenThrow(RepeatedValueException.class, ()- >{
           repository.existePorFechaPoblacion(LocalDate.now(), "Sevilla");
        })
    }


}
