package enig.dhiya_oueslati.atelier_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditEtudiant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_etudiant);
        RelativeLayout relativeLayout = findViewById(R.id.edit_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextInputEditText prenom = findViewById(R.id.prenom);
        TextInputEditText nom = findViewById(R.id.nom);
        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText groupe = findViewById(R.id.groupe);
        MaterialButton save = findViewById(R.id.save);
        MaterialButton delete = findViewById(R.id.delete);

        prenom.setText(App.etudiant.getprenom());
        nom.setText(App.etudiant.getnom());
        email.setText(App.etudiant.getemail());
        groupe.setText(App.etudiant.getgroupe());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("etudiants").document(App.etudiant.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditEtudiant.this, "Etudiant supprimé", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditEtudiant.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> etudiant = new HashMap<>();
                etudiant.put("prénom", Objects.requireNonNull(prenom.getText()).toString());
                etudiant.put("nom", Objects.requireNonNull(nom.getText()).toString());
                etudiant.put("email", Objects.requireNonNull(email.getText()).toString());
                etudiant.put("groupe", Objects.requireNonNull(groupe.getText()).toString());

                db.collection("etudiants").document(App.etudiant.getId()).set(etudiant).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditEtudiant.this, "Etudiant enregistré", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditEtudiant.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}