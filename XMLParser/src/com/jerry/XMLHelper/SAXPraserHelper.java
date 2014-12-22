package com.jerry.XMLHelper;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.jerry.entity.channel;

public class SAXPraserHelper extends DefaultHandler {

	final int ITEM = 0x0005;

	List<channel> list;
	channel chann;
	int currentState = 0;

	public List<channel> getList() {
		return list;
	}

	/*
	 * �ӿ��ַ���֪ͨ
	 */
	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		// super.characters(ch, start, length);
		String theString = String.valueOf(ch, start, length);
		if (currentState != 0) {
			chann.setName(theString);
			currentState = 0;
		}
		return;
	}

	/*
	 * �����ĵ�����֪ͨ
	 */
	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}

	/*
	 * ���ձ�ǩ����֪ͨ
	 */
	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		if (localName.equals("item"))
			list.add(chann);
	}

	/*
	 * �ĵ���ʼ֪ͨ
	 */
	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		list = new ArrayList<channel>();
	}

	/*
	 * ��ǩ��ʼ֪ͨ
	 */
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		chann = new channel();
		if (localName.equals("item")) {
			for (int i = 0; i < attributes.getLength(); i++) {
				if (attributes.getLocalName(i).equals("id")) {
					chann.setId(attributes.getValue(i));
				} else if (attributes.getLocalName(i).equals("url")) {
					chann.setUrl(attributes.getValue(i));
				}
			}
			currentState = ITEM;
			return;
		}
		currentState = 0;
		return;
	}
}
