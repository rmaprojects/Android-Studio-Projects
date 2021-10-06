package project.raka.m.a.portalberitaext.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import project.raka.m.a.portalberitaext.R;

public class DetailActivity extends AppCompatActivity {
    ImageView imageBerita;
    String linkImage;
    TextView Judul, Sumber, Penulis, Isi, Deskripsi, Tanggal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        imageBerita = (ImageView)findViewById(R.id.imageDetail);
        Judul = (TextView)findViewById(R.id.judulDetail);
        Sumber = (TextView)findViewById(R.id.sumberDetail);
        Penulis = (TextView)findViewById(R.id.penulisDetail);
        Isi = (TextView)findViewById(R.id.isiBerita);
        Deskripsi = (TextView)findViewById(R.id.DeskrpsiBerita);
        Tanggal = (TextView)findViewById(R.id.publikasiBerita);

        Bundle pull = getIntent().getExtras();
        Judul.setText(pull.getString("JUDUL"));
        Sumber.setText(pull.getString("SUMBER"));
        Penulis.setText(pull.getString("PENULIS"));
        Isi.setText(pull.getString("ISI"));
        Deskripsi.setText(pull.getString("DESKRIPSI"));
        Tanggal.setText(pull.getString("TANGGAL"));
        linkImage = pull.getString("IMAGE");
        Picasso.get().load(linkImage).into(imageBerita);

    }
}