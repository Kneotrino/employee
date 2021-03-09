package com.github.kneotrino.employee.controller;

import com.github.kneotrino.employee.common.BaseModel;
import com.github.kneotrino.employee.common.BaseRestController;
import com.github.kneotrino.employee.dto.PegawaiDto;
import com.github.kneotrino.employee.service.PegawaiService;
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
@RequestMapping("/api/pegawai")
public class PegawaiRestController implements BaseRestController<PegawaiDto> {

    @Autowired
    private PegawaiService service;

    @Override
    public PegawaiDto getOneById(Long id) throws NotFoundException {
        return service.SelectOneAvailableById(id);
    }

    @Override
    public PegawaiDto postOne(PegawaiDto data) throws NotFoundException {
        return service.InsertOneAvailableById(data);
    }

    @Override
    public PegawaiDto putOneById(PegawaiDto data, Long id) throws NotFoundException {
        return service.UpdateOneAvailableById(data, id);
    }

    @Override
    public PegawaiDto restoreOneById(Long id) throws NotFoundException {
        return service.EnableOneAvailableById(id);

    }

    @Override
    public List<PegawaiDto> getAll() {
        return service.SelectAllAvailable();
    }

    @Override
    public void deleteOneById(Long id) throws NotFoundException {
        service.DisableOneAvailableById(id);
    }

    @Override
    public List<PegawaiDto> getPage(int page, int size) {
        Pageable paging = PageRequest.of(page, size, Sort.by(BaseModel.DEFAULT_SORT).descending());
        return service.SelectByPageable(paging).getContent();
    }
}
