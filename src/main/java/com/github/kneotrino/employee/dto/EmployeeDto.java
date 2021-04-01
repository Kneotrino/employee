package com.github.kneotrino.employee.dto;


import com.github.kneotrino.employee.common.ModelMapUtil;
import com.github.kneotrino.employee.constant.GenderEnum;
import com.github.kneotrino.employee.entity.Employee;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author Kneotrino
 * @date 29/11/20
 */
@Data
public class EmployeeDto {

  String employeeId;
  String FullName;
  String Address;
  String role;
  String position;
  String department;
  GenderEnum Gender;

  public static EmployeeDto convertToDto(Employee entity) {
    ModelMapper modelMapper = ModelMapUtil.GetDefaultModelMapper();
    return modelMapper.map(entity, EmployeeDto.class);
  }

  public static Employee convertToEntity(EmployeeDto dto) {
    ModelMapper modelMapper = ModelMapUtil.GetDefaultModelMapper();
    return modelMapper.map(dto, Employee.class);
  }
}
