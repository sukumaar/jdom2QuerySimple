package com.sukumaar.jdom2.SimpleQuery;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;

import javaslang.collection.List;

/**
 * @author Sukumaar
 * @since 09-June-2017
 *
 */

public class SimpleRead {
	private String SEPARATOR;
	private List<Element> foundAttributeList;

	public String getSEPARATOR() {
		return SEPARATOR;
	}

	public SimpleRead() {
		super();
		SEPARATOR = ".";

	}

	public void setSEPARATOR(String sEPARATOR) {
		SEPARATOR = sEPARATOR;
	}

	public java.util.List<Element> getData(File xmlFile, String query) throws JDOMException, IOException {
		foundAttributeList = List.of();// initialize with nothing
		// you need to initialize foundAttributeList each time or you would be
		// getting java.lang.NullPointerException from 2nd call to intance of
		// this class
		SAXBuilder builder = new SAXBuilder();
		Document document = (Document) builder.build(xmlFile);
		return getData(document, query);
	}

	public java.util.List<Element> getData(Document doc, String query) {

		Element rootNode = doc.getRootElement();

		String namespace = rootNode.getNamespaceURI();
		Namespace docNameSpace = Namespace.getNamespace(namespace);
		List<String> queryAsImmutableList = List
				.ofAll(Arrays.asList(query.split(SEPARATOR.equals(".") ? "\\." : SEPARATOR)));

		getData(rootNode, docNameSpace, queryAsImmutableList.tail());

		/*
		 * here we are ignoring first element from queryAsImmutableList because
		 * it is root ! so if you provide root tag name in your query then root
		 * element will search for itself in it's children
		 *
		 * and ultimately program wont find rootNode as a child of rootNode , so
		 * it wont execute ahead
		 */

		List<Element> listToReturn = foundAttributeList;
		foundAttributeList = null;
		/*
		 * Here we are copying contents of foundAttributeList to listToReturn
		 * for returning found elements if you don't do it then
		 * foundAttributeList will keep on adding elements bcoz it is Instance
		 * Variable(Object Reference) here
		 * 
		 * After copying into another list we are dereferencing
		 * foundAttributeList
		 */
		return listToReturn.toJavaList();
	}

	private void getData(Element node, Namespace docNameSpace, List<String> queryAsImmutableList) {

		if (queryAsImmutableList.size() > 0) {
			// System.out.println("Currently processing: " +
			// queryAsImmutableList.head());
			List<Element> extractedElement = List.ofAll(node.getChildren(queryAsImmutableList.head(), docNameSpace));
			extractedElement.forEach(x -> {
				getData(x, docNameSpace, queryAsImmutableList.tail());
			});

		} else {
			// System.out.println("Inside else condition");
			foundAttributeList = foundAttributeList.append(node);
		}
		// foundAttributeList is immutable

	}

}

// references:
// vavr(javaslang) example
// http://www.baeldung.com/javaslang
// https://github.com/eugenp/tutorials/blob/master/vavr/src/test/java/com/baeldung/vavr/VavrUnitTest.java