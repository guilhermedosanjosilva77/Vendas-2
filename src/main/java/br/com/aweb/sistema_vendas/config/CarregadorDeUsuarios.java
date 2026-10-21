package br.com.aweb.sistema_vendas.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.aweb.sistema_vendas.model.User;
import br.com.aweb.sistema_vendas.model.UsuarioRole;
import br.com.aweb.sistema_vendas.repository.UsuarioRepository;
import br.com.aweb.sistema_vendas.service.UsuarioService;

/**
 * Cria os usuários de teste na primeira execução (local e Render).
 * Se o e-mail já existir, não faz nada. A senha é gravada com hash pelo UsuarioService.
 */
@Component
public class CarregadorDeUsuarios implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioService usuarioService;

    public CarregadorDeUsuarios(UsuarioRepository usuarioRepository, UsuarioService usuarioService) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public void run(String... args) {
        criarSeNaoExistir("Administrador", "admin@sistema.com", "admin123", UsuarioRole.ADMIN);
        criarSeNaoExistir("Usuário Padrão", "padrao@sistema.com", "padrao123", UsuarioRole.PADRAO);
    }

    private void criarSeNaoExistir(String nome, String email, String senha, UsuarioRole role) {
        if (usuarioRepository.findByEmailIgnoreCase(email).isEmpty()) {
            usuarioService.salvar(new User(nome, email, senha, role));
        }
    }
}