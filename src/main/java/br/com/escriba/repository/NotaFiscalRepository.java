package br.com.escriba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.escriba.model.NotaFiscal;


@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
	@Query(value = "SELECT * FROM nota_fiscal WHERE NUMERO = ?1 ", nativeQuery = true)
    NotaFiscal findByNumero(String numero);
}
