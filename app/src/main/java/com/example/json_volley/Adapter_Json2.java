package com.example.json_volley;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_Json2 extends RecyclerView.Adapter<Adapter_Json2.charViewHold> {
    private ArrayList<Char_> datalist;

    public Adapter_Json2(ArrayList<Char_> datalist){
        this.datalist=datalist;
    }

    @Override
    public Adapter_Json2.charViewHold onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.obj_list,parent,false);
        return new Adapter_Json2.charViewHold(view);
    }

    @Override
    public void onBindViewHolder(Adapter_Json2.charViewHold holder, int position){
        holder.txtName.setText(datalist.get(position).getName());
        holder.txtId.setText(datalist.get(position).getId());
        holder.txtnohp.setText(datalist.get(position).getUsername());
        holder.txtEmail.setText(datalist.get(position).getEmail());
        holder.txtAddress.setText(datalist.get(position).getAddress());


    }

    @Override
    public int getItemCount() {
        return (datalist != null) ? datalist.size() : 0;

    }

    public class charViewHold extends RecyclerView.ViewHolder {
        private TextView txtName, txtId,txtnohp, txtEmail,txtAddress;

        public charViewHold(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.textNama);
            txtId = (TextView) itemView.findViewById(R.id.textIds);
            txtnohp = (TextView) itemView.findViewById(R.id.textNamaUser);
            txtEmail = (TextView) itemView.findViewById(R.id.textemail);
            txtAddress = (TextView)itemView.findViewById(R.id.textAlamat);
        }
    }
}
