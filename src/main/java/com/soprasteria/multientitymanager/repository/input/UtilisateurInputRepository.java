package com.soprasteria.multientitymanager.repository.input;

import com.soprasteria.multientitymanager.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UtilisateurInputRepository extends JpaRepository<Utilisateur, UUID> {
}
