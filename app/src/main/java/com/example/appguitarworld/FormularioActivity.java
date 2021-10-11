package com.example.appguitarworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class FormularioActivity extends AppCompatActivity {

    private EditText etNome;
    private EditText etMarca;
    private EditText etValor;
    private Spinner spCategorias;
    private Button btnSalvar;
    private String acao;
    private Equipamento equipamento;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNome = findViewById(R.id.etNome);
        etMarca = findViewById(R.id.etMarca);
        etValor = findViewById(R.id.etValor);
        spCategorias = findViewById(R.id.spCategoria);
        btnSalvar = findViewById(R.id.btnSalvar);

        acao = getIntent().getStringExtra("acao");
        if(acao.equals("editar")){
            //chamar CarregarFormulario
            CarregarFormulario();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Salvar();
            }
        });
    }

    private void CarregarFormulario(){
        int id = getIntent().getIntExtra("idEquipamento", 0);
        equipamento = EquipamentoDAO.BuscaApenasUmEquipamento(this, id);
        etNome.setText( equipamento.getNome());
        etMarca.setText( equipamento.getMarca());
        etValor.setText(equipamento.getValor());
        //double value = equipamento.getValor();
        //String strValue = String.format("%.2f", value );
        //etValor.setText(strValue);
        String[] categorias = getResources().getStringArray(R.array.categorias);
        for (int i = 1; i < categorias.length ;i++){
            if( equipamento.getCategoria().equals( categorias[i] ) ){
                spCategorias.setSelection(i);
                break;
            }
        }
    }

    private void Salvar(){
        String nome = etNome.getText().toString();
        String marca = etMarca.getText().toString();
        String valor = etValor.getText().toString();
        if(nome.isEmpty() || marca.isEmpty() || valor.isEmpty() || spCategorias.getSelectedItemPosition() == 0){ // Se está vazia no campo nome
            //retornar MSG na tela
            Toast.makeText(this,"Preencha todos os campos!",Toast.LENGTH_LONG).show();
        }else{
            if(acao.equals("inserir")){
                equipamento = new Equipamento();
            }

            equipamento.setNome(nome);
            equipamento.setMarca(marca);
            equipamento.setValor(valor);
            equipamento.setCategoria(spCategorias.getSelectedItem().toString());

            if(acao.equals("inserir")) {
                EquipamentoDAO.Inserir(this, equipamento);
                etNome.setText("");
                etMarca.setText("");
                etValor.setText("");
                spCategorias.setSelection(0, true);
                Toast.makeText(this,"Equipamento cadastrado com sucesso!",Toast.LENGTH_LONG).show();
            }else{
                EquipamentoDAO.Editar(this,equipamento);
                finish();
            }
        }
    }

}