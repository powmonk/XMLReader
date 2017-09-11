package org.aldron.XMLTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class postcodeBoundary {
	String name;
	ArrayList<String> polys = new ArrayList<>();
	
	public void setName(String nameIn){
		name = nameIn;
	}
	
	public String getName(){
		return name;
	}

	public void addCoords(String coordsIn){
		polys.add(coordsIn); 
		
	}
	
	public String getCoords(){
		String result = new String();;
		for(String cache:polys){
			result = result + cache;
		}
		return result;
	}
	
	public String getPolys(){
		return formatJSArrays();
	}
	
	private String formatJSArrays(){
		String result = new String();

		//This loops through the amount of polys passed in
		for(int i=0;i<polys.size();i++){
			result = result + "var " + name + i + " = [";

			List<String> cache = Arrays.asList(polys.get(i).split(",0 "));
			
			for(int j=0;j<cache.size();j+=3){
				String[] tmp = cache.get(j).split(",");
				if(tmp.length>1){
					result = result +  "{lat: " + tmp[1] + ", lng: " + tmp[0] + " },";
				}
			}
			
			
			result = result + "];\n";
			
			if(i == polys.size() - 1 ){
				String allPolys = new String();
				for(int j=0;j<i+1;j++){
					allPolys = allPolys + name + j + ", ";
				}
				result = result + "var " + name + " = [" + allPolys + "];";
			}

		}
//		result = result + ";\n";
		return result;
	}
}
