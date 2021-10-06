package project.raka.m.a.notepad.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import project.raka.m.a.notepad.R;
import project.raka.m.a.notepad.adapter.AdapterDashboard;
import project.raka.m.a.notepad.model.listDataNote.ListnotesItem;
import project.raka.m.a.notepad.model.listDataNote.ResponseListNotes;
import project.raka.m.a.notepad.network.APIServices;
import project.raka.m.a.notepad.network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboardActivity extends AppCompatActivity {

    RecyclerView rvListNote, rvListTask, rvListToDo;
    SwipeRefreshLayout srflistnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        rvListToDo = (RecyclerView)findViewById(R.id.rv_listTodo);
        rvListToDo.setHasFixedSize(true);
        rvListToDo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListNote = (RecyclerView)findViewById(R.id.rv_listnote);
        rvListNote.setHasFixedSize(true);
        rvListNote.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvListTask = (RecyclerView)findViewById(R.id.rv_listtask);
        rvListTask.setHasFixedSize(true);
        rvListTask.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        String username = "Raka M.A";
        String PW = "12345678";
        String hash_useraccess = "administrator";
        String lvl = "Administrator";

        srflistnote = (SwipeRefreshLayout)findViewById(R.id.srlLoadList);
        //Meload data ketika launch pertama App
        updateList(username, PW, hash_useraccess, lvl);


        srflistnote.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Meload ketika Wipe refresh dilakukan
                updateList(username, PW, hash_useraccess, lvl);
            }
        });

        loadDataNotes(username, PW, hash_useraccess, lvl, "Notes");
        loadDataTask(username, PW, hash_useraccess, lvl, "Task");
        loadDataToDo(username, PW, hash_useraccess, lvl, "Todo");

    }

    private void updateList(String username, String pw, String hash_useraccess, String lvl) {
        loadDataNotes(username, pw, hash_useraccess, lvl, "Notes");
        loadDataTask(username, pw, hash_useraccess, lvl, "Task");
        loadDataToDo(username, pw, hash_useraccess, lvl, "Todo");
    }

    private void loadDataToDo(String username, String pw, String hash_useraccess, String lvl, String to_do) {
        TextView txtViewToDo, viewToDo;
        txtViewToDo = (TextView)findViewById(R.id.totalTodo);
        viewToDo = (TextView)findViewById(R.id.kategoriTask);

        APIServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListNotes> callRespondListNote = apiServices.requestListNotes(username, pw, hash_useraccess, lvl, to_do);

        callRespondListNote.enqueue(new Callback<ResponseListNotes>() {
            //Callback = Ambil terus request
            @Override
            public void onResponse(Call<ResponseListNotes> call, Response<ResponseListNotes> response) {
                if (response.isSuccessful()){
                    TextView todo404 = (TextView)findViewById(R.id.todoNotFound);
                    boolean status = response.body().isStatus();
                    List<ListnotesItem> listnotesItems = response.body().getListnotes();
                    AdapterDashboard adapterDashboard = new AdapterDashboard(dashboardActivity.this, listnotesItems);
                    int totalTodo = Integer.parseInt(response.body().getTotalnotes().toString());
                    if (totalTodo == 0){
                        srflistnote.setRefreshing(false);
                        todo404.setVisibility(View.VISIBLE);
                        txtViewToDo.setText(response.body().getTotalnotes());
                        rvListToDo.setVisibility(View.GONE);
                    } else if (totalTodo > 0){
                        todo404.setVisibility(View.GONE);
                        srflistnote.setRefreshing(false);
                        txtViewToDo.setText(("Total Data: " + totalTodo));
                        rvListToDo.setAdapter(adapterDashboard);
                    }
                } else{
                    Toast.makeText(dashboardActivity.this, "G bisa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListNotes> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void loadDataTask(String username, String pw, String hash_useraccess, String lvl, String task) {
        TextView txtViewTask, viewTask;
        txtViewTask = (TextView)findViewById(R.id.totaltask);
        viewTask = (TextView)findViewById(R.id.kategoriTask);

        APIServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListNotes> callRespondListNote = apiServices.requestListNotes(username, pw, hash_useraccess, lvl, task);

        callRespondListNote.enqueue(new Callback<ResponseListNotes>() {
            //Callback = Ambil terus request
            @Override
            public void onResponse(Call<ResponseListNotes> call, Response<ResponseListNotes> response) {
                if (response.isSuccessful()){
                    TextView task404 = (TextView)findViewById(R.id.taskNoteFound);
                    boolean status = response.body().isStatus();
                    List<ListnotesItem> listnotesItems = response.body().getListnotes();
                    AdapterDashboard adapterDashboard = new AdapterDashboard(dashboardActivity.this, listnotesItems);
                    int totalTask = Integer.parseInt(response.body().getTotalnotes().toString());
                    if (totalTask == 0){
                        srflistnote.setRefreshing(false);
                        task404.setVisibility(View.VISIBLE);
                        txtViewTask.setText(response.body().getTotalnotes());
                        rvListTask.setVisibility(View.GONE);
                    } else if (totalTask > 0){
                        task404.setVisibility(View.GONE);
                        srflistnote.setRefreshing(false);
                        txtViewTask.setText(("Total Data: " + totalTask));
                        rvListTask.setAdapter(adapterDashboard);
                    }

                } else{
                    Toast.makeText(dashboardActivity.this, "G bisa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListNotes> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    private void loadDataNotes(String username, String pw, String hash_useraccess, String lvl, String category) {
        TextView txtViewNotes, viewNotes;
        txtViewNotes = (TextView)findViewById(R.id.totalNotes);
        viewNotes = (TextView)findViewById(R.id.kategoryNote);

        APIServices apiServices = RetrofitClient.getInstance();
        Call<ResponseListNotes> callRespondListNote = apiServices.requestListNotes(username, pw, hash_useraccess, lvl, category);

        callRespondListNote.enqueue(new Callback<ResponseListNotes>() {
            //Callback = Ambil terus request
            @Override
            public void onResponse(Call<ResponseListNotes> call, Response<ResponseListNotes> response) {
                if (response.isSuccessful()){
                    TextView notes404 = (TextView)findViewById(R.id.notesNotfound);
                    boolean status = response.body().isStatus();
                    List<ListnotesItem> listnotesItems = response.body().getListnotes();
                    AdapterDashboard adapterDashboard = new AdapterDashboard(dashboardActivity.this, listnotesItems);
                    int totalNotes = Integer.parseInt(response.body().getTotalnotes().toString());
                    if (totalNotes == 0){
                        srflistnote.setRefreshing(false);
                        notes404.setVisibility(View.VISIBLE);
                        txtViewNotes.setText(response.body().getTotalnotes());
                        rvListNote.setVisibility(View.GONE);
                    } else if (totalNotes > 0){
                        notes404.setVisibility(View.GONE);
                        srflistnote.setRefreshing(false);
                        txtViewNotes.setText(("Total Data: " + totalNotes));
                        rvListNote.setAdapter(adapterDashboard);
                    }
                } else{
                    Toast.makeText(dashboardActivity.this, "G bisa", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseListNotes> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }


}