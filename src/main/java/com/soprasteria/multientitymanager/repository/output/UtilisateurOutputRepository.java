package com.soprasteria.multientitymanager.repository.output;

import com.soprasteria.multientitymanager.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UtilisateurOutputRepository extends JpaRepository<Utilisateur, UUID> {
}
