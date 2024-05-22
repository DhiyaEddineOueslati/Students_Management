package enig.dhiya_oueslati.atelier_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
SwipeRefreshLayout swiper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout relativeLayout = findViewById(R.id.main_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        
        FirebaseApp.initializeApp(MainActivity.this);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recycler);

        FloatingActionButton add = findViewById(R.id.addEtudiant);
        add.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, AddEtudiant.class)));

        db.collection("etudiants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList<Etudiant> arrayList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Etudiant etudiant = document.toObject(Etudiant.class);
                        etudiant.setId(document.getId());
                        arrayList.add(etudiant);
                    }
                    Adapter adapter = new Adapter(MainActivity.this, arrayList);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                        @Override
                        public void onClick(Etudiant etudiant) {
                            App.etudiant = etudiant;
                            startActivity(new Intent(MainActivity.this, EditEtudiant.class));
                        }
                    });
                } else {
                    Toast.makeText(MainActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        swiper = findViewById(R.id.swiper);
        swiper.setOnRefreshListener(new SwipeRefreshLayout. OnRefreshListener() {
            @Override
            public void onRefresh() {

                db.collection("etudiants").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Etudiant> arrayList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Etudiant etudiant = document.toObject(Etudiant.class);
                                etudiant.setId(document.getId());
                                arrayList.add(etudiant);
                            }
                            Adapter adapter = new Adapter(MainActivity.this, arrayList);
                            recyclerView.setAdapter(adapter);

                            adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
                                @Override
                                public void onClick(Etudiant etudiant) {
                                    App.etudiant = etudiant;
                                    startActivity(new Intent(MainActivity.this, EditEtudiant.class));
                                }
                            });
                        } else {
                            Toast.makeText(MainActivity.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                swiper.setRefreshing (false);
            }
            });
    }
}