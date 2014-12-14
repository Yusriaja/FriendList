package id.aditya.friendlist;

import java.util.ArrayList;

import id.aditya.friendlist.database.DsFriend;
import id.aditya.friendlist.kelas.ListAdapter;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ActionMode;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.SubMenu;

public class HomeActivity extends SherlockActivity implements ActionMode.Callback, android.widget.AdapterView.OnItemLongClickListener {
	
	private SubMenu mItem;
	private DsFriend dsfriend;
	private ListView dataList;
	private ListAdapter adapter;
	private ArrayList<String> dataNama = new ArrayList<String>();
	private ArrayList<String> dataId = new ArrayList<String>();
	private ArrayList<String> dataAge = new ArrayList<String>();
	private ArrayList<String> dataAddress = new ArrayList<String>();
	private ActionMode mActionMode;
	private TextView txtId, txtName, txtAge, txtAddress;
	private String str_id;

	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		ActionBar ab = getSupportActionBar();
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#e51c23")));
		
		dsfriend = new DsFriend(this);
		dsfriend.open();
		
		dataList = (ListView) findViewById(R.id.list_friend);
		dataList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				txtId = (TextView) view.findViewById(R.id.txtId);
				txtName = (TextView) view.findViewById(R.id.txtNama);
				txtAge = (TextView) view.findViewById(R.id.txtAge);
				txtAddress = (TextView) view.findViewById(R.id.txtAddress);
				
				//Toast.makeText(getBaseContext(), txtName.getText().toString(), Toast.LENGTH_SHORT).show();
				// TODO Auto-generated method stub
				Intent a = new Intent(HomeActivity.this, ViewDataActivity.class);
				a.putExtra("str_name", txtName.getText().toString());
				a.putExtra("str_age", txtAge.getText().toString());
				a.putExtra("str_address", txtAddress.getText().toString());
				startActivity(a);
			}
		});
		
		ReadData();
	}
	
	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu){
		
		mItem = menu.addSubMenu(0, 1, 0, null);
		mItem.setIcon(R.drawable.ic_content_new);
		mItem.getItem().setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
		
		return true;
	}
	
	public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem item){
		
		switch(item.getItemId()){
			case 1:
				Intent a = new Intent(HomeActivity.this, TambahDataActivity.class);
				startActivity(a);
				HomeActivity.this.finish();
				break;
		}
		
		return true;
	}
	
	private void ReadData(){
		Cursor cr = dsfriend.ReadData();
		if(cr != null){
			if(cr.moveToFirst()){
				do {
					dataId.add(cr.getString(0));
					dataNama.add(cr.getString(1));
					dataAge.add(cr.getString(2));
					dataAddress.add(cr.getString(3));
				} while(cr.moveToNext());
			}
			
		}
		adapter = new ListAdapter(this, dataNama, dataId, dataAge, dataAddress);
		dataList.setAdapter(adapter);
		dataList.setOnItemLongClickListener(this);
	}
	
	private void ClearData(){
		adapter.clear();
		dataList.setAdapter(adapter);
	}

	@Override
	public boolean onCreateActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = mode.getMenuInflater();
		inflater.inflate(R.menu.context_menu, menu);
		return true;
	}

	@Override
	public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
			case R.id.action_hapus:
				long delete = dsfriend.DeleteData(Integer.valueOf(str_id));
				if(delete != 0){
					Toast.makeText(getBaseContext(), "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
					ClearData();
					ReadData();
					mActionMode.finish();
				} else {
					Toast.makeText(getBaseContext(), "Data gagal dihapus", Toast.LENGTH_SHORT).show();
				}
				return true;
				
			case R.id.action_edit:
				Intent intent = new Intent(this, EditDataActivity.class);
				intent.putExtra("str_name", txtName.getText().toString());
				intent.putExtra("str_id", txtId.getText().toString());
				intent.putExtra("str_age", txtAge.getText().toString());
				intent.putExtra("str_address", txtAddress.getText().toString());
				startActivity(intent);
				mActionMode.finish();
				HomeActivity.this.finish();
				//Toast.makeText(getBaseContext(), txtId.getText().toString() + txtName.getText().toString() + txtAge.getText().toString() + txtAddress.getText().toString(), Toast.LENGTH_SHORT).show();
				return true;
				
			default:
				return false;
		}
	}

	@Override
	public void onDestroyActionMode(ActionMode mode) {
		// TODO Auto-generated method stub
		mActionMode = null;
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {
		// TODO Auto-generated method stub
		if(mActionMode != null){
			return false;
		}
		txtId = (TextView) view.findViewById(R.id.txtId);
		txtName = (TextView) view.findViewById(R.id.txtNama);
		txtAge = (TextView) view.findViewById(R.id.txtAge);
		txtAddress = (TextView) view.findViewById(R.id.txtAddress);
		
		mActionMode = this.startActionMode(this);
		mActionMode.setTitle(txtName.getText().toString());
		str_id = txtId.getText().toString();
		view.setSelected(true);
		return true;
	}
	
}
