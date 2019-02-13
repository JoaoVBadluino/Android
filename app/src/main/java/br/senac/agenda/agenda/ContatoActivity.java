package br.senac.agenda.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class ContatoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        //-------------
        //acao de click de salvar
        //-------------

        //recuperar o botao de salvar
        Button salvarContatoButton = findViewById(R.id.salvarContatoButton);

        salvarContatoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Recuperar o texto dos editText
                //Objetos de tela
                EditText nomeEditText = findViewById(R.id.nomeEditText);
                EditText telefoneEditText = findViewById(R.id.telefoneEditText);
                EditText emailEditText = findViewById(R.id.emailEditText);

                //Potuacao rating
                RatingBar pontuacaoRatingBar = findViewById(R.id.pontuacaoRatingBar);

                //Texto que estava presentes nos objetos
                String nome = nomeEditText.getText().toString();
                String telefone = telefoneEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String pontuacao = String.valueOf(pontuacaoRatingBar.getRating());

                //exibe uma mensagem
                Toast.makeText(ContatoActivity.this,"Contato Salvo! //// Nome: " + nome + " - Tel: "+telefone+" - Email: " +email +"Pontuação: "+pontuacao ,Toast.LENGTH_LONG
                ).show();

                //finalizar a activity atual e voltar para a MainActivity
                finish();
            }
        });
    }
}
