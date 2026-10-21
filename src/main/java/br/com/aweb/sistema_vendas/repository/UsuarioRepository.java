package br.com.aweb.sistema_vendas.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aweb.sistema_vendas.model.User;

public interface UsuarioRepository extends JpaRepository<User, Long> {

    // Busca por e-mail ignorando maiúsculas/minúsculas
    Optional<User> findByEmailIgnoreCase(String email);
}