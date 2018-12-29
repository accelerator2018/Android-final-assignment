package com.example.administrator.myapplication.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Xml;

import com.example.administrator.myapplication.bean.Info;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Service {

	private Context context;

	public Service(Context context) {
		super();
		this.context = context;
	}

	public List<Info> parserXml(String filesName) throws IOException,
            XmlPullParserException {
		List<Info> students=new ArrayList<Info>();
		Info student=null;
		AssetManager aManage = context.getAssets();
		InputStream is = null;
		XmlPullParser parser =null;
		
		is=aManage.open(filesName);
		parser=	Xml.newPullParser();
		parser.setInput(is, "utf-8");
		
		int event = parser.getEventType();
		while (event!= XmlPullParser.END_DOCUMENT) {
			 switch (event) {

			 case XmlPullParser.START_TAG:
				 if ("student".equalsIgnoreCase(parser.getName())) {
					student=new Info();
					student.setId(parser.getAttributeValue(0));
					break;
				}
				 if (student!=null) {
					 if ("name".equalsIgnoreCase(parser.getName())) {
						 student.setName(parser.nextText());
					 }
					 if ("time".equalsIgnoreCase(parser.getName())) {
						 student.setTime(parser.nextText());
					 }
					
				}
				 break;
			case XmlPullParser.END_TAG:
				if ("student".equalsIgnoreCase(parser.getName())) {
					students.add(student);
					student=null;
				}
				break;

			default:
				break;
			}
			 event = parser.next();
		}
		if(is !=null){
			is.close();
		}
		SharedPreferences preferences = context.getSharedPreferences("contact", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putBoolean("first", false);
		editor.commit();
		return students;
	}
}
