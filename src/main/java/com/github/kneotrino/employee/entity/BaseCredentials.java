package com.github.kneotrino.employee.entity;

import com.github.kneotrino.employee.common.BaseModel;
import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;


@Data
public class BaseCredentials extends BaseModel {

    @Column(nullable = false, unique = true)
    protected String username;

    @Column(nullable = false)
    protected String password;

    @Column(nullable = false, unique = true)
    protected String email;

    @Column(nullable = false)
    protected String mobileNumber;

    @Column(nullable = false)
    protected String role;
}
