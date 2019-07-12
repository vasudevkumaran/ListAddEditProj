package com.dev.mycrudapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SudentRecyclerAdapter extends RecyclerView.Adapter<SudentRecyclerAdapter.StudentViewHolder>{

    private ArrayList<Student> students = new ArrayList<Student>();
    private LayoutInflater layoutInflater;
    private View.OnClickListener mOnClickListener;

    public SudentRecyclerAdapter(Context context, ArrayList<Student> mStudents, View.OnClickListener onClickListener){
        students = mStudents;
        layoutInflater = LayoutInflater.from(context);
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = (View)layoutInflater.inflate(R.layout.student_item,parent,false);
        return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
        holder.nameTx.setText(students.get(position).getName());
        holder.lastNameTx.setText(students.get(position).getLastName());
        holder.rollNumTx.setText(students.get(position).getRollNum());
        holder.nameTx.setTag(R.string.index,position);
        holder.lastNameTx.setTag(R.string.index,position);
        holder.rollNumTx.setTag(R.string.index,position);
        holder.mVtemView.setTag(R.string.index,position);
        holder.mVtemView.setClickable(true);
        //holder.mVtemView.setTag(R.string.studentObj,students.get(position));
        //holder.nameTx.setOnClickListener(mOnClickListener);
        //holder.lastNameTx.setOnClickListener(mOnClickListener);
        //holder.rollNumTx.setOnClickListener(mOnClickListener);
        holder.mVtemView.setOnClickListener(mOnClickListener);

    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public  class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView nameTx;
        TextView lastNameTx;
        TextView rollNumTx;
        View mVtemView;
        public StudentViewHolder(View itemView) {
            super(itemView);
            mVtemView = itemView;
            nameTx = (TextView)itemView.findViewById(R.id.firstNameTx);
            lastNameTx = (TextView)itemView.findViewById(R.id.lastNameTx);
            rollNumTx = (TextView)itemView.findViewById(R.id.rollNumTx);
        }
    }





}
