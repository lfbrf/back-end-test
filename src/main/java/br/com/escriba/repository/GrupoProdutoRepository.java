package br.com.escriba.repository;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.escriba.model.GrupoProduto;

@Repository
public interface GrupoProdutoRepository extends JpaRepository<GrupoProduto, Long> {
	@Query(value = "SELECT * FROM grupo_produto WHERE nome LIKE %?1% ", nativeQuery = true)
	List <GrupoProduto> findLikeNome(String nome);
	@Query(value = "SELECT * FROM grupo_produto WHERE ((ROUND (            (             LENGTH(nome)             - LENGTH( REPLACE ( nome, ?1, '') )          ) / LENGTH(?1)             )) > 0) ORDER BY (ROUND (            (             LENGTH(nome)             - LENGTH( REPLACE ( nome, ?1, '') )          ) / LENGTH(?1)             ))  DESC limit 1 " , nativeQuery = true)
	GrupoProduto findMatchLetter(String letra);
	@Query(value="SELECT * FROM grupo_produto LIMIT ?1 OFFSET ?2 ", nativeQuery = true)
	List <GrupoProduto> pagination(int quantidade, int inicio);
}
