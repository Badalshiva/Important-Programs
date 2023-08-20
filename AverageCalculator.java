package com.badal.movingaverage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Set;
import java.util.StringTokenizer;
//Commeny
//adding 2nd comment
public class AverageCalculator {

	public static boolean isPrevious(CustomerBean bean, int i, ArrayList<CustomerBean> cList){
		
		if(i == 0)
			return false;
		else if(bean.getCustomer().equals(cList.get(i-1).getCustomer()))
			return true;
		
		return false;
	}
	
	public static boolean isNext(CustomerBean bean, int i, ArrayList<CustomerBean> cList){
	
		if(i == cList.size()-1)
			return false;
		else if(bean.getCustomer().equals(cList.get(i+1).getCustomer()))
			return true;
		
		return false;
	}
	public static void processData(ArrayList<String> array){
		
		ArrayList<CustomerBean> cList = new ArrayList<>();

		for(int i=0; i<array.size(); i++){

			StringTokenizer st = new StringTokenizer(array.get(i), "|");

			CustomerBean c = new CustomerBean();
			
			while(st.hasMoreElements()){
				c.setCustomer(st.nextToken());
				c.setCdate(Integer.parseInt(st.nextToken()));
				String str = st.nextToken();
				
				Pattern p = Pattern.compile("\\s");
				Matcher m = p.matcher(str);
				boolean b = m.find();
				if(Pattern.matches("[a-zA-Z]+",str) == false && b == false && str.isEmpty() == false)
					c.setAmountspent(Double.parseDouble(str));
				else
					c.setAmountspent(0);
			}
			
			cList.add(c);
		}

		Collections.sort(cList, new DateComparator());
				
		for(int i=0; i<cList.size();i++){
			CustomerBean c = cList.get(i);
			if(isPrevious(c, i, cList) == true && isNext(c, i, cList) == true){
				double average = (cList.get(i-1).getAmountspent()+c.getAmountspent()+cList.get(i+1).getAmountspent())/3;
				System.out.println(c.getCustomer()+" ---- "+c.getCdate()+" ---- "+c.getAmountspent()+" ---- "+average);
			}
			else if(isPrevious(c, i, cList) == false && isNext(c, i, cList) == true){
				double average = (c.getAmountspent()+cList.get(i+1).getAmountspent())/2;
				System.out.println(c.getCustomer()+" ---- "+c.getCdate()+" ---- "+c.getAmountspent()+" ---- "+average);
			}
			else{
				double average = (cList.get(i-1).getAmountspent()+c.getAmountspent())/2;
				System.out.println(c.getCustomer()+" ---- "+c.getCdate()+" ---- "+c.getAmountspent()+" ---- "+average);
			}
			
		}
		/*HashMap<String, LinkedHashMap> cMap = new HashMap<>();
		for(int i=0; i<cList.size(); i++){
			
			LinkedHashMap<Integer, Double> dataMap = new LinkedHashMap<>();
			for(int j = i+1; j<cList.size(); j++){
				
				if(cList.get(i).getCustomer().equals(cList.get(j).getCustomer())){
					dataMap.put(cList.get(i).getCdate(), cList.get(i).getAmountspent());
					
				}
				System.out.println(cList.get(i).getAmountspent());
				cMap.put(cList.get(i).getCustomer(), dataMap);
					
			}
			
			//System.out.println(dataMap);	
		}*/
		
		/*Map<String,String> sbiMap = map.get("sbi");
		  Set<String> keys = sbiMap.keySet();
		  for(String key: keys){
		    System.out.println("key="+key+" value = " + sbiMap.get(key));
		  }*/
		//HashMap<String, LinkedHashMap<Integer, Double>> PropertyHolder;
		
		/*Iterator<Entry<String, LinkedHashMap>> parent = cMap.entrySet().iterator();
		while (parent.hasNext()) {
		    Entry<String, LinkedHashMap> parentPair = parent.next();
		    System.out.println("parentPair.getKey() :   " + parentPair.getKey() + " parentPair.getValue()  :  " + parentPair.getValue().size());

		    Iterator<Map.Entry<String, String>> child = (parentPair.getValue()).entrySet().iterator();
		    while (child.hasNext()) {
		        Map.Entry childPair = child.next();
		        System.out.println("childPair.getKey() :   " + childPair.getKey() + " childPair.getValue()  :  " + childPair.getValue());

		        child.remove(); // avoids a ConcurrentModificationException
		    }

		}*/
		
		
		
		
//		for (Entry<String, LinkedHashMap> entry : cMap.entrySet()) {
//	        
//			System.out.println(entry.getKey());
//			
//			
//			LinkedHashMap<Integer, Double> childMap = cMap.get(entry.getValue());
//
//	        for (Entry<Integer, Double> childEntry : childMap.entrySet()) {
////	            String childKey = entry2.getKey();
////	            String childValue = entry2.getValue();
//	        	System.out.println(entry.getKey()+"---"+childEntry.getKey()+"----"+childEntry.getKey());
//	        }
//	    }
		/*for(String s: cMap.keySet()){
			LinkedHashMap<Integer, Double> valMap = cMap.get(s); 
			Set<Integer> set = cMap.get(s).keySet();
			
			
			for(int i: set){
			System.out.println(s+"----"+i);
			}
		}*/
		
	}

	public static void main(String[] args) {

		String csvFile = "res/TestFile.csv";

		ArrayList<String> array = new ArrayList<>();
		
		try{
			FileReader fileReader = new FileReader(csvFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				array.add(line);

				stringBuffer.append("\n");
			}
			fileReader.close();
			
			/*Removing the Header*/
			//System.out.println(array.size());
			array.remove(0);
			
			//System.out.println(array.size());
			
			processData(array);


		}catch(IOException e){
			e.printStackTrace();
		}

	}

}
