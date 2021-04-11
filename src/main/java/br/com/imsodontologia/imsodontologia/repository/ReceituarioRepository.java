package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.domain.Receituario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceituarioRepository  extends JpaRepository<Receituario, Integer> {


}
