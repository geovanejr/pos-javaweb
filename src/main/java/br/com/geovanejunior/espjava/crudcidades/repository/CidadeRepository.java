package br.com.geovanejunior.espjava.crudcidades.repository;

import br.com.geovanejunior.espjava.crudcidades.entity.CidadeEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntidade, Long> {

    public Optional<CidadeEntidade> findByNomeAndEstado(String nome, String estado);
}
