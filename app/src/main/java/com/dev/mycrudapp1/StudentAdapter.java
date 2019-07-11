package com.dev.mycrudapp1;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {
    private ArrayList<Student> students;
    private LayoutInflater layoutInflater;
    public StudentAdapter(Context context, ArrayList<Student> mStudents){
        layoutInflater = LayoutInflater.from(context);
        students = mStudents;
    }


    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int i) {
        return students.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if (view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.student_item,viewGroup,false);
            viewHolder.nameTx = (TextView) view.findViewById(R.id.firstNameTx);
            viewHolder.lastNameTx = (TextView) view.findViewById(R.id.lastNameTx);
            viewHolder.rollNumTx = (TextView) view.findViewById(R.id.rollNumTx);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Log.w(Util.INDEX,"index loaded: "+i);
        viewHolder.nameTx.setText(students.get(i).getName());
        viewHolder.lastNameTx.setText(students.get(i).getLastName());
        viewHolder.rollNumTx.setText(students.get(i).getRollNum());


        return view;
    }

    private class ViewHolder{
        TextView nameTx;
        TextView lastNameTx;
        TextView rollNumTx;
    }
}
