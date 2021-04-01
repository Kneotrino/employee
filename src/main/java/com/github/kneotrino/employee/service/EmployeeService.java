package com.github.kneotrino.employee.service;


import com.github.kneotrino.employee.common.BaseService;
import com.github.kneotrino.employee.dto.EmployeeDto;
import com.github.kneotrino.employee.entity.Employee;
import com.github.kneotrino.employee.repository.EmployeeRepository;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Kneotrino
 * @date 17/12/20
 */
@Slf4j
@Service
public class EmployeeService implements BaseService<EmployeeDto> {

  final static String ENTITY_NAME = "Pegawai";

  @Autowired
  EmployeeRepository repository;

  @Override
  public EmployeeDto SelectOneAvailableById(Long id) throws NotFoundException {
    Employee data = repository
            .findByEnabledTrueAndId(id)
            .orElseThrow(() -> new NotFoundException(GetEntityName() + "with ID :: " + " Data not found"));
    log.info("Success Get " + ENTITY_NAME + " id : " + id);
    return EmployeeDto.convertToDto(data);
  }

  @Override
  public EmployeeDto InsertOneAvailableById(EmployeeDto data) throws NotFoundException {
    Employee insert = EmployeeDto.convertToEntity(data);
    insert.setId(null);
    Employee save = repository.save(insert);
    log.info("Success Create " + ENTITY_NAME + " id : " + save.getId());
    return EmployeeDto.convertToDto(save);
  }

  @Override
  public EmployeeDto UpdateOneAvailableById(EmployeeDto data, Long id) throws NotFoundException {
    Optional<Employee> update = repository.findByEnabledTrueAndId(id);
    if (update.isPresent()) {
      Employee entity = EmployeeDto.convertToEntity(data);
      entity.setId(id);
      System.out.println("entity = " + entity);
      Employee save = repository.save(entity);
      log.info("Success Update " + ENTITY_NAME + " id : " + save.getId());
      return EmployeeDto.convertToDto(save);
    } else {
      throw new NotFoundException(ENTITY_NAME + "with ID :: " + " Data not found");
    }
  }

  @Override
  public List<EmployeeDto> SelectAllAvailable() {
    List<EmployeeDto> collect = repository.findAllByEnabledTrue()
            .stream()
            .map(EmployeeDto::convertToDto)
            .collect(Collectors.toList());
    log.info("Success Get All " + ENTITY_NAME + "; Count = " + collect.size());
    return collect;
  }

  @Override
  public void DisableOneAvailableById(Long id) throws NotFoundException {
    Optional<Employee> fromDb = repository.findByEnabledTrueAndId(id);
    if (fromDb.isPresent()) {
      Employee disable = fromDb.get();
      disable.setEnabled(Boolean.FALSE);
      repository.save(disable);
      log.info("Success Disable " + ENTITY_NAME + " id : " + id);
    } else {
      throw new NotFoundException(ENTITY_NAME + "with ID :: " + " Data not found");
    }
  }

  @Override
  public EmployeeDto EnableOneAvailableById(Long id) throws NotFoundException {
    Optional<Employee> fromDb =
            repository.findByEnabledFalseAndId(id);
    if (fromDb.isPresent()) {
      Employee disable = fromDb.get();
      disable.setEnabled(Boolean.TRUE);
      repository.save(disable);
      log.info("Success Restore " + ENTITY_NAME + " id : " + id);
    } else {
      throw new NotFoundException(GetEntityName() + "with ID :: " + " Data not found");
    }
    return SelectOneAvailableById(id);
  }

  @Override
  public String GetEntityName() {
    return ENTITY_NAME;
  }

  @Override
  public Page<EmployeeDto> SelectByPageable(Pageable pageable) {
    Page<EmployeeDto> collect =
            repository
                    .findAllByEnabledTrue(pageable)
                    .map(EmployeeDto::convertToDto);
    log.info("Success Get All " + ENTITY_NAME + "; Count = " + collect.getContent().size() + "; Page Number =" + pageable.getPageNumber() + "; Page Size = " + pageable.getPageSize());
    return collect;
  }
}
