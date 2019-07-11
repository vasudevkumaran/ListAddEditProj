package com.dev.mycrudapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddEditActivity extends AppCompatActivity {

    private EditText nameEd;
    private EditText lastNameEd;
    private EditText rollNumEd;
    private RadioButton maleRd;
    private RadioButton femaleRd;
    private RadioButton transRd;
    private CheckBox mathCb;
    private CheckBox phyCb;
    private CheckBox cheCb;
    private CheckBox tamCb;
    private CheckBox engCb;
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        ActionBar actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);
        nameEd = (EditText)findViewById(R.id.nameEdit);
        lastNameEd = (EditText)findViewById(R.id.lastNameEdit);
        rollNumEd = (EditText)findViewById(R.id.rollNumEdit);

        maleRd = (RadioButton)findViewById(R.id.maleRd);
        femaleRd = (RadioButton)findViewById(R.id.femaleRd);
        transRd = (RadioButton)findViewById(R.id.transRd);

        mathCb = (CheckBox)findViewById(R.id.matchCb);
        phyCb = (CheckBox)findViewById(R.id.phyCb);
        cheCb = (CheckBox)findViewById(R.id.cheCb);
        tamCb = (CheckBox)findViewById(R.id.tamCb);
        engCb = (CheckBox)findViewById(R.id.engCb);

        Button saveBtn = (Button)findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra(Util.NAME,nameEd.getText().toString());
                intent.putExtra(Util.LAST_NAME,lastNameEd.getText().toString());
                intent.putExtra(Util.ROLL_NUM,rollNumEd.getText().toString());
                if (maleRd.isChecked()){
                    intent.putExtra(Util.GENDER,Util.MALE);
                }else if (femaleRd.isChecked()){
                    intent.putExtra(Util.GENDER,Util.FEMALE);
                }else{
                    intent.putExtra(Util.GENDER,Util.TRANS_GENDER);
                }

                intent.putExtra(Util.MATH,mathCb.isChecked());
                intent.putExtra(Util.PHY,phyCb.isChecked());
                intent.putExtra(Util.CHE,cheCb.isChecked());
                intent.putExtra(Util.ENG,engCb.isChecked());
                intent.putExtra(Util.TAMIL,tamCb.isChecked());
                setResult(Util.RES_CODE,intent);
                finish();

            }
        });

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            index = bundle.getInt(Util.INDEX);
            if (index == Util.NEW_ENTRY){
                actionBar.setTitle("Add Student Record");
            }else{
                actionBar.setTitle("Edit Student Record");
            }
        }


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
