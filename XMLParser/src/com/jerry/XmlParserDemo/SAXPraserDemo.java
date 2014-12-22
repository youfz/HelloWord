package com.jerry.XmlParserDemo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.jerry.XMLHelper.SAXPraserHelper;
import com.jerry.entity.channel;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class SAXPraserDemo extends ListActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		SimpleAdapter adapter = null;
		try {
			adapter=new SimpleAdapter(this, getData(), R.layout.list,new String[]{"name","id"},new  int[]{
				R.id.textName,R.id.textId	
			});
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
		setListAdapter(adapter);
	}
	
	private List<Map<String, String>> getData() throws ParserConfigurationException, SAXException, IOException
	{
		List<channel> list;
		list=getChannelList();
		List<Map<String, String>> mapList=new ArrayList<Map<String,String>>();
		for(int i=0;i<list.size();i++)
		{
			Map<String, String> map=new HashMap<String, String>();
			map.put("name", list.get(i).getName());
			map.put("id", list.get(i).getId());
			mapList.add(map);
		}
		return mapList;
		
	}
	
	private List<channel> getChannelList() throws ParserConfigurationException, SAXException, IOException
	{
		//ʵ����һ��SAXParserFactory����
		SAXParserFactory factory=SAXParserFactory.newInstance();
		SAXParser parser;
		//ʵ����SAXParser���󣬴���XMLReader���󣬽�����
		parser=factory.newSAXParser();
		XMLReader xmlReader=parser.getXMLReader();
		//ʵ����handler���¼�������
		SAXPraserHelper helperHandler=new SAXPraserHelper();
		//������ע���¼�
		xmlReader.setContentHandler(helperHandler);
		//��ȡ�ļ���
		InputStream stream=getResources().openRawResource(R.raw.channels);
		InputSource is=new InputSource(stream);
		//�����ļ�
		xmlReader.parse(is);
		return helperHandler.getList();
	}
	

}
