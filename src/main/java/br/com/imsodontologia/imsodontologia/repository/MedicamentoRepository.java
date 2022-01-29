package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Medicamento;
import br.com.imsodontologia.imsodontologia.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    @Query(value = "SELECT * FROM ims.tb_medicamento where id_receituario = :id" , nativeQuery = true )
    List<Medicamento> findAllByReceituario(@Param("id") Integer id);

    @Query( value = "select * from ims.tb_medicamento where medicamento ilike %:x%" ,nativeQuery = true)
    public List<Medicamento> buscar(String x);

}
