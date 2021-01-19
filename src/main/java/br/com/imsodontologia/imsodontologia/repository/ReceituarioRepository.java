package br.com.imsodontologia.imsodontologia.repository;

import br.com.imsodontologia.imsodontologia.domain.Receituario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceituarioRepository  extends JpaRepository<Receituario, Integer> {
}
