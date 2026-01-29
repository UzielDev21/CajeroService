package com.Examen.CajeroService.DAO;

import com.Examen.CajeroService.JPA.Result;

public interface ICuentasJPA {
    
    Result GetAll();

    Result GetById(int idCuenta);
    
}
