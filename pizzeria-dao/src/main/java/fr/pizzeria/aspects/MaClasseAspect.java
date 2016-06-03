package fr.pizzeria.aspects;

import java.util.Calendar;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;

import fr.pizzeria.dao.performance.RepositoryPerformance;
import fr.pizzeria.model.performance.Performance;

//déclarer un aspect
@Aspect
public class MaClasseAspect {
	// static final Logger logger = Logger.getLogger(MaClasseAspect.class);

	@Autowired
	private RepositoryPerformance repositoryPerformance;

	@Around("execution(*fr.pizzeria.dao.*.*(..))")

	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
		long debut = System.currentTimeMillis();

		Object sortie = joinPoint.proceed();
		long fin = System.currentTimeMillis();

		Performance perf = new Performance();
		perf.setService(joinPoint.getSignature().toShortString());
		perf.setTemps(fin - debut);
		perf.setDate(Calendar.getInstance().getTime());

		repositoryPerformance.save(perf);
		return sortie;

	}

}
