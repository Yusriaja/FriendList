package id.aditya.friendlist;

import id.aditya.friendlist.database.DsFriend;
import android.content.Intent;
import android.database.Cursor;
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

public class EditDataActivity extends SherlockActivity {
	
	private SubMenu mItem;
	private DsFriend dsfriend;
	private Bundle b;
	private EditText edtName, edtAge, edtAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tambah_data);
		
		ActionBar ab = getSupportActionBar();
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e51c23")));
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle("Edit Data Teman");
		
		edtName = (EditText) findViewById(R.id.edt_name);
		edtAge = (EditText) findViewById(R.id.edt_age);
		edtAddress = (EditText) findViewById(R.id.edt_address);
		
		b = getIntent().getExtras();
		edtName.setText(b.getString("str_name"));
		edtAge.setText(b.getString("str_age"));
		edtAddress.setText(b.getString("str_address"));
		
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
	
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item){
			
			switch(item.getItemId()){
				case 1:
					Cursor cr = dsfriend.EditData(edtName.getText().toString(), edtAge.getText().toString(), edtAddress.getText().toString(), Integer.parseInt(b.getString("str_id").toString()));
					if(cr != null){
						Toast.makeText(getBaseContext(), "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
					}
				break;
				case android.R.id.home:
					NavUtils.navigateUpTo(this, new Intent(this, HomeActivity.class));
					EditDataActivity.this.finish();
					dsfriend.close();
				break;
			}
			return true;
	}

	
}
