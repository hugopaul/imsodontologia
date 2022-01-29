package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Paciente;
import br.com.imsodontologia.imsodontologia.model.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Observable;
import java.util.Optional;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Integer> {


    @Query(value = "SELECT * FROM ims.tb_prontuario where id_paciente = :id", nativeQuery = true)
    Prontuario findProntByPac(Integer id);

    @Query( value = "select pronts.* from ims.tb_prontuario pronts " +
                    "inner join(select * from ims.tb_paciente where paciente ilike %:x%) " +
                    "as pacs on pronts.id_paciente = pacs.id_paciente",nativeQuery = true)
    public List<Prontuario> buscar(String x);
}
