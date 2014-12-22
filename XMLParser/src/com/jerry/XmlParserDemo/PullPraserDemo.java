package com.jerry.XmlParserDemo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xmlpull.v1.XmlPullParserException;


import android.app.ListActivity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.SimpleAdapter;

public class PullPraserDemo extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.list, new String[] { "id", "name" }, new int[] {
						R.id.textId, R.id.textName });
		setListAdapter(adapter);
	}

	private List<Map<String, String>> getData() {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		XmlResourceParser xrp = getResources().getXml(R.xml.channels);

		try {
			// ֱ���ĵ��Ľ�β��
			while (xrp.getEventType() != XmlResourceParser.END_DOCUMENT) {
				// ��������˿�ʼ��ǩ
				if (xrp.getEventType() == XmlResourceParser.START_TAG) {
					String tagName = xrp.getName();// ��ȡ��ǩ������
					if (tagName.equals("item")) {
						Map<String, String> map = new HashMap<String, String>();
						String id = xrp.getAttributeValue(null, "id");// ͨ������������ȡ����ֵ
						map.put("id", id);
						String url = xrp.getAttributeValue(1);// ͨ��������������ȡ����ֵ
						map.put("url", url);
						map.put("name", xrp.nextText());
						list.add(map);
					}
				}
				xrp.next();// ��ȡ������һ���¼�
			}
		} catch (XmlPullParserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

}
