package com.Examen.CajeroService.DAO;

import com.Examen.CajeroService.JPA.Result;

public interface ICajeroJPA {

    Result GetAll();
    
    Result GetById(int idCajero);
    
}
