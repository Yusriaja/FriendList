package id.aditya.friendlist.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
	
	private static final String db_name = "db_friend";
	private static final int db_version = 1;

	public Database(Context context) {
		super(context, db_name, null, db_version);
		// TODO Auto-generated constructor stub
	}
	
	//Table Friend
	public static final String tb_friend = "tb_friend";
	public static final String id_friend = "id_friend";
	public static final String name = "name";
	public static final String age = "age";
	public static final String address = "address";
	
	private static final String create_tb_friend = "create table "
			+ tb_friend + "("
			+ id_friend + " integer primary key autoincrement,"
			+ name + " varchar(25) not null,"
			+ age + " varchar(2) not null,"
			+ address + " varchar(50) null);";

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(create_tb_friend);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("drop table if exists" + tb_friend);
		
		onCreate(db);
	}
	
}
