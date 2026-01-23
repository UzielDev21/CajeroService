package com.Examen.CajeroService.DAO;

import com.Examen.CajeroService.JPA.UsuarioJPA;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepositoryDAO extends JpaRepository<UsuarioJPA, Integer> {

    UsuarioJPA findByUserName(String username);
    
    UsuarioJPA findByEmail(String email);
    
}
