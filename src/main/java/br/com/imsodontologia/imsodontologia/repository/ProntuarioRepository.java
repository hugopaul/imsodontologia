package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {


    @Query(value = "SELECT * FROM ims.tb_prontuario where id_paciente = :id", nativeQuery = true)
    Prontuario findProntByPac(Integer id);
}
