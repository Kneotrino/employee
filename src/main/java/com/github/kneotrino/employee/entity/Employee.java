package com.github.kneotrino.employee.entity;

import com.github.kneotrino.employee.constant.GenderEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@EntityListeners(AuditingEntityListener.class)
public class Employee extends BaseCredentials implements Serializable {

    @Column(nullable = false, unique = true)
    String employeeId;

    @Column(nullable = false)
    String FullName;

    @Column(nullable = false)
    String Address;

    @Column(nullable = false)
    String role;

    @Column(nullable = false)
    String position;

    //todo department tables
    @Column(nullable = false)
    String department;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    GenderEnum Gender;

}
