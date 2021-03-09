package com.github.kneotrino.employee.entity;

import com.github.kneotrino.employee.common.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class Pegawai extends BaseModel {

    @Column(length = 100)
    String nama;

    @Column(length = 250)
    String alamat;
}
