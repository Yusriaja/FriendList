package id.aditya.friendlist;

import id.aditya.friendlist.database.DsFriend;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.SubMenu;

public class TambahDataActivity extends SherlockActivity {
	
	private SubMenu mItem;
	private DsFriend dsfriend;
	private EditText edtName, edtAge, edtAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tambah_data);
		
		ActionBar ab = getSupportActionBar();
		//ab.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e51c23")));
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle("Tambah Data Teman");
		
		edtName = (EditText) findViewById(R.id.edt_name);
		edtAge = (EditText) findViewById(R.id.edt_age);
		edtAddress = (EditText) findViewById(R.id.edt_address);
		
		dsfriend = new DsFriend(this);
		dsfriend.open();
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		
		mItem = menu.addSubMenu(0, 1, 0, null);
		mItem.setIcon(R.drawable.ic_content_save);
		mItem.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return true;
	}
	
	private void ClearData(){
		edtName.setText("");
		edtAge.setText("");
		edtAddress.setText("");
	}
	
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item){
		
		switch(item.getItemId()){
			case 1:
				
				if(edtName.getText().toString().matches("") 
						|| edtAge.getText().toString().matches("") 
						|| edtAddress.getText().toString().matches("")){
					Toast.makeText(getBaseContext(), "Isi Semua data dengan benar", Toast.LENGTH_SHORT).show();
				} else {
					long data_return;
					data_return = dsfriend.SimpanData(
									edtName.getText().toString(), 
									edtAge.getText().toString(), 
									edtAddress.getText().toString());
					
					if(data_return != 0){
						Toast.makeText(getBaseContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
						ClearData();
					} else {
						Toast.makeText(getBaseContext(), "Data gagal disimpan", Toast.LENGTH_SHORT).show();
					}
				}
				
				break;
			
			case android.R.id.home:
				NavUtils.navigateUpTo(this, new Intent(this, HomeActivity.class));
				TambahDataActivity.this.finish();
				dsfriend.close();
				break;
		}
		
		return true;
	}
	
}
