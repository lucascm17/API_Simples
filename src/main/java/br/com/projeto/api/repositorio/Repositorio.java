package br.com.projeto.api.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.projeto.api.model.Pessoa;

@Repository
public interface Repositorio extends CrudRepository<Pessoa, Integer> {
    
    List<Pessoa> findAll();

    Pessoa findById(int id);

    List<Pessoa> findByOrderByNome();

    List<Pessoa> findByNomeOrderByIdade(String nome);

    List<Pessoa> findByNomeContaining(String termo);

    List<Pessoa> findByNomeStartsWith(String termo);

    List<Pessoa> findByNomeEndsWith(String termo);

    @Query(value = "SELECT SUM(IDADE) FROM PESSOAS", nativeQuery = true)
    int somaIdades();

    @Query(value = "SELECT * FROM PESSOAS WHERE IDADE >= :idade", nativeQuery = true)
    List<Pessoa> idadeMaiorIgual(int idade);

    int countById(int id);
}
