package br.senac.agenda.agenda.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import br.senac.agenda.agenda.model.EnderecoEntity;

public class EnderecoDAO {
    private SQLiteHelper sqLiteHelper;
    private SQLiteDatabase sqLiteDatabase;

    public EnderecoDAO(Context context) {
        this.sqLiteHelper = new SQLiteHelper(context);
    }


    public void salvar(EnderecoEntity endereco) {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("RUA", endereco.getRua());
        values.put("CIDADE", endereco.getCidade());
        values.put("NUMERO", endereco.getNumero());

        sqLiteDatabase.insert("ENDERECO", null, values);

        sqLiteDatabase.close();
    }

    public List<EnderecoEntity> listou() {
        sqLiteDatabase = sqLiteHelper.getReadableDatabase();

        String sql = "SELECT * FROM ENDERECO;";

        Cursor e = sqLiteDatabase.rawQuery(sql, null);

        List<EnderecoEntity> enderecos = new ArrayList<>();

        while (e.moveToNext()) {
            EnderecoEntity endereco = new EnderecoEntity();
            endereco.setId(e.getInt(e.getColumnIndex("ID")));
            endereco.setCidade(e.getString(e.getColumnIndex("CIDADE")));
            endereco.setNumero(e.getString(e.getColumnIndex("NUMERO")));
            endereco.setRua(e.getString(e.getColumnIndex("RUA")));

            enderecos.add(endereco);
        }
        return enderecos;
    }
}

