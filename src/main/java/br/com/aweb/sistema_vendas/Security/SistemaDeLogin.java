package br.com.aweb.sistema_vendas.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SistemaDeLogin {

    @Bean
    public SecurityFilterChain filtrosSeguranca(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(req -> {
                    // Recursos estáticos e páginas de login/logout liberados
                    req.requestMatchers("/css/**", "/js/**", "/img/**").permitAll();
                    req.requestMatchers("/login", "/logout").permitAll();

                    // Regras da mais específica para a mais genérica
                    // ADMIN: cadastro de produtos e clientes
                    req.requestMatchers("/produtos/**").hasRole("ADMIN");
                    req.requestMatchers("/clientes/**").hasRole("ADMIN");

                    // ADMIN e PADRAO: operações de pedidos
                    req.requestMatchers("/pedidos/**").hasAnyRole("ADMIN", "PADRAO");

                    // Demais rotas (ex.: página inicial): qualquer usuário autenticado
                    req.anyRequest().authenticated();
                })
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                        .permitAll())
                .logout(logout -> logout
                        // POST /logout é tratado pelo Spring (formulário em logout.html)
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .rememberMe(rememberMe -> rememberMe.key("lembrarDeMim"))
                .build();
    }

    @Bean
    public PasswordEncoder codificadorSenha() {
        return new BCryptPasswordEncoder();
    }

}