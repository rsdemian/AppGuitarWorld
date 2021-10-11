package com.example.appguitarworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    private ListView lvEquipamentos;
    private ArrayAdapter adapter;
    private List<Equipamento> listaDeEquipamentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        lvEquipamentos = findViewById(R.id.lvEquipamentos);

        CarregaEquipamentos();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,FormularioActivity.class);
                intent.putExtra("acao","inserir");
                startActivity(intent);
            }
        });

        lvEquipamentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int idEquipamento = listaDeEquipamentos.get(i).getId();
                Intent intent = new Intent(MainActivity.this,FormularioActivity.class);
                intent.putExtra("acao","editar");
                intent.putExtra("idEquipamento",idEquipamento);
                startActivity(intent);
            }
        });

        lvEquipamentos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Excluir(i);
                return true;
            }
        });

    }

    private void Excluir(int posicao){
        Equipamento equip = listaDeEquipamentos.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir...");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Confirma a exclusão do equipamento " + equip.getNome() + "?");
        alerta.setNeutralButton("Cancelar",null);

        alerta.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                EquipamentoDAO.Excluir(MainActivity.this,equip.getId());
                //Atualizar a lista
                CarregaEquipamentos();
            }
        });
        alerta.show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        CarregaEquipamentos();
    }


    private void CarregaEquipamentos(){
        listaDeEquipamentos = EquipamentoDAO.BuscaEquipamentos(this);// Passando o contexto ou seja,a  tela
        if(listaDeEquipamentos.size() == 0){
            Equipamento vazio  = new Equipamento("Lista Vazia","","","");
            listaDeEquipamentos.add(vazio);
        }else{
            lvEquipamentos.setEnabled(true);
        }


        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaDeEquipamentos);
        lvEquipamentos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}