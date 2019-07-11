package com.dev.mycrudapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //private String[] students = new String[4];
    //private ArrayList<String> students = new ArrayList<String>();
    //private ArrayAdapter<String> adapter;
    private ArrayList<HashMap<String,Object>> students = new ArrayList<HashMap<String, Object>>();
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.students);
        actionBar.setSubtitle(R.string.manage_students);
        ListView listView = (ListView)findViewById(R.id.listView);

        //adapter = new ArrayAdapter<String>(this,R.layout.item_layout,students);
        adapter = new SimpleAdapter(this,
                students,
                R.layout.student_item,
                new String[]{Util.NAME,Util.LAST_NAME,Util.ROLL_NUM},
                new int[]{R.id.firstNameTx,R.id.lastNameTx,R.id.rollNumTx});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int index, long id) {
                HashMap<String,Object> student = students.get(index);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int index, long id) {

                displayDeleteAlert(index);
                return true;
            }
        });

    }

    private void displayDeleteAlert(final int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Wait");
        builder.setMessage("Are you sure to Delete this entry?");
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                students.remove(index);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancel",null);

        builder.setCancelable(false);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add:
                //Util.display(this,"Add menu pressed");
                Intent intent = new Intent(this,AddEditActivity.class);
                intent.putExtra(Util.INDEX,Util.NEW_ENTRY);
                startActivityForResult(intent,Util.REQ_CODE);
                break;

            case R.id.action_my:

                break;

            case R.id.action_logout:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Util.REQ_CODE){
            if (resultCode == Util.RES_CODE){
                Bundle bundle = data.getExtras();
                //students.add(bundle.getString(Util.NAME));

                HashMap<String,Object> hashMap = new HashMap<String, Object>();
                hashMap.put(Util.NAME,bundle.getString(Util.NAME));
                hashMap.put(Util.LAST_NAME,bundle.getString(Util.LAST_NAME));
                hashMap.put(Util.ROLL_NUM,bundle.getString(Util.ROLL_NUM));
                hashMap.put(Util.GENDER,bundle.getString(Util.GENDER));
                hashMap.put(Util.MATH,bundle.getString(Util.MATH));
                hashMap.put(Util.PHY,bundle.getString(Util.PHY));
                hashMap.put(Util.CHE,bundle.getString(Util.CHE));
                hashMap.put(Util.ENG,bundle.getString(Util.ENG));
                hashMap.put(Util.TAMIL,bundle.getString(Util.TAMIL));
                students.add(hashMap);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
