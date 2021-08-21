package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer> {

    @Query(value = "SELECT id_atendimento, atendimento, data_de_cadastro, valor, id_prontuario FROM ims.tb_atendimento where id_prontuario = :id ", nativeQuery = true)
    List<Atendimento>atendsByPront(Integer id);
}
