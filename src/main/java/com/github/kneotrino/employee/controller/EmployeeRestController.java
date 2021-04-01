package com.github.kneotrino.employee.controller;

import com.github.kneotrino.employee.common.BaseModel;
import com.github.kneotrino.employee.common.BaseRestController;
import com.github.kneotrino.employee.dto.EmployeeDto;
import com.github.kneotrino.employee.service.EmployeeService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/employee")
public class EmployeeRestController implements BaseRestController<EmployeeDto> {

    @Autowired
    private EmployeeService service;

    @Override
    public EmployeeDto getOneById(Long id) throws NotFoundException {
        return service.SelectOneAvailableById(id);
    }

    @Override
    public EmployeeDto postOne(EmployeeDto data) throws NotFoundException {
        return service.InsertOneAvailableById(data);
    }

    @Override
    public EmployeeDto putOneById(EmployeeDto data, Long id) throws NotFoundException {
        return service.UpdateOneAvailableById(data, id);
    }

    @Override
    public EmployeeDto restoreOneById(Long id) throws NotFoundException {
        return service.EnableOneAvailableById(id);

    }

    @Override
    public List<EmployeeDto> getAll() {
        return service.SelectAllAvailable();
    }

    @Override
    public void deleteOneById(Long id) throws NotFoundException {
        service.DisableOneAvailableById(id);
    }

    @Override
    public List<EmployeeDto> getPage(int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(BaseModel.DEFAULT_SORT).descending());
        return service.SelectByPageable(paging).getContent();
    }
}
