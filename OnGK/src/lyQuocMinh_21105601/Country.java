package lyQuocMinh_21105601;

import java.util.Objects;

public class Country {
	private String name, capital;
	private int population;
	private boolean democracy;
	
	public Country(String name, String capital, int population, boolean democracy) {
		super();
		this.name = name;
		this.capital = capital;
		this.population = population;
		this.democracy = democracy;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public boolean isDemocracy() {
		return democracy;
	}

	public void setDemocracy(boolean democracy) {
		this.democracy = democracy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Country other = (Country) obj;
		return Objects.equals(name, other.name);
	}
	
	
}
