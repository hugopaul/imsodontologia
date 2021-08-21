package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {

    @Query(value = "SELECT * FROM ims.medicamento where id_receituario = :id" , nativeQuery = true )
    List<Medicamento> findAllByReceituario(@Param("id") Integer id);
}
