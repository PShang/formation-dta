package fr.pizzeria.dao.performance;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.pizzeria.model.performance.Performance;

//entité stock les infos:id,service,date,tempsExecution.

public interface RepositoryPerformance extends JpaRepository<Performance, Integer> {

}
