package com.example.listadecompras.activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listadecompras.R;
import com.example.listadecompras.adapter.AdapterProdutos;
import com.example.listadecompras.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Variáveis visuais
    RecyclerView recyclerViewComponent;
    EditText editTextProduto;
    EditText editTextPreco;
    ImageButton btnAdd;
    ImageButton btnRemove;


    //Demais Variáveis
    AdapterProdutos adaptador;

    //Variáveis Arrays
    List<Produto> produto = new ArrayList<>();

    //Início do Código
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerViewComponent   = findViewById(R.id.recycleViewComponent);
        editTextProduto         = findViewById(R.id.editTextNome);
        btnAdd                  = findViewById(R.id.imageButtonAdd);
        btnRemove               = findViewById(R.id.imageButtonRemove);
        editTextPreco           = findViewById(R.id.editTextPreco);


        //Configurando Adapter
        adaptador = new AdapterProdutos(produto);

        //Configurando RecycleView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewComponent.setLayoutManager(layoutManager);
        recyclerViewComponent.setAdapter(adaptador);
        recyclerViewComponent.setHasFixedSize(true);
        //recyclerViewComponent.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));

    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public double converterParaDouble(String texto) {
        double valor;

        if(texto.isEmpty()) {
            return 0;
        } else if (isNumeric(texto)){
           return Double.parseDouble(texto);
        }
    return 0;
    }

    public void addProduto(View view) {
        Produto produto;
        String nomeProduto;
        String precoProduto;

        precoProduto = editTextPreco.getText().toString();
        nomeProduto  = editTextProduto.getText().toString();
        double preco = converterParaDouble(precoProduto);


        if(nomeProduto.length() != 0) {

            produto = new Produto(nomeProduto);
            produto.setPrice(preco);
            produto.setTotal(produto.addOneProduct());
            this.produto.add(produto);
            Toast.makeText(getApplicationContext(), nomeProduto + " adicionado", Toast.LENGTH_SHORT).show();
            editTextProduto.setText("");
            editTextPreco.setText("");
            adaptador = new AdapterProdutos(this.produto);

        } else {
            Toast.makeText(getApplicationContext(), "Digite o nome do Produto", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeProduto(View view) {
        String nomeProduto = editTextProduto.getText().toString();
        Produto produtoComparacao;

        for (int i =0; i < this.produto.size(); i++) {
            produtoComparacao = produto.get(i);
            if (produtoComparacao.getName().equals(nomeProduto)) {
                produto.remove(i);
                Toast.makeText(getApplicationContext(), "Produto Removido", Toast.LENGTH_SHORT).show();
                editTextProduto.setText("");
                editTextPreco.setText("");
                adaptador = new AdapterProdutos(this.produto);

            } else {
                Toast.makeText(getApplicationContext(), "Produto não existe", Toast.LENGTH_LONG).show();
            }

            //Toast.makeText(getApplicationContext(), "Ainda Não Implementado", Toast.LENGTH_SHORT).show();
        }
    }
}
