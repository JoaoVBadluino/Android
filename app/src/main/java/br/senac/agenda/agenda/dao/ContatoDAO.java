package br.senac.agenda.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.senac.agenda.agenda.model.ContatoEntity;

public class ContatoDAO {

    private SQLiteHelper sqliteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public ContatoDAO(Context context) {
        this.sqliteHelper = new SQLiteHelper(context);
    }

    public void salvar(ContatoEntity contato){
        sqLiteDatabase = sqliteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("NOME", contato.getNome());
        values.put("TELEFONE", contato.getTelefone());
        values.put("PONTUACAO", contato.getPontuacao());

        sqLiteDatabase.insert("CONTATO",null ,values);

        sqLiteDatabase.close();
    }
}
