package com.Examen.CajeroService.UserDetailsJPAService;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;

@Service
public class TokenBlackListService implements ITokenInvalidationService {

    private final Set<String> blackList = ConcurrentHashMap.newKeySet();

    @Override
    public void invalidationToken(String jti) {
        blackList.add(jti);
        System.out.println("Token Invalidado correctamente: " + jti);
    }

    @Override
    public boolean isTokenInvalid(String jti) {
        return blackList.contains(jti);
    }

}
