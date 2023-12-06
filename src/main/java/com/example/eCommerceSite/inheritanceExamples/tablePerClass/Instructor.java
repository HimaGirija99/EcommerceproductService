package com.example.eCommerceSite.inheritanceExamples.tablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_instructor")
public class Instructor extends User{
    private String company;
}
