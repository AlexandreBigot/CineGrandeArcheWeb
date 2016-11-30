package fr.demos.formation.poe.cinegrandearche.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import fr.demos.formation.poe.cinegrandearche.metier.Article;
import fr.demos.formation.poe.cinegrandearche.metier.ArticleDivers;
import fr.demos.formation.poe.cinegrandearche.metier.Etat;
import fr.demos.formation.poe.cinegrandearche.metier.Livre;

public class ArticleDAOMySql implements ArticleDAO {

	private Context context;
	private DataSource dataSource;
		
	// dans la constructeur je lance le context (annuaire) et le datasource (pool de connexion)
	public ArticleDAOMySql() throws Exception {
		context = new InitialContext();
		dataSource = (DataSource) context.lookup("java:comp/env/jdbc/CineGrandeArche");
	}

	@Override
	public void insert(Article a) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Article a) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Article a) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Article> select(String critere) {

		ArrayList<Article> catalogue = new ArrayList<Article>();

		try (Connection cx = dataSource.getConnection()){
			
			PreparedStatement contexteRequete = null;
			
			// si pas de crit�re de recherche, j'envoie toute la BDD
			if (critere.equals("")){
				contexteRequete = cx.prepareStatement(
				"SELECT * FROM Livre JOIN Article ON Livre.reference_livre=Article.reference_article");
			// sinon j'envoie la s�lection
			} else {
				String critereSQL = "%"+critere+"%";
				// upper(colonne) pour tout mettre virtuellement en MAJ et pouvoir comparer
				// on a aussi mis le critere re�u en MAJ
				
// ##### requete qui marche #####				
// SELECT * FROM Livre JOIN Article ON Livre.reference_livre=Article.reference_article
// WHERE upper(Livre.editeur) LIKE '%DUNOD%'				
				
				
				contexteRequete = cx.prepareStatement(
				"SELECT * FROM Livre JOIN Article ON Livre.reference_livre=Article.reference_article "
				+ "WHERE upper(Article.reference_article) LIKE ? "
				+ "OR upper(Article.nom) LIKE ? "
				+ "OR upper(Article.description) LIKE ? "
				+ "OR upper(Article.type_article) LIKE ? "
				+ "OR upper(Article.etat) LIKE ? "
				+ "OR upper(Article.format) LIKE ? "
				+ "OR upper(Livre.auteur) LIKE ? "
				+ "OR upper(Livre.isbn) LIKE ? "
				+ "OR upper(Livre.editeur) LIKE ? "
				+ "OR upper(Livre.genre) LIKE ? ");
// on fera article divers plus tard...
//				+ "OR upper(article_divers.type_article_divers) LIKE ? "
//				+ "OR upper(article_divers.caracteristiques) LIKE ?"


				// je renseigne les valeurs des "?"
				contexteRequete.setString(1, critereSQL);
				contexteRequete.setString(2, critereSQL);
				contexteRequete.setString(3, critereSQL);
				contexteRequete.setString(4, critereSQL);
				contexteRequete.setString(5, critereSQL);
				contexteRequete.setString(6, critereSQL);
				contexteRequete.setString(7, critereSQL);
				contexteRequete.setString(8, critereSQL);
				contexteRequete.setString(9, critereSQL);
				contexteRequete.setString(10, critereSQL);
//				contexteRequete.setString(11, critereSQL);
//				contexteRequete.setString(12, critereSQL);
				
			}// if else
			
			// stockage de l'ensemble des r�sultats qu'on peut parcourrir
			ResultSet rs = contexteRequete.executeQuery();
			
			// on parcours caque �l�ment de l'objet
			while (rs.next()) {
				
				Livre livre;
				ArticleDivers articleDivers;
				
				// je r�cup�re chaque enregistrement de la colonne dans une variable
				String ref = rs.getString("reference_article");
				String auteur = rs.getString("auteur");
				String isbn = rs.getString("isbn");
				String editeur = rs.getString("editeur");
				String genre = rs.getString("genre");
				LocalDate date = rs.getDate("date").toLocalDate();
				Double prix = rs.getDouble("prix");
				String nom = rs.getString("nom");
				String description = rs.getString("description");
				String url_image = rs.getString("url_image");
		//		String type_article = rs.getString("type");
				int stock = rs.getInt("stock");
				String format = rs.getString("format");
				// etat � utiliser apr�s modif
				Etat etat;
				if (format.equals("")){
					etat = Etat.valueOf(rs.getString("etat"));
				} else {
					etat = null;
				}
				String url_download = rs.getString("url_download");
				
				// maintenant je cr�e une instance de livre avec les donn�es
				// r�cup�r�es en BDD pour pouvoir l'utiliser
				// je dois identifier s'il s'agit d'un livre mat�riel ou non
				// ajouter un if livre ou else article divers

				if (format.equals("")){
					livre = new Livre(ref, prix, nom, url_image, stock, etat, auteur, isbn, editeur, genre);
				} else {
					livre = new Livre(ref, prix, nom, url_image, format, url_download, auteur, isbn, editeur, genre);
				} // if else
			
			// je remplis les attribut non obligatoires (non d�finis dans le constructeur)
			livre.setDescription(description);
			livre.setDate(date);
			livre.setUrlImage(url_image);
			
			// j'ajoute le livre dans mon catalogue
			catalogue.add(livre);
			
			} // while next
			
		
		}
		catch (Exception ex) {
			ex.printStackTrace();
		} // try catch
		
		return catalogue;
	} // override select

}// class
