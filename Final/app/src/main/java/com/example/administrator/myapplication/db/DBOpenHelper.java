package com.example.administrator.myapplication.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {
      

	private static final String DATABASE="school.db";
    private static final int VENSION=1;
    
	
	public DBOpenHelper(Context context) {
		super(context, DATABASE, null, VENSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		 String sql = "create table tb_school(Id varchar(10) primary key,Name varchar(10),Time varchar(20))";
		  arg0.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
	}

}
