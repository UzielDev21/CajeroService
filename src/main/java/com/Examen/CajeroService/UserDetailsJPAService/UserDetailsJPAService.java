package com.Examen.CajeroService.UserDetailsJPAService;

import com.Examen.CajeroService.DAO.IUsuarioRepositoryDAO;
import com.Examen.CajeroService.JPA.UsuarioJPA;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsJPAService implements UserDetailsService {

    private final IUsuarioRepositoryDAO iUsuarioRepositoryDAO;

    public UserDetailsJPAService(IUsuarioRepositoryDAO iUsuarioRepositoryDAO) {
        this.iUsuarioRepositoryDAO = iUsuarioRepositoryDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioJPA usuarioJPA = iUsuarioRepositoryDAO.findByUserName(username);

        if (usuarioJPA == null) {
            throw new UsernameNotFoundException("el usuario no se encontro" + username);
        }

        return User.withUsername(usuarioJPA.getUserName())
                .password(usuarioJPA.getPassword())
                .roles(usuarioJPA.rolJPA.getNombreRol())
                .build();

    }

}
