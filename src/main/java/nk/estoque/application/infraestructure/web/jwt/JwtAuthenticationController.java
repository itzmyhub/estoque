package nk.estoque.application.infraestructure.web.jwt;

import static org.springframework.http.HttpStatus.OK;

import jakarta.validation.Valid;
import nk.estoque.application.infraestructure.entity.jwt.JwtRequest;
import nk.estoque.application.infraestructure.security.JwtTokenUtil;
import nk.estoque.application.infraestructure.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/authenticate")
public class JwtAuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenUtil jwtTokenUtil;

  private final JwtUserDetailsService userDetailsService;

  @Autowired
  public JwtAuthenticationController(
      AuthenticationManager authenticationManager,
      JwtTokenUtil jwtTokenUtil,
      JwtUserDetailsService userDetailsService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userDetailsService = userDetailsService;
  }

  @PostMapping
  public ResponseEntity<?> createAuthenticationToken(
      @Valid @RequestBody JwtRequest authenticationRequest) throws Exception {
    authenticate(authenticationRequest.getEmail(), authenticationRequest.getSenha());
    final UserDetails userDetails =
        userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
    final String token = jwtTokenUtil.generateToken(userDetails);
    return new ResponseEntity<>(new JwtResponse(token), OK);
  }

  private void authenticate(String username, String password) throws Exception {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
    } catch (DisabledException e) {
      throw new Exception("USER_DISABLED", e);
    } catch (BadCredentialsException e) {
      throw new Exception("INVALID_CREDENTIALS", e);
    }
  }
}
