package br.senac.agenda.agenda.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.List;

import br.senac.agenda.agenda.R;
import br.senac.agenda.agenda.dao.ContatoDAO;
import br.senac.agenda.agenda.dao.EnderecoDAO;
import br.senac.agenda.agenda.model.ContatoEntity;
import br.senac.agenda.agenda.model.EnderecoEntity;

public class MainActivity extends AppCompatActivity {
    public ListView lista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Recupera a referencia da lista que tem no layout do aplicativo
        lista = findViewById(R.id.listaContatos);

        //Cria a lista de contatos como string
        final ContatoDAO contatoDAO = new ContatoDAO(this);
        EnderecoDAO enderecoDAO = new EnderecoDAO(this);
        List<ContatoEntity> contatos = contatoDAO.listar();
        List<EnderecoEntity> enderecos = enderecoDAO.listou();

        //para conseguirmos  exibir a lista de listview, preciso criar um adaptador
        ArrayAdapter<ContatoEntity> adapter = new ArrayAdapter<ContatoEntity>(this, android.R.layout.simple_list_item_1, contatos);
        ArrayAdapter<EnderecoEntity> adapter_E = new ArrayAdapter<EnderecoEntity>(this, android.R.layout.simple_list_item_1, enderecos);

        //insere o adaptador na lista de contatos
        lista.setAdapter(adapter);
        //lista.setAdapter(adapter_E);

        //Recuperar o botao e criar acao para ele
        Button novoContato = findViewById(R.id.novoContatoButton);

        novoContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent contato = new Intent(MainActivity.this, ContatoActivity.class);
                startActivity(contato);
                finish();
            }
        });

        Button buscarContato = findViewById(R.id.buscarButton);
        buscarContato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nomeEditText = findViewById(R.id.buscarEditText);

                ContatoDAO contatoDAO = new ContatoDAO(MainActivity.this);
                List<ContatoEntity> contatos = contatoDAO.listarPorNome(nomeEditText.getText().toString());

                ListView lista = findViewById(R.id.listaContatos);

                ArrayAdapter<ContatoEntity> adapter = new ArrayAdapter<ContatoEntity>(MainActivity.this, android.R.layout.simple_list_item_1,contatos);

                lista.setAdapter(adapter);
            }
        });
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ContatoEntity contato = (ContatoEntity) lista.getItemAtPosition(position);
                Intent intentcontato = new Intent(MainActivity.this, ContatoActivity.class);
                intentcontato.putExtra("C",contato);
                startActivity(intentcontato);

            }
        });
//        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "EVENTO DE CLICK LONGO!", Toast.LENGTH_LONG
//                ).show();
//                return false;
//            }
//        });
      registerForContextMenu(lista);

    }
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem remover = menu.add("REMOVER");

        remover.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AdapterView.AdapterContextMenuInfo infoAdapterView = (AdapterView.AdapterContextMenuInfo) menuInfo;

                ContatoEntity contato = (ContatoEntity) lista.getItemAtPosition(infoAdapterView.position);

                //remover o contato DÃO
                ContatoDAO contatoDAO = new ContatoDAO(MainActivity.this);
                contatoDAO.removerContato(contato);

                List<ContatoEntity> contatos = contatoDAO.listar();
                ArrayAdapter<ContatoEntity> adapter = new ArrayAdapter<ContatoEntity>(MainActivity.this, android.R.layout.simple_list_item_1, contatos);
                lista.setAdapter(adapter);


                return false;
            }
        });

    }
}
