package pl.pojo;

 import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@javax.persistence.Entity
@Table(name="Company")
public class Company {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private long idCompany;
	@Column (name="Nazwa_Firmy", length=30)
	private String name;
	@Column (name="Segment", length=30)
	private String segment;
	@Column (name="Miasto", length=30)
	private String town;
	@OneToMany(mappedBy="company", fetch=FetchType.EAGER, orphanRemoval=true)
	@Cascade({CascadeType.PERSIST, CascadeType.SAVE_UPDATE})
	private Set<Worker> workers;
	
	public Company() {
		super();
	}

	public Company(String company, String segment, String town) {
		super();
		this.name = company;
		this.segment = segment;
		this.town = town;
	}

	

	public void setCompanyId(long idCompany) {
		this.idCompany = idCompany;
	}

	public long getCompanyId() {
		return idCompany;
	}

	public String getName() {
		return name;
	}

	public void setName(String companyName) {
		this.name = companyName;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Set<Worker> getWorkers() {
		return workers;
	}

	public void setWorkers(Set<Worker> workers) {
		this.workers = workers;
	}
	
	public void addWorker(Worker worker)
	{
		if (workers==null)
		workers=new HashSet<Worker>();
			workers.add(worker);
	}

	public boolean isWorker()
	{
		if (workers!=null) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "Company [idCompany=" + idCompany + ", company="
				+ name + ", segment=" + segment + ", town=" + town
				+ ", workers=" + "]";
	}
}