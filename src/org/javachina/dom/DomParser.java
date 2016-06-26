package org.javachina.dom;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DomParser {
	public static void main(String[] args) throws Exception {
		//创建Dom解析器工厂
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		//利用工厂模式创建Dom解析器
		DocumentBuilder builder = dbf.newDocumentBuilder();
		
		File f = new File("./src/config/学校.xml");
		//因为w3c下的document
		Document doc = builder.parse(f);
		
		//树化：结果在内存中进行结构化排序
		doc.normalize();
		
		Element xuexiao = doc.getDocumentElement();
		
		String schoolName = xuexiao.getAttribute("name");
		System.out.println(schoolName);
//		String tagName = xuexiao.getTagName();
//		System.out.println(tagName);
		
		NodeList xiaozhangmen = xuexiao.getElementsByTagName("校长们");
		
		//取得nodelist中item的个数
//		System.out.println(xiaozhangmen.getLength());
		
		for (int i = 0; i < xiaozhangmen.getLength(); i++) {
			Element xiaozhangmens = (Element)xiaozhangmen.item(i);
//			System.out.println(xiaozhangmens.getTagName());
			NodeList zhengxiaozhangmen = xiaozhangmens.getElementsByTagName("正校长");
			for (int j = 0; j < zhengxiaozhangmen.getLength(); j++) {
				Element zhengXiaozhang = (Element)zhengxiaozhangmen.item(j);
				String id = zhengXiaozhang.getElementsByTagName("员工号").item(0).getTextContent();
				System.out.println(id);
//				System.out.println(zhengXiaozhang.getTextContent());
				Element yuangongHao = (Element)zhengXiaozhang.getElementsByTagName("员工号").item(0);
			
				String value = yuangongHao.getFirstChild().getNodeValue();
				System.out.println(value);
			}
		}
		
	}
}
