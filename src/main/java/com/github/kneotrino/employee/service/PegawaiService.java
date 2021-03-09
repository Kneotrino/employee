package com.github.kneotrino.employee.service;


import com.github.kneotrino.employee.common.BaseService;
import com.github.kneotrino.employee.dto.PegawaiDto;
import com.github.kneotrino.employee.entity.Pegawai;
import com.github.kneotrino.employee.repository.PegawaiRepository;
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
public class PegawaiService implements BaseService<PegawaiDto> {

  final static String ENTITY_NAME = "Pegawai";

  @Autowired
  PegawaiRepository repository;

  @Override
  public PegawaiDto SelectOneAvailableById(Long id) throws NotFoundException {
    Pegawai data = repository
            .findByEnabledTrueAndId(id)
            .orElseThrow(() -> new NotFoundException(GetEntityName() + "with ID :: " + " Data not found"));
    log.info("Success Get " + ENTITY_NAME + " id : " + id);
    return PegawaiDto.convertToDto(data);
  }

  @Override
  public PegawaiDto InsertOneAvailableById(PegawaiDto data) throws NotFoundException {
    Pegawai insert = PegawaiDto.convertToEntity(data);
    insert.setId(null);
    Pegawai save = repository.save(insert);
    log.info("Success Create " + ENTITY_NAME + " id : " + save.getId());
    return PegawaiDto.convertToDto(save);
  }

  @Override
  public PegawaiDto UpdateOneAvailableById(PegawaiDto data, Long id) throws NotFoundException {
    Optional<Pegawai> update = repository.findByEnabledTrueAndId(id);
    if (update.isPresent()) {
      Pegawai entity = PegawaiDto.convertToEntity(data);
      entity.setId(id);
      System.out.println("entity = " + entity);
      Pegawai save = repository.save(entity);
      log.info("Success Update " + ENTITY_NAME + " id : " + save.getId());
      return PegawaiDto.convertToDto(save);
    } else {
      throw new NotFoundException(ENTITY_NAME + "with ID :: " + " Data not found");
    }
  }

  @Override
  public List<PegawaiDto> SelectAllAvailable() {
    List<PegawaiDto> collect = repository.findAllByEnabledTrue()
            .stream()
            .map(PegawaiDto::convertToDto)
            .collect(Collectors.toList());
    log.info("Success Get All " + ENTITY_NAME + "; Count = " + collect.size());
    return collect;
  }

  @Override
  public void DisableOneAvailableById(Long id) throws NotFoundException {
    Optional<Pegawai> fromDb = repository.findByEnabledTrueAndId(id);
    if (fromDb.isPresent()) {
      Pegawai disable = fromDb.get();
      disable.setEnabled(Boolean.FALSE);
      repository.save(disable);
      log.info("Success Disable " + ENTITY_NAME + " id : " + id);
    } else {
      throw new NotFoundException(ENTITY_NAME + "with ID :: " + " Data not found");
    }
  }

  @Override
  public PegawaiDto EnableOneAvailableById(Long id) throws NotFoundException {
    Optional<Pegawai> fromDb =
            repository.findByEnabledFalseAndId(id);
    if (fromDb.isPresent()) {
      Pegawai disable = fromDb.get();
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
  public Page<PegawaiDto> SelectByPageable(Pageable pageable) {
    Page<PegawaiDto> collect =
            repository
                    .findAllByEnabledTrue(pageable)
                    .map(PegawaiDto::convertToDto);
    log.info("Success Get All " + ENTITY_NAME + "; Count = " + collect.getContent().size() + "; Page Number =" + pageable.getPageNumber() + "; Page Size = " + pageable.getPageSize());
    return collect;
  }
}
