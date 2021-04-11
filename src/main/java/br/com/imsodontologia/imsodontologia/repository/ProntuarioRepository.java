package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.domain.Prontuario;
import br.com.imsodontologia.imsodontologia.domain.Receituario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {


    @Query(value = "SELECT * FROM public.prontuario where id_paciente = :id", nativeQuery = true)
    Prontuario findProntByPac(@Param("id") Integer id);
}
