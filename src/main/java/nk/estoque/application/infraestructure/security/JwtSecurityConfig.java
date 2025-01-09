package nk.estoque.application.infraestructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class JwtSecurityConfig {

  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  private final AuthenticationConfiguration authConfiguration;

  private final UserDetailsService jwtUserDetailsService;

  private final JwtRequestFilter jwtRequestFilter;

  @Autowired
  public JwtSecurityConfig(
      JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
      AuthenticationConfiguration authConfiguration,
      UserDetailsService jwtUserDetailsService,
      JwtRequestFilter jwtRequestFilter) {
    this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
    this.authConfiguration = authConfiguration;
    this.jwtUserDetailsService = jwtUserDetailsService;
    this.jwtRequestFilter = jwtRequestFilter;
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  @Autowired
  public void configure(AuthenticationManagerBuilder builder) throws Exception {
    builder.userDetailsService(jwtUserDetailsService);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/authenticate").permitAll())
        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/usuarios").permitAll())
        .authorizeHttpRequests(authorize -> authorize.requestMatchers("/produtos").permitAll())
        .authorizeHttpRequests(requests -> requests.anyRequest().authenticated())
        .exceptionHandling(
            exceptionHandling ->
                exceptionHandling.authenticationEntryPoint(jwtAuthenticationEntryPoint))
        .sessionManagement(
            sessionManagement ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    return httpSecurity.build();
  }
}
