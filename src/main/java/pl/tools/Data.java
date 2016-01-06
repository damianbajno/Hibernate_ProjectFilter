package pl.tools;

import java.util.Random;

import pl.dao.CompanyDAO;
import pl.dao.WorkerDAO;
import pl.pojo.Company;
import pl.pojo.Worker;

public class Data {

     
     public static void main(String[] args) {
	  Data.sendToDataBase();
     }



     public static void sendToDataBase() {
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
