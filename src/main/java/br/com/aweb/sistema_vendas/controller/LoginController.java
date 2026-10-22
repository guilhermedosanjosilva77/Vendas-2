package br.com.aweb.sistema_vendas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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

    // Página com a mensagem exibida a quem não tem permissão (ver SistemaDeLogin)
    @GetMapping("/acesso-negado")
    public String acessoNegado() {
        return "acesso-negado";
    }
}