package com.soprasteria.multientitymanager.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Utilisateur {
    private @Id UUID id;
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Telephone telephone;
}
