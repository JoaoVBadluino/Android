package br.senac.agenda.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera a referencia da lista que tem no layout do aplicativo
        ListView lista = findViewById(R.id.listaContatos);

        //Cria a lista de contatos como string
        String[] contatos = {"André","Ari","Roberto","Luzia" };

        //para conseguirmos  exibir a lista de listview, preciso cirar um adaptador
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contatos);

        //insere o adaptador na lista de contatos
        lista.setAdapter(adapter);
    }
}
