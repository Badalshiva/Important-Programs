package com.badal.movingaverage;

import java.util.Comparator;

public class DateComparator implements Comparator<CustomerBean>{

	@Override
	public int compare(CustomerBean c1, CustomerBean c2) {
		String s1= c1.getCustomer();
		String s2= c2.getCustomer();
		int d1 = c1.getCdate();
		int d2= c2.getCdate();
		
		String cd1 = s1+d1;
		String cd2 = s2+d2;
		return cd1.compareTo(cd2);
	}

}
