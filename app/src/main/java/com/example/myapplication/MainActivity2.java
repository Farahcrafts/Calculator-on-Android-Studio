package com.example.myapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;
    EditText editText;
    LinearLayout linearLayout;
    
    // Variables pour la logique
    private double firstValue = Double.NaN;
    private String currentOperation = null;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##########"); // Pour enlever les .0 inutiles

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculatrice);
        
        // Gestion des insets (barres système)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
    }

    // Méthode pour initialiser toutes les vues et les écouteurs
    private void initViews() {
        linearLayout = findViewById(R.id.main);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        // --- Initialisation des chiffres (0-9) ---
        // Une boucle ou des appels groupés rendent le code plus propre
        int[] numberIds = {
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, 
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        };

        View.OnClickListener numberListener = v -> {
            Button b = (Button) v;
            String currentText = editText.getText().toString();
            
            // Si on affiche "0" ou "Error", on remplace, sinon on ajoute
            if (currentText.equals("0") || currentText.equals("Error") || currentText.equals("Infinity")) {
                editText.setText(b.getText().toString());
            } else {
                editText.append(b.getText().toString());
            }
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(numberListener);
        }

        // --- Initialisation des opérations (+, -, *, /, %) ---
        int[] opIds = {
            R.id.addition, R.id.soustraction, R.id.multiplication, 
            R.id.division, R.id.modulo
        };

        View.OnClickListener opListener = v -> {
            Button b = (Button) v;
            computeSelection(b.getText().toString());
        };

        for (int id : opIds) {
            findViewById(id).setOnClickListener(opListener);
        }

        // --- Autres boutons ---
        
        // Virgule
        findViewById(R.id.virgule).setOnClickListener(v -> {
            String currentText = editText.getText().toString();
            if (!currentText.contains(".")) {
                editText.append(".");
            }
        });

        // Delete (Effacer un chiffre)
        findViewById(R.id.delete).setOnClickListener(v -> {
            String currentText = editText.getText().toString();
            if (currentText.length() > 1) {
                editText.setText(currentText.substring(0, currentText.length() - 1));
            } else {
                editText.setText("0");
            }
        });

        // AC (Tout effacer)
        findViewById(R.id.AC).setOnClickListener(v -> {
            firstValue = Double.NaN;
            currentOperation = null;
            editText.setText("0");
            textView.setText("");
        });

        // C (Effacer l'entrée courante)
        findViewById(R.id.C).setOnClickListener(v -> editText.setText("0"));

        // Égal (=)
        findViewById(R.id.equal).setOnClickListener(v -> computeResult());
    }

    private void computeSelection(String operation) {
        if (!Double.isNaN(firstValue)) {
            // Si on enchaine les opérations sans faire =, on calcule l'intermédiaire
            computeResult();
        }
        
        try {
            firstValue = Double.parseDouble(editText.getText().toString());
            currentOperation = operation;
            textView.setText(decimalFormat.format(firstValue) + " " + operation);
            editText.setText("0");
        } catch (NumberFormatException e) {
            editText.setText("Error");
        }
    }

    private void computeResult() {
        if (Double.isNaN(firstValue) || currentOperation == null) return;

        double secondValue;
        try {
            secondValue = Double.parseDouble(editText.getText().toString());
        } catch (NumberFormatException e) {
            return;
        }

        textView.setText(decimalFormat.format(firstValue) + " " + currentOperation + " " + decimalFormat.format(secondValue));

        double result = 0;
        boolean error = false;

        switch (currentOperation) {
            case "+": result = firstValue + secondValue; break;
            case "-": result = firstValue - secondValue; break;
            case "*": result = firstValue * secondValue; break;
            case "%": result = firstValue % secondValue; break;
            case "/":
                if (secondValue == 0) {
                    error = true;
                } else {
                    result = firstValue / secondValue;
                }
                break;
        }

        if (error) {
            editText.setText("Error");
        } else {
            editText.setText(decimalFormat.format(result));
            // On stocke le résultat comme première valeur pour la suite des calculs
            firstValue = Double.NaN; 
            currentOperation = null;
        }
    }

  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.standard) {
            Snackbar.make(linearLayout, "Calculatrice scientifique choisie", Snackbar.LENGTH_LONG)
                    .setAction("Annuler", v -> 
                        Snackbar.make(linearLayout, "Operation annulee", Snackbar.LENGTH_LONG)
                                .setBackgroundTint(Color.WHITE)
                                .setTextColor(Color.GRAY)
                                .show()
                    )
                    .setBackgroundTint(Color.BLACK)
                    .setTextColor(Color.WHITE)
                    .show();
        }
        return super.onOptionsItemSelected(item);
    }
}
