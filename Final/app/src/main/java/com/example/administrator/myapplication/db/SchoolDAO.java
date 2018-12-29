package com.example.administrator.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.administrator.myapplication.bean.Info;

import java.util.ArrayList;
import java.util.List;

public class SchoolDAO {

	private DBOpenHelper helper;
	private SQLiteDatabase db;

	public SchoolDAO(Context context) {

		helper = new DBOpenHelper(context);

	}

	public void add(Info info) {
		db = helper.getWritableDatabase();
		db.execSQL("insert into tb_school values(?,?,?)", new String[] {
				info.getId(), info.getName(), info.getTime()});
		db.close();
	}



	public void addContentValues(Info info) {
		db = helper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put("Id", info.getId());
		cv.put("Name", info.getName());
		cv.put("Time", info.getTime());
		db.insert("tb_school", null, cv);
		db.close();

	}


	public List<Info> quereyTable() {
		List<Info> listInfos = new ArrayList<Info>();
		db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from tb_school", null);
		while (cursor.moveToNext()) {
			Info info = new Info();
			info.setId(cursor.getString(0));
			info.setName(cursor.getString(1));
			info.setTime(cursor.getString(2));
			listInfos.add(info);
			db.close();
		}
		return listInfos;

	}

	public Info quereyStudentRaw(Info info) {
		db = helper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from tb_school where Id=?",
				new String[] { info.getId() });
		while (cursor.moveToNext()) {

			info.setId(cursor.getString(cursor.getColumnIndex("id")));
			// info.setId(cursor.getString(0));
			info.setName(cursor.getString(1));
			info.setTime(cursor.getString(2));
			db.close();
		}
		return info;
	}

	public List<Info> queryAll(Info info) {
		List<Info> sList = new ArrayList<Info>();
		db = helper.getWritableDatabase();
		Cursor cursor = db.query("tb_school", null, null, null, null, null,
				null);
		while (cursor.moveToNext()) {
			Info info1 = new Info(cursor.getString(0),
					cursor.getString(1), cursor.getString(2)
					);
          sList.add(info1);
		}
		return sList;
	}
	public Info quereyStuden(Info info) {
		db = helper.getReadableDatabase();
		Cursor cursor=db.query("tb_school", null, "id=?", new String[] { info.getId() }, null, null, null);
				while (cursor.moveToNext()) {


					info.setId(cursor.getString(cursor.getColumnIndex("id")));
					// info.setId(cursor.getString(0));
					info.setName(cursor.getString(1));
					info.setTime(cursor.getString(2));
					db.close();
				}
				return info;

	}

//	public void upDate(Info info) {
//		db = helper.getWritableDatabase();
//		db.execSQL(
//				"update tb_school set Name=?,Time=?,Qq=?,Tel=? where Id=?",
//				new String[] { info.getName(), info.getTime(), info.getQq(), info.getId()});
//		db.close();
//	}


//	public int delete(String id) {
//		db = helper.getWritableDatabase();
//		Info info = new Info();
//		int count=db.delete("tb_school", "Id=?", new String[]{info.getId()});
//		db.close();
//		return count;
//	}
}
