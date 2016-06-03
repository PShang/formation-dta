package fr.pizzeria.model.performance;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // class java à persister et Mapper
public class Performance {

	// private final static Map<String, String> FORMAT = new HashMap<String,
	// String>();
	// private final static String AUTRE_FORMAT = "(%s)";
	// static {FORMAT.put("id", "%s ->");FORMAT.put("id", "%s ***");}
	// table <<performance>> stocker les informations
	// static final Logger logger = Logger.getLogger(Performance.class);
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String service;
	private Date date;
	private long temps;

	public Performance(int id, String service, Date date, long temps) {
		this();
		this.id = id;
		this.service = service;
		this.date = date;
		this.temps = temps;
	}

	public Performance() {
		// TODO Auto-generated constructor stub
	}

	public long getTemps() {
		return temps;
	}

	public void setTemps(long temps) {
		this.temps = temps;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	@Override
	public String toString() {
		return "Performance{" + "id=" + id + " date = " + date + ", service='" + service + '\'' + ", tempsExecution="
				+ temps + '}';
	}
}
