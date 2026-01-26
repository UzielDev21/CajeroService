package com.Examen.CajeroService.RestController;

import com.Examen.CajeroService.DAO.IUsuarioRepositoryDAO;
import com.Examen.CajeroService.JPA.Result;
import com.Examen.CajeroService.JPA.UsuarioJPA;
import com.Examen.CajeroService.UserDetailsJPAService.JwtService;
import com.Examen.CajeroService.UserDetailsJPAService.TokenBlackListService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final IUsuarioRepositoryDAO iUsuarioRepositoryDAO;
    private final TokenBlackListService tokenBlackListService;

    public AuthRestController(
            AuthenticationManager authenticationManager,
            JwtService jwtService,
            IUsuarioRepositoryDAO iUsuarioRepositoryDAO,
            TokenBlackListService tokenBlackListService) throws Exception {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.iUsuarioRepositoryDAO = iUsuarioRepositoryDAO;
        this.tokenBlackListService = tokenBlackListService;
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody Map<String, String> json) {

        Result result = new Result();

        try {

            String username = json.get("username");
            String password = json.get("password");

            UsuarioJPA usuarioJPA = iUsuarioRepositoryDAO.findByUserName(username);

            if (usuarioJPA == null) {
                result.correct = false;
                result.errorMessage = "Credenciales inexistentes";
                result.status = 401;

                return ResponseEntity.status(result.status).body(result);
            }

            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    password);

            try {

                authenticationManager.authenticate(auth);

            } catch (Exception ex) {
                result.correct = false;
                result.errorMessage = "Error al iniciar sesión, Ingresa las credenciales correctas";
                result.status = 401;

                return ResponseEntity.status(result.status).body(result);
            }

            String rol = usuarioJPA.rolJPA.getNombreRol();
            int idUsuario = usuarioJPA.getIdUsuario();

            String jwt = jwtService.GenerateUserToken(username, idUsuario, rol);
            result.correct = true;
            result.status = 200;
            result.object = jwt;

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

    @PostMapping("/logout")
    public ResponseEntity Logout(HttpServletRequest request) {

        Result result = new Result();

        try {

            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                result.correct = false;
                result.errorMessage = "No se encontro el token";
                result.status = 400;
                return ResponseEntity.status(result.status).body(result);
            }

            String token = authHeader.substring(7);

            if (!jwtService.isTokenValid(token)) {
                result.correct = false;
                result.errorMessage = "Token invalidado o expirado";
                result.status = 401;
                return ResponseEntity.status(result.status).body(result);
            }

            String jti = jwtService.getJtiFromToken(token);
            tokenBlackListService.invalidationToken(jti);
            SecurityContextHolder.clearContext();

            result.correct = true;
            result.status = 200;
            result.object = "Logout Exitoso";

        } catch (Exception ex) {
            result.correct = false;
            result.errorMessage = ex.getLocalizedMessage();
            result.ex = ex;
            result.status = 500;
        }
        return ResponseEntity.status(result.status).body(result);
    }

}
