package fr.demos.formation.poe.cinegrandearche.data;

import java.util.List;

import fr.demos.formation.poe.cinegrandearche.metier.Article;

public interface ArticleDAO {
	
	void insert (Article a) throws Exception;
	void update (Article a) throws Exception;
	void delete (Article a) throws Exception;
	List<Article> select (String critere);
	
	
}
