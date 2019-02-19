package br.senac.agenda.agenda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import br.senac.agenda.agenda.R;
import br.senac.agenda.agenda.dao.ContatoDAO;
import br.senac.agenda.agenda.model.ContatoEntity;
import br.senac.agenda.agenda.model.EnderecoEntity;

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
                EditText ruaEditText = findViewById(R.id.ruaEditText);
                EditText numeroEditText = findViewById(R.id.numeroEditText);
                EditText cidadeEditText = findViewById(R.id.cidadeEditText);

                //Potuacao rating
                RatingBar pontuacaoRatingBar = findViewById(R.id.pontuacaoRatingBar);

                //Texto que estava presentes nos objetos

                //nome e telefone do contato
                ContatoEntity contato = new ContatoEntity(nomeEditText.getText().toString(),
                        telefoneEditText.getText().toString(),
                        Double.valueOf(pontuacaoRatingBar.getProgress()));

                ContatoDAO contatoDAO = new ContatoDAO(ContatoActivity.this);
                contatoDAO.salvar(contato);

                //endereço do contato
                EnderecoEntity endereco = new EnderecoEntity(ruaEditText.getText().toString(),
                        numeroEditText.getText().toString(),
                        cidadeEditText.getText().toString());


                //exibe uma mensagem
                Toast.makeText(ContatoActivity.this,"Contato Salvo!" ,Toast.LENGTH_LONG
                ).show();

                //finalizar a activity atual e voltar para a MainActivity
                Intent main = new Intent (ContatoActivity.this, MainActivity.class);
                startActivity(main);
                finish();
            }
        });
    }
}


