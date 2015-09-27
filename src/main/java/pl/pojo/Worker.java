package pl.pojo;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@javax.persistence.Entity
@Table(name = "Worker")
public class Worker {
	@Id
	@GeneratedValue
	@Column(name="Id")
	private Long idWorker;
	@Column(name = "Imie", length = 20, nullable = false)
	private String name;
	@Column(name = "Nazwisko", length = 20, nullable = false)
	private String surName;
	@Column(name = "Wiek", scale = 3)
	private Integer age;
	@JoinColumn(name="Id_Firmy")
	@ManyToOne(fetch = FetchType.EAGER)
	@Cascade({org.hibernate.annotations.CascadeType.PERSIST, CascadeType.SAVE_UPDATE})
	private Company company;

	public Worker() {
		super();
	}

	public Worker(String name, String surName, int age) {
		super();
		this.name = name;
		this.surName = surName;
		this.age = age;
	}

	public long getIdWorker() {
		return idWorker;
	}

	public void setIdWorker(long idWorker) {
		this.idWorker = idWorker;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public boolean isComapnyNull()
	{
		if (company!=null) return true;
		else return false;
	}
	
	@Override
	public String toString() {
		return "Worker [idWorker=" + idWorker + ", name=" + name + ", surName="
				+ surName + ", age=" + age + ", company=" + company
				+ "]";
	}

	
}
