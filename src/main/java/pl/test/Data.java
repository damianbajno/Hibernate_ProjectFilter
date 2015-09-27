package pl.test;

import java.util.Random;

import pl.dao.CompanyDAO;
import pl.dao.WorkerDAO;
import pl.pojo.Company;
import pl.pojo.Worker;
import pl.tools.Generator;

public class Data {

	public Data(){
			
	}
	
	public void sendToDataBase(){
		Random random = new Random();
	
		for (int i = 0; i < 30; i++) {
			Company company = new Company(Generator.generateName(),
					Generator.generateStreetName(),
					Generator.generateCityNames());
			for (int j = 0; j < random.nextInt(8); j++) {
				Worker worker = new Worker(Generator.generateName(),
						Generator.generateSurname(),
						Generator.generateInteger(100));
				worker.setCompany(company);
				company.addWorker(worker);
			}
	
			CompanyDAO.persist(company);
		}
	}

	
}
