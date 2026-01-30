package com.Examen.CajeroService.Service;

public interface ITokenInvalidationService {
    
    void invalidationToken(String jti);
    
    boolean isTokenInvalid(String jti);

}
