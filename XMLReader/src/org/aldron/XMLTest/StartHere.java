package org.aldron.XMLTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class StartHere {

	public static void main(String[] args) throws XMLStreamException {
	
		XMLInputFactory factory = XMLInputFactory.newInstance();
	    XMLStreamReader reader = factory.createXMLStreamReader(
	            ClassLoader.getSystemResourceAsStream("postcode-boundaries.kml"));
        		//ClassLoader.getSystemResourceAsStream("newbounds.kml"));
	    
	    List<postcodeBoundary> postcodes = new ArrayList<>();;
	    postcodeBoundary currCode = null;
	    String tagContent = null;

		while(reader.hasNext()){
			int event = reader.next();
			switch(event){
			//This deals with the contents of an XML field
			case XMLStreamConstants.START_ELEMENT:
			 		
		 		switch(reader.getLocalName()){
		 		case "Placemark":
		 			currCode = new postcodeBoundary();
		 			break;			
		 		}
		 		break;
			
			//This deals with the contents of an XML field
			case XMLStreamConstants.CHARACTERS:
			 	tagContent = reader.getText().trim();
			 	break;
			 		
			//This deals with the closing of an XML field
			case XMLStreamConstants.END_ELEMENT:
				switch(reader.getLocalName()){
				case "name":
			 		if(currCode!=null)
			 			currCode.setName(tagContent);
			 		break;	    	 		
				case "coordinates":
					currCode.addCoords(tagContent);
			 		break;				
				case "Placemark":		
					postcodes.add(currCode);
					break;
				}
				break;
				
			}	
		}
		
		
//			System.out.println(cache.getName());
//			System.out.println(cache.getPolys());
		try{
		    PrintWriter writer = new PrintWriter("polys.js");
		    String PCNames = new String();
		    PCNames = "\nvar allBounds = [";
			for(postcodeBoundary cache:postcodes){
				PCNames = PCNames + cache.getName() + ", ";
				writer.println(cache.getPolys());
			}
		    PCNames = PCNames + "];";
			writer.println(PCNames);

			
		    writer.close();

		} catch (IOException e) {
		   // do something
		}
		

			
}
	
	

}
