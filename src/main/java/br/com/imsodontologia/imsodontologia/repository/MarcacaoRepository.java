package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Marcacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MarcacaoRepository extends JpaRepository<Marcacao, Integer> {

    @Query( value = "select * from ims.tb_marcacao where  dt_inicio >= to_date(:dtInicio, 'yyyy-mm-dd hh24:mi:ss') and dt_inicio <= to_date(:dtFim, 'yyyy-mm-dd hh24:mi:ss') ", nativeQuery = true)
    List<Marcacao> getByDate(String dtInicio, String dtFim);
}
