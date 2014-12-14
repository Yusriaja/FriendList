package id.aditya.friendlist;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.widget.TextView;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.SubMenu;

public class ViewDataActivity extends SherlockActivity {
	
	private SubMenu mItem;
	private Bundle b;
	private TextView txtName, txtAge, txtAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_data);
		
		ActionBar ab = getSupportActionBar();
		//ab.setBackgroundDrawable(new ColorDrawable(Color.DKGRAY));
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e51c23")));
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setTitle("Lihat Data Teman");
		
		txtName = (TextView) findViewById(R.id.txtViewName);
		txtAge = (TextView) findViewById(R.id.txtViewAge);
		txtAddress = (TextView) findViewById(R.id.txtViewAddress);
		
		b = getIntent().getExtras();
		txtName.setText(b.getString("str_name"));
		txtAge.setText(b.getString("str_age"));
		txtAddress.setText(b.getString("str_address"));
		
	}
	
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item){
			
			switch(item.getItemId()){
			case android.R.id.home:
				NavUtils.navigateUpTo(this, new Intent(this, HomeActivity.class));
				ViewDataActivity.this.finish();
				break;
			}
			return true;
	}

}
