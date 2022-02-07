package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Paciente;
import br.com.imsodontologia.imsodontologia.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);

    @Query(value = "SELECT perfil FROM ims.tb_usuario where username = :username", nativeQuery = true)
    String findPerfilByUsusario(String username);

    @Query( value = "select * from ims.tb_usuario where nome ilike %:x% and perfil like 'DENTISTA'" ,nativeQuery = true)
    List<Usuario> getDocByNome(String x);

    @Query(value = "select nome from ims.tb_usuario where username =:username", nativeQuery = true)
    String getNomePorUsername(String username);
}
