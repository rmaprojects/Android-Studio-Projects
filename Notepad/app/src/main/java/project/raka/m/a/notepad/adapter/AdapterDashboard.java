package project.raka.m.a.notepad.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import project.raka.m.a.notepad.R;
import project.raka.m.a.notepad.model.listDataNote.ListnotesItem;

public class AdapterDashboard extends RecyclerView.Adapter<AdapterDashboard.MyviewHolder> {

    Context context;
    List<ListnotesItem> listnotesItems;
    //Constructure
    public AdapterDashboard(Context context, List<ListnotesItem> listnotesItems){
        this.context = context;
        this.listnotesItems = listnotesItems;
    }

    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_listnotes, parent, false);
        MyviewHolder holder = new MyviewHolder(view);
        return holder;
        // Berfungsi untuk looping data
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        holder.txtLabel.setText(listnotesItems.get(position).getJudul());
        holder.date.setText(listnotesItems.get(position).getTanggal() + " . " + listnotesItems.get(position).getUsername() + " . " + listnotesItems.get(position).getCategory());

        String category = listnotesItems.get(position).getCategory().toString();
        if (category.equals("Notes")){
            holder.picNotes.setImageResource(R.drawable.ic_notes);
        } else if (category.equals("Task")){
            holder.picNotes.setImageResource(R.drawable.ic_tasknote);
        } else {
            holder.picNotes.setImageResource(R.drawable.ic_baseline_check_box_24);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, listnotesItems.get(position).getJudul(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //Berfungsi sebagai penghitung jumlah data
    @Override
    public int getItemCount() {
        return listnotesItems.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        ImageView picNotes;
        TextView txtLabel, date;
        public MyviewHolder(View itemView) {
            super(itemView);
            picNotes = (ImageView)itemView.findViewById(R.id.img_notes);
            txtLabel = (TextView) itemView.findViewById(R.id.txtTittle);
            date = (TextView) itemView.findViewById(R.id.txtInformation);
        }
    }
}
