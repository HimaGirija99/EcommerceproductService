package com.example.eCommerceSite.inheritanceExamples.singleClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "sc_instructor")
@DiscriminatorValue(value = "3")
public class Instructor extends User {
    private String company;
}
