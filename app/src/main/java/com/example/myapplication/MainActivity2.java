package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {


    TextView textView;
    EditText editText;
    Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9;
    Button AC,C,delete;
    Button division,modulo,multiplication,soustraction,addition,virgule;
    Button equal;
    boolean operationEnCours;
    float result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculatrice);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Associations de editText et textView
        editText=findViewById(R.id.editText);
        textView=findViewById(R.id.textView);

        //Associations des chiffres
        btn0=findViewById(R.id.btn0);
        btn1=findViewById(R.id.btn1);
        btn2=findViewById(R.id.btn2);
        btn3=findViewById(R.id.btn3);
        btn4=findViewById(R.id.btn4);
        btn5=findViewById(R.id.btn5);
        btn6=findViewById(R.id.btn6);
        btn7=findViewById(R.id.btn7);
        btn8=findViewById(R.id.btn8);
        btn9=findViewById(R.id.btn9);

        //associations des operations
        addition=findViewById(R.id.addition);
        soustraction=findViewById(R.id.soustraction);
        multiplication=findViewById(R.id.multiplication);
        division=findViewById(R.id.division);
        modulo=findViewById(R.id.modulo);
        equal=findViewById(R.id.equal);

        //associations des autres boutons
        AC=findViewById(R.id.AC);
        C=findViewById(R.id.C);
        delete=findViewById(R.id.delete);
        virgule=findViewById(R.id.virgule);

        //operation en cours
        operationEnCours=false;

        //initialisation de result
        result=0;

        //virgule clique
        virgule.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (!currentEditText.contains(".")){
                    editText.setText(currentEditText+".");
                } else {
                    editText.setText("Error");
                }
            }
        });

        //btn1 clique
        btn1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("1");
                } else {
                    editText.setText(currentEditText + "1");
                }
            }
        });

        //btn2 clique
        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("2");
                } else {
                    editText.setText(currentEditText + "2");
                }
            }
        });

        //btn3 clique
        btn3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("3");
                } else {
                    editText.setText(currentEditText + "3");
                }
            }
        });

        //btn4 clique
        btn4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("4");
                } else {
                    editText.setText(currentEditText + "4");
                }
            }
        });

        //btn5 clique
        btn5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("5");
                } else {
                    editText.setText(currentEditText + "5");
                }
            }
        });

        //btn3 clique
        btn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("6");
                } else {
                    editText.setText(currentEditText + "6");
                }
            }
        });

        //btn clique
        btn7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("7");
                } else {
                    editText.setText(currentEditText + "7");
                }
            }
        });

        //btn8 clique
        btn8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("8");
                } else {
                    editText.setText(currentEditText + "8");
                }
            }
        });

        //btn9 clique
        btn9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("9");
                } else {
                    editText.setText(currentEditText + "9");
                }
            }
        });

        //btn0 clique
        btn0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String currentEditText=editText.getText().toString();
                if (currentEditText.equals("0")) {
                    editText.setText("0");
                } else {
                    editText.setText(currentEditText + "0");
                }
            }
        });

        // boutton plus clique
        addition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operationEnCours){
                    textView.setText(editText.getText().toString()+ "+");
                    editText.setText("0");
                    operationEnCours=true;
                }else {
                    editText.setText("Error");
                }
            }
        });

        // boutton moins clique
        soustraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operationEnCours){
                    textView.setText(editText.getText().toString()+ "-");
                    editText.setText("0");
                    operationEnCours=true;
                }else {
                    editText.setText("Error");
                }
            }
        });

        //boutton multiplication clique
        multiplication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operationEnCours){
                    textView.setText(editText.getText().toString()+ "*");
                    editText.setText("0");
                    operationEnCours=true;
                }else {
                    editText.setText("Error");
                }
            }
        });

        // boutton division clique
        division.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operationEnCours){
                    textView.setText(editText.getText().toString()+ "/");
                    editText.setText("0");
                    operationEnCours=true;
                }else {
                    editText.setText("Error");
                }
            }
        });

        // boutton modulo clique
        modulo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!operationEnCours){
                    textView.setText(editText.getText().toString()+ "%");
                    editText.setText("0");
                    operationEnCours=true;
                }else {
                    editText.setText("Error");
                }
            }
        });

        //bouton equal clique
        equal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String tv=textView.getText().toString();
                String et=editText.getText().toString();
                float number1=Float.parseFloat(tv.substring(0,tv.length()-1));
                float number2=Float.parseFloat(et.trim());
                String operation=tv.substring(tv.length()-1).trim();

                textView.setText(tv + et);

                switch (operation){
                    case "+": result=number1+number2;
                    break;
                    case "-": result=number1-number2;
                        break;
                    case "*": result=number1*number2;
                        break;
                    case "/": result=number1/number2;
                        break;
                    case "%": result=number1%number2;
                    break;

                }
                //show result
                editText.setText(String.valueOf(result));
                operationEnCours=false;

            }
        });

        //bouton delete
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentNumber=editText.getText().toString();
                editText.setText(currentNumber.substring(0,currentNumber.length()-1));
            }
        });

        //bouton AC et C
        AC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("0");
                textView.setText("0");
            }
        });

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("0");
            }
        });


        }
    }
