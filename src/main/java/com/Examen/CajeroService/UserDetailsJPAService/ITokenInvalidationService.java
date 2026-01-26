package com.Examen.CajeroService.UserDetailsJPAService;

public interface ITokenInvalidationService {
    
    void invalidationToken(String jti);
    
    boolean isTokenInvalid(String jti);

}
