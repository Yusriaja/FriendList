package id.aditya.friendlist.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DsFriend {
	private SQLiteDatabase database;
	private Database db;
	
	public DsFriend(Context context){
		db = new Database(context);
	}
	
	public void open() throws SQLException{
		database = db.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	public long SimpanData(String str_name, String str_age, String str_address){
		ContentValues values = new ContentValues();
		values.put("name", str_name);
		values.put("age", str_age);
		values.put("address", str_address);
		
		long data_return = database.insert("tb_friend", null, values);
		
		return data_return;
	}
	
	public Cursor ReadData(){
		String query = "SELECT * FROM tb_friend";
		Cursor cr = database.rawQuery(query, null);
		return cr;
	}
	
	public long DeleteData(int str_id){
		long delete = database.delete("tb_friend", "id_friend = " + str_id, null);
		return delete;
	}
	
	public Cursor EditData(String str_name, String str_age, String str_address, int str_id){
		String query = "UPDATE tb_friend SET name = '" + str_name  + "', age = '" + str_age  + "', " +
						" address = '" + str_address  + "' WHERE id_friend = " + str_id  + "";
		Cursor cr = database.rawQuery(query, null);
		if(cr != null){
			cr.moveToFirst();
		}
		return cr;
		
	}
	
}
