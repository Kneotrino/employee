package com.github.kneotrino.employee.dto;


import com.github.kneotrino.employee.common.ModelMapUtil;
import com.github.kneotrino.employee.entity.Pegawai;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;

/**
 * @author Kneotrino
 * @date 29/11/20
 */
@Data
public class PegawaiDto {

  Long id;
  String nama;
  String alamat;

  public static PegawaiDto convertToDto(Pegawai entity) {
    ModelMapper modelMapper = ModelMapUtil.GetDefaultModelMapper();
    return modelMapper.map(entity, PegawaiDto.class);
  }

  public static Pegawai convertToEntity(PegawaiDto dto) {
    ModelMapper modelMapper = ModelMapUtil.GetDefaultModelMapper();
    return modelMapper.map(dto, Pegawai.class);
  }
}
