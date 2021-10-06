package ei.eseptiyadi.apbexternal.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ei.eseptiyadi.apbexternal.R;
import ei.eseptiyadi.apbexternal.model.ArticlesItem;

public class AdapterListBerita extends RecyclerView.Adapter<AdapterListBerita.MyViewHolder> {

    Context context;
    List<ArticlesItem> articlesItems;

    public AdapterListBerita(Context context, List<ArticlesItem> articlesItems) {
        this.context = context;
        this.articlesItems = articlesItems;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.txJudul.setText(articlesItems.get(position).getTitle());
        holder.txSumberBerita.setText(articlesItems.get(position).getSource().getName());

        String LINK_IMAGE = articlesItems.get(position).getUrlToImage();

        Picasso.get().load(LINK_IMAGE).into(holder.ivPictureBerita);
    }

    @Override
    public int getItemCount() {
        return articlesItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivPictureBerita;
        TextView txJudul, txSumberBerita;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivPictureBerita = (ImageView)itemView.findViewById(R.id.imvBerita);
            txJudul = (TextView)itemView.findViewById(R.id.txtJudul);
            txSumberBerita = (TextView)itemView.findViewById(R.id.txtSumberBerita);
        }
    }
}
