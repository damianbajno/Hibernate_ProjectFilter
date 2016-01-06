package pl.tools;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Random;

import com.mysql.jdbc.Buffer;

public class Generator {

	private static final String GENERATOR_IO_EXEPTION_MESSAGE = "Generator: IOExeption";
     private static final String FILE_NOT_FOUND_MESSAGE = "Generator: FileNotFound";
     private static Properties generatorProperties;

	static {
		generatorProperties = new Properties();
		
		ClassLoader classLoader = Generator.class.getClassLoader();

		InputStream resourceAsStream = classLoader.getResourceAsStream("GeneratorPropertise.properties");

		try {
			generatorProperties.load(resourceAsStream);
		} catch (FileNotFoundException e) {
			System.out.println(FILE_NOT_FOUND_MESSAGE);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(GENERATOR_IO_EXEPTION_MESSAGE);
			e.printStackTrace();
		}

	}

	public static String generateName() {
		Random random = new Random();
		String[] names = generatorProperties.getProperty("names").split(", ");
		return names[random.nextInt(names.length)];
	}

	public static boolean generateisActive() {
		Random random = new Random();
		if (random.nextInt(2) == 0)
			return true;
		else
			return false;
	}

	public static String generateSurname() {
		Random random = new Random();
		String[] surNames = generatorProperties.getProperty("surNames")
				.split(", ");
		return surNames[random.nextInt(surNames.length)];
	}

	public static String generateStreetName() {
		Random random = new Random();
		String[] streetNames = generatorProperties.getProperty("streetNames")
				.split(", ");
		return streetNames[random.nextInt(streetNames.length)];
	}

	public static String generateCityNames() {
		Random random = new Random();
		String[] cityNames = generatorProperties.getProperty("cityNames")
				.split(", ");
		return cityNames[random.nextInt(cityNames.length)];
	}

	public static int generateInteger(int rangeInt) {
		Random random = new Random();
		return random.nextInt(rangeInt);
	}

}
