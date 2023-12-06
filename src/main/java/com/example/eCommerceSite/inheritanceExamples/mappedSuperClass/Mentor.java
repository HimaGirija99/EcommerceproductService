package com.example.eCommerceSite.inheritanceExamples.mappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mps_mentor")
public class Mentor extends User {
    private int gradYear;

}
