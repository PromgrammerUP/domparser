package org.javachina.dom;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

public class DomParser2 {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		File f = new File("./src/config/person.xml");
		Document doc = builder.parse(f);
		doc.normalize();
		
		Element root = doc.getDocumentElement();
		
		String name = root.getAttribute("name");
		System.out.println(name);
		
		NodeList persons = root.getElementsByTagName("person");
		
		for (int i = 0; i < persons.getLength(); i++) {
			Element person = (Element)persons.item(i);
			Element nameElement = (Element)person.getElementsByTagName("name").item(0);
			Node node = nameElement.getFirstChild();
			String nameValue = node.getNodeValue();
			System.out.println(nameValue);
			String name2 = nameElement.getTextContent();
			System.out.println(name2);
			String age = person.getElementsByTagName("age").item(0).getFirstChild().getNodeValue();
			
			String sex = person.getElementsByTagName("sex").item(0).getTextContent();
			
			System.out.println(nameValue+":"+age+":"+sex);
//			if(nameValue.equals("张三")){
//				Element ageElement = (Element)person.getElementsByTagName("age").item(0);
//				//ageElement.setTextContent(Integer.toString(Integer.parseInt(age)+1));
//				ageElement.getFirstChild().setNodeValue(Integer.toString(Integer.parseInt(age)+1));
//			}
//			if(nameValue.equals("张三")){
////				Element ageElement = (Element)person.getElementsByTagName("age").item(0);
////				person.removeChild(ageElement);
//				root.removeChild(person);
//			}
		}
		
		//增加节点：增加一个Person
		Element personElement = doc.createElement("person");
		Element nameElement = doc.createElement("name");
		Element ageElement = doc.createElement("age");
		Element sexElement = doc.createElement("sex");
		
		//创建文本节点
		Text nameText = doc.createTextNode("陈胜");
		Text ageText = doc.createTextNode("26");
		Text sexText = doc.createTextNode("male");
		//元素套接
		
		nameElement.appendChild(nameText);
		ageElement.appendChild(ageText);
		sexElement.appendChild(sexText);
		
		personElement.appendChild(nameElement);
		personElement.appendChild(ageElement);
		personElement.appendChild(sexElement);
		
		root.appendChild(personElement);
		
		//把xml序列化到另外的地方
		
		
		OutputStream os = new FileOutputStream("./src/config/person2.xml");
		//规范文档格式(param1:规范输出哪个文档，param2：采用的编码格式，param3：是否缩进）
		OutputFormat format =  new OutputFormat(doc, "GBK",true);
		
		//创建序列化器：(param1：向哪里输出，param2：定义文档格式）
		XMLSerializer ser = new XMLSerializer(os, format);
		//以DOM形式序列化
		ser.asDOMSerializer();
		ser.serialize(doc);
		
		
	}
}
