package com.github.kneotrino.employee;

import com.github.kneotrino.employee.controller.EmployeeRestController;
import com.github.kneotrino.employee.dto.EmployeeDto;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PegawaiRestControllerTest {

    @Autowired
    private EmployeeRestController controller;

    @Test
    void contextLoads() {
        assertThat(controller).isNotNull();
    }

    @Test
    void getOneByUserId() throws NotFoundException, IOException {
        assertThrows(NotFoundException.class, () -> {
            controller.getOneById(0L);
        });
        assertThat(controller.getOneById(1L)).isNotNull();
    }

    @Test
    void getOneById() {
    }

    @Test
    void postOne() throws NotFoundException, IOException {
        EmployeeDto dto = new EmployeeDto();
        assertThat(controller.postOne(dto)).isNotNull();
    }

    @Test
    void putOneById() throws NotFoundException, IOException {
        EmployeeDto dto = new EmployeeDto();

        EmployeeDto trackingDto = controller.putOneById(dto, 1L);
        assertThat(trackingDto).isNotNull();
    }

    @Test
    void getAll() throws IOException, NotFoundException {
        assertThat(controller.getAll().size()).isNotNull();
    }

    @Test
    void getPage() throws IOException {
        assertThat(controller.getPage(0, 10).size()).isLessThanOrEqualTo(10);
        assertThat(controller.getPage(0, 2000).size()).isLessThanOrEqualTo(2000);
        assertThat(controller.getPage(10, 100).size()).isLessThanOrEqualTo(100);
        assertThat(controller.getPage(0, 1000).size()).isLessThanOrEqualTo(1000);
    }
}