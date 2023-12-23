package lyQuocMinh_21105601;

import java.util.ArrayList;
import java.util.List;

public class ListCountry {
	private List<Country> list;

	public ListCountry() {
		super();
		list = new ArrayList<Country>();
	}
	
	public boolean them(Country o) {
		if(list.contains(o)) {
			return false;
		}
		list.add(o);
		return true;
	}
}
