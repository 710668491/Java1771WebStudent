package DOM;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.junit.Test;

public class DOMTest1 {
	
	@Test
	public void test1() {
	    try {
	    	SAXReader reader = new SAXReader();
			Document document = reader.read(new File("./src/DOM/contacts.xml"));
			Iterator<Node> iterator = document.nodeIterator();
			while (iterator.hasNext()) {
				Node node = iterator.next();
				System.out.println(node.getName());
					if (node instanceof Element) {
						Element element = (Element) node;
						Iterator<Node> it = element.nodeIterator();
							while (it.hasNext()) {
								Node n = it.next();
								System.out.println(n.getName());
								
							}
					}
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}		
				
	}
	
	@Test
	public void test2() throws Exception {
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("./src/DOM/contacts.xml"));
		Element rootElement = document.getRootElement();
		getChildNodes(rootElement);
	}

	private void getChildNodes(Element rootElement) {
		System.out.println(rootElement.getName());
		Iterator<Node> iterator = rootElement.nodeIterator();
		while (iterator.hasNext()) {
			Node node = iterator.next();
			if (node instanceof Element) {
				Element element = (Element) node;
				getChildNodes(element);
			}
		}
		
	}
	
	@Test
	public void test3() throws Exception {
		List<Contact> list = new ArrayList<Contact>();
		SAXReader reader = new SAXReader();
		Document document = reader.read(new File("./src/DOM/contacts.xml"));
		Element rootElement = document.getRootElement();
		Iterator<Element> iterator = rootElement.elementIterator("contact");
		while (iterator.hasNext()) {
			Element element = iterator.next();
			Contact contact = new Contact();
			contact.setId(element.attributeValue("id"));
			contact.setName(element.elementText("name"));
			contact.setAge(element.elementText("age"));
			contact.setPhone(element.elementText("phone"));
			contact.setEmail(element.elementText("email"));
			contact.setQq(element.elementText("qq"));
			list.add(contact);
			
		}
		for (Contact contact : list) {
			System.out.println(contact);
		}

	}

}
