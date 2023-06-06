package com.soprasteria.multientitymanager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
public class Telephone {
    @Id
    private UUID id;

    private String number;
}
