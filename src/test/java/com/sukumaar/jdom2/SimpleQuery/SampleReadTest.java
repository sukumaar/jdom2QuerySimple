package com.sukumaar.jdom2.SimpleQuery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.junit.Test;


/**
 * Unit test for SampleRead class.
 */
public class SampleReadTest

{

	@Test
	public void testJDOM2SimpleQuery() throws JDOMException, IOException {
		SimpleRead obj = new SimpleRead();
		File xmlFile = new File("src/main/resources/xml/sample.xml");
		
		String query1 = "company.staff.personalInfo.name.firstname";
		List<Element> receiveData1 = obj.getData(xmlFile, query1);
		System.out.println(receiveData1.size());
		System.out.println(receiveData1);

		String query2 = "company.staff.personalInfo.phone";
		List<Element> receiveData2 = obj.getData(xmlFile, query2);
		System.out.println(receiveData2.size());
		//iterating over received data
		receiveData2.forEach(x -> {
			System.out.println(x);
		});
	}
}
