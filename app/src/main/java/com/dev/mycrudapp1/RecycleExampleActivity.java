package com.dev.mycrudapp1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class RecycleExampleActivity extends AppCompatActivity {
    private ArrayList<Student> students = new ArrayList<Student>();
    private SudentRecyclerAdapter adapter;

    private View.OnClickListener itemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //Student student = (Student) view.getTag();
            int index = (int)view.getTag(R.string.index);
            Student student = students.get(index);
            Intent intent = new Intent(RecycleExampleActivity.this,AddEditActivity.class);
            intent.putExtra(Util.INDEX,index);
            intent.putExtra(Util.NAME,student.getName());
            intent.putExtra(Util.LAST_NAME,student.getLastName());
            intent.putExtra(Util.ROLL_NUM,student.getRollNum());
            intent.putExtra(Util.GENDER,student.getGender());
            intent.putExtra(Util.MATH,student.isMath());
            intent.putExtra(Util.PHY,student.isPhysics());
            intent.putExtra(Util.CHE,student.isChemistry());
            intent.putExtra(Util.ENG,student.isEnglish());
            intent.putExtra(Util.TAMIL,student.isTamil());
            startActivityForResult(intent,Util.REQ_CODE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_example);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.students);
        actionBar.setSubtitle(R.string.manage_students);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        adapter = new SudentRecyclerAdapter(this,students,itemClickListener);

        recyclerView.setAdapter(adapter);






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

                Student student = new Student();
                student.setName(bundle.getString(Util.NAME));
                student.setLastName(bundle.getString(Util.LAST_NAME));
                student.setRollNum(bundle.getString(Util.ROLL_NUM));
                student.setGender(bundle.getInt(Util.GENDER));
                student.setMath(bundle.getBoolean(Util.MATH));
                student.setPhysics(bundle.getBoolean(Util.PHY));
                student.setChemistry(bundle.getBoolean(Util.CHE));
                student.setEnglish(bundle.getBoolean(Util.ENG));
                student.setTamil(bundle.getBoolean(Util.TAMIL));

                if (bundle.getInt(Util.INDEX) == Util.NEW_ENTRY) {
                    students.add(student);
                }else{
                    students.set(bundle.getInt(Util.INDEX),student);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }
}
