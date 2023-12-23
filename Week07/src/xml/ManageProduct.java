package xml;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class ManageProduct {
	private static String fileName = "src//qlsp//products.xml";
	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document;
	
	
	public ManageProduct() {
		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			document = builder.parse(fileName);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<Product> getAllProducts() {
		ArrayList<Product> list = new ArrayList<>();
		
		Element root = document.getDocumentElement();
		NodeList pList = root.getElementsByTagName("product");
		
		int pCount = pList.getLength();
		for (int i=0; i<pCount; i++) {
			Element pNode = (Element) pList.item(i);
			String productID = pNode.getAttribute("id");
			String name = pNode.getElementsByTagName("productName").item(0).getTextContent();
			String manufacture = pNode.getElementsByTagName("manufacture").item(0).getTextContent();
			String description = pNode.getElementsByTagName("description").item(0).getTextContent();
			
			Element sNode = (Element) pNode.getElementsByTagName("supplier").item(0);
			String sName = sNode.getElementsByTagName("supplierName").item(0).getTextContent();
			String country = sNode.getElementsByTagName("country").item(0).getTextContent();
			String website = sNode.getElementsByTagName("website").item(0).getTextContent();
			
			Supplier supplier = new Supplier(sName, country, website);
			String sPrice = pNode.getElementsByTagName("price").item(0).getTextContent();
			
			double price = 0.0;
			try {
				price = Double.valueOf(sPrice);
			} catch (NumberFormatException ex) {
				ex.printStackTrace();
			}
			
			Product product = new Product(productID, name, manufacture, description, supplier, price);
			list.add(product);
		}
		
		return list;
	}
	
	
	public void addProduct (Product p) {
		Element root = document.getDocumentElement();
		
		Element pNode = document.createElement("product");
		root.appendChild(pNode);
		pNode.setAttribute("id", p.getProductID());
		
		Node nameNode0 = document.createElement("productName");
		pNode.appendChild(nameNode0);
		nameNode0.setTextContent(p.getName());
		
		Node nameNode1 = document.createElement("manufacture");
		pNode.appendChild(nameNode1);
		nameNode0.setTextContent(p.getManufacture());
		
		Node nameNode2 = document.createElement("description");
		pNode.appendChild(nameNode2);
		nameNode0.setTextContent(p.getDescription());
		
			Element pNodes = document.createElement("supplier");
			pNode.appendChild(pNodes);
			
			Node nameNode11 = document.createElement("supplierName");
			pNode.appendChild(nameNode11);
			nameNode11.setTextContent(p.getSupplier().getName());
			
			Node nameNode22 = document.createElement("country");
			pNode.appendChild(nameNode22);
			nameNode11.setTextContent(p.getSupplier().getCountry());
			
			Node nameNode33 = document.createElement("website");
			pNode.appendChild(nameNode33);
			nameNode11.setTextContent(p.getSupplier().getWebsite());
			
		Node nameNode3 = document.createElement("price");
		pNode.appendChild(nameNode3);
		nameNode3.setTextContent(p.getPrice() + "");
	}
	
	
	public void deleteProduct (String pID) {
		Element root = document.getDocumentElement();
		NodeList pList = root.getElementsByTagName("product");
		
		for (int i=0; i<pList.getLength(); i++) {
			Element pNode = (Element) pList.item(i);
			String productID = pNode.getAttribute("id");
			if (productID.equalsIgnoreCase(pID)) {
				pNode.getParentNode().removeChild(pNode);
				break;
			}
		}
	}
	
	
	public void updatePrice (String pID, double newPrice) {
		Element root = document.getDocumentElement();
		NodeList pList = root.getElementsByTagName("product");
		
		for (int i=0; i<pList.getLength(); i++) {
			Element pNode = (Element) pList.item(i);
			String productID = pNode.getAttribute("id");
			if (productID.equalsIgnoreCase(pID)) {
				Node priceNode = pNode.getElementsByTagName(productID).item(0);
				priceNode.setTextContent(newPrice + "");
				break;
			}
		}
	}
		
		
	public void writeXMLFile() {
		TransformerFactory factory = null;
		Transformer transformer = null;
		
		try {
			factory = TransformerFactory.newInstance();
			transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(System.out));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	
	public void printAll() {
		TransformerFactory factory = null;
		Transformer transformer = null;
		
		try {
			factory = TransformerFactory.newInstance();
			transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(new DOMSource(document), new StreamResult(System.out));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
