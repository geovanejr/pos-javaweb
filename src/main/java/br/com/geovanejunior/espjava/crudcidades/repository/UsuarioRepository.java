package br.com.geovanejunior.espjava.crudcidades.repository;

import br.com.geovanejunior.espjava.crudcidades.entity.UsuarioEntidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioEntidade, Long> {

    public UsuarioEntidade findByNome(String nome);
}
