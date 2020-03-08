package br.com.escriba.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.escriba.model.ProdutoNotaFiscal;

@Repository
public interface ProdutoNotaFiscalRepository extends JpaRepository<ProdutoNotaFiscal, Long> {
	@Query(value = "SELECT * FROM produto_nota_fiscal WHERE produto_id = ?1 and nota_fiscal_id = ?2", nativeQuery = true)
	ProdutoNotaFiscal findProdutoNF(long produtoId, long  notaFiscalId);
}
