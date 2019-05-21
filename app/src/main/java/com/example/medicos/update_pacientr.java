package com.example.medicos;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class update_pacientr extends AppCompatActivity {
    private TextView novoPaciente;
    private TextView nome;
    private TextView idade;
    private TextView leococitos;
    private TextView glicemia;
    private TextView ast;
    private TextView ldh;
    private TextView pontuacao;
    private TextView mortalidade;
    private EditText campoNome;
    private EditText campoIdade;
    private EditText campoLeococitos;
    private EditText campoGlicemia;
    private EditText campoAst;
    private EditText campoLdh;
    private CheckBox litiaseBiliar;
    private DAL dal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pacientr);

        Button adc = findViewById(R.id.button2);
        Button voltar = findViewById(R.id.button);
        litiaseBiliar = findViewById(R.id.checkBox);
        campoNome = findViewById(R.id.editText);
        campoIdade = findViewById(R.id.editText2);
        campoLeococitos = findViewById(R.id.editText4);
        campoGlicemia = findViewById(R.id.editText8);
        campoAst = findViewById(R.id.editText10);
        campoLdh = findViewById(R.id.editText12);
        pontuacao = findViewById(R.id.textView18);
        mortalidade = findViewById(R.id.textView19);
        pontuacao.setText("");
        mortalidade.setText("");
        Intent intent = getIntent();

        final int id = intent.getIntExtra(CreateDatabase.ID, 0);

        dal = new DAL(this);


        Cursor cursor = dal.findById(id);

        //campo.setText(String.valueOf(id));
        campoNome.setText(cursor.getString(cursor.getColumnIndex(CreateDatabase.NOME)));
        campoIdade.setText(cursor.getString(cursor.getColumnIndex(CreateDatabase.IDADE)));
        campoLeococitos.setText(cursor.getString(cursor.getColumnIndex(CreateDatabase.LEOCOCITOS)));
        campoGlicemia.setText(cursor.getString(cursor.getColumnIndex(CreateDatabase.GLICEMIA)));
        campoAst.setText(cursor.getString(cursor.getColumnIndex(CreateDatabase.AST)));
        campoLdh.setText(cursor.getString(cursor.getColumnIndex(CreateDatabase.LDH)));

        /////


        View.OnClickListener trocaTela = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(update_pacientr.this, MainActivity.class);
                startActivity(it);
            }
        };
        voltar.setOnClickListener(trocaTela);


        /////
        adc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DAL dal = new DAL(update_pacientr.this);
                String nome = campoNome.getText().toString();
                Double idade = Double.valueOf(campoIdade.getText().toString());
                Double leococitos = Double.valueOf(campoLeococitos.getText().toString());
                Double glicemia = Double.valueOf(campoGlicemia.getText().toString());
                Double ast = Double.valueOf(campoAst.getText().toString());
                Double ldh = Double.valueOf(campoLdh.getText().toString());
                int qtd = 0;
                int mor = 0;




                if(litiaseBiliar.isChecked()){
                    if(idade>70){
                        qtd++;
                    }
                    if(leococitos>18000){
                        qtd++;
                    }
                    if(glicemia>12.2){
                        qtd++;
                    }
                    if(ast>250){
                        qtd++;
                    }
                    if(ldh>400){
                        qtd++;
                    }
                }else{
                    if(idade>55){
                        qtd++;
                    }
                    if(leococitos>16000){
                        qtd++;
                    }
                    if(glicemia>11){
                        qtd++;
                    }
                    if(ast>250){
                        qtd++;
                    }
                    if(ldh>350){
                        qtd++;
                    }

                }
                ////





                if(qtd<=2){
                    mor = 2;
                }else if(qtd>=3 && qtd<=4){
                    mor =  15;
                }else if(qtd==5){
                    mor = 40;
                }

                pontuacao.setText("Pontuação:" + qtd);
                mortalidade.setText("Mortalidade: " + mor+"%");
                if (dal.update(id,nome, idade, leococitos,glicemia, ast,ldh, (double)mor)) {
                    Toast.makeText(update_pacientr.this,
                            "Registro atualizado com sucesso!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(update_pacientr.this,
                            "Erro ao atualizar registro!", Toast.LENGTH_LONG).show();
                }
            }

        });

    }
    }

