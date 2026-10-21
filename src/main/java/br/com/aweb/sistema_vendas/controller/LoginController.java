package br.com.aweb.sistema_vendas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    // O POST /login é tratado pelo Spring Security
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // Página de confirmação de saída; o POST /logout é tratado pelo Spring Security
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    // Destino do accessDeniedPage. Usa @RequestMapping (todos os métodos HTTP)
    // porque o Spring faz forward da requisição original, que pode ser um POST.
    @RequestMapping("/acesso-negado")
    public String acessoNegado() {
        return "acesso-negado";
    }
}