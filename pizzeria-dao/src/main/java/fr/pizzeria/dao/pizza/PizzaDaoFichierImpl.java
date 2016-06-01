package fr.pizzeria.dao.pizza;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

@Repository
@Lazy
public class PizzaDaoFichierImpl implements IPizzaDao {
	
	private static final Logger LOG = Logger.getLogger(PizzaDaoFichierImpl.class.toString());
	
	private static final String REPERTOIRE_DATA = "data";
	
	

	public PizzaDaoFichierImpl() {
		LOG.log(Level.INFO, "Création du bean PizzaDaoFichierImpl");
	}
	
	@Override
	public List<Pizza> findAllPizzas() throws DaoException {
		
		try {
			return Files.list(Paths.get(REPERTOIRE_DATA))
				.map(path -> {
					Pizza p = new Pizza();
					p.setCode(path.getFileName().toString().replaceAll(".txt", ""));
					try {
						String ligne = Files.readAllLines(path).get(0);
						String[] ligneTab = ligne.split(";");
						p.setNom(ligneTab[0]);
						p.setPrix(new BigDecimal(ligneTab[1]));
						p.setCategorie(CategoriePizza.valueOf(ligneTab[2]));
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					return p;
				})
				.collect(Collectors.toList());
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}
	
	private String convertPizzaToString(Pizza p) {
		return p.getNom() + ";" + p.getPrix() + ";" + p.getCategorie().name();
	}
	

	@Override
	public void savePizza(Pizza newPizza) throws DaoException {
		try {
			Path nouveauFichier = Paths.get(REPERTOIRE_DATA + "/" + newPizza.getCode() + ".txt");
			Files.write(nouveauFichier, Arrays.asList(convertPizzaToString(newPizza)), StandardOpenOption.CREATE_NEW);
		} catch (IOException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void updatePizza(String codePizza, Pizza updatePizza) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		// TODO Auto-generated method stub
		
	}

	
	public void importerPizzasDepuisFichiers() throws DaoException {
		throw new NotImplementedException("importerPizzasDepuisFichiers non implémenté");
	}

	@Override
	public void saveAllPizzas(List<Pizza> listPizzas, int nb) throws DaoException {
		// TODO Auto-generated method stub
		
	}

}
