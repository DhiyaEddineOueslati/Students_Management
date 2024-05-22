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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AddEtudiant extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_etudiant);
        RelativeLayout relativeLayout = findViewById(R.id.add_layout);
        AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(5000);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextInputEditText prenom = findViewById(R.id.prenom);
        TextInputEditText nom = findViewById(R.id.nom);
        TextInputEditText email = findViewById(R.id.email);
        TextInputEditText groupe = findViewById(R.id.groupe);
        MaterialButton addEtudiant = findViewById(R.id.addEtudiant);

        addEtudiant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> etudiant = new HashMap<>();
                etudiant.put("prenom", Objects.requireNonNull(prenom.getText()).toString());
                etudiant.put("nom", Objects.requireNonNull(nom.getText()).toString());
                etudiant.put("email", Objects.requireNonNull(email.getText()).toString());
                etudiant.put("groupe", Objects.requireNonNull(groupe.getText()).toString());

                db.collection("etudiants").add(etudiant).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddEtudiant.this, "Etudiant ajouté", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddEtudiant.this, "error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}