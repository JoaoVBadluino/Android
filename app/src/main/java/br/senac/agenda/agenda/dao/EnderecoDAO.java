package br.senac.agenda.agenda.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.senac.agenda.agenda.model.ContatoEntity;
import br.senac.agenda.agenda.model.EnderecoEntity;

public class EnderecoDAO {
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EnderecoDAO(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }

    public void salvar(EnderecoEntity contato) {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
    }
}