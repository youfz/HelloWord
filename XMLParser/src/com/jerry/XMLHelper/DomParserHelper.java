package com.jerry.XMLHelper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import com.jerry.entity.channel;

public class DomParserHelper {
	
	public static List<channel> getChannelList(InputStream stream)
	{
		List<channel> list=new ArrayList<channel>();
		
		//�õ� DocumentBuilderFactory ����, �ɸö�����Եõ� DocumentBuilder ����
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		
		try {
			//�õ�DocumentBuilder����
			DocumentBuilder builder=factory.newDocumentBuilder();
			//�õ���������xml��Document����
			Document document=builder.parse(stream);
			//�õ� "���ڵ�" 
			Element root=document.getDocumentElement();
			//��ȡ���ڵ������items�Ľڵ�
			NodeList items=root.getElementsByTagName("item");  
			//�������нڵ�
			for(int i=0;i<items.getLength();i++)
			{
				channel chann=new channel();
				Element item=(Element)items.item(i);
				chann.setId(item.getAttribute("id"));
				chann.setUrl(item.getAttribute("url"));
				chann.setName(item.getFirstChild().getNodeValue());
				list.add(chann);
			}
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

}
