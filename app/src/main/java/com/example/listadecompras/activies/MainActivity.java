package com.example.listadecompras.activies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.listadecompras.ClickListener;
import com.example.listadecompras.R;
import com.example.listadecompras.adapter.AdapterProdutos;
import com.example.listadecompras.model.Produto;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //Variáveis visuais
    RecyclerView recyclerViewComponent;
    EditText editTextProduto;
    EditText editTextPreco;
    ImageButton btnAdd;
    ImageButton btnRemove;
    LinearLayout layoutBtns;


    //Demais Variáveis
    AdapterProdutos adaptador;

    //Variáveis Arrays
    List<View> focusViews = new ArrayList<>();
    List<Produto> produtoList = new ArrayList<>();

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
        layoutBtns              = findViewById(R.id.linearLayout);

        fillFocusViewList();
        configurarRecycle();
        recycleViewListner();
        editPrecoListener();
    }

    public void fillFocusViewList() {
        focusViews.add(0, btnAdd);
        focusViews.add(1, editTextProduto);
        focusViews.add(2, editTextPreco);
    }

    public void recycleViewListner() {
        recyclerViewComponent.addOnItemTouchListener(
                new ClickListener(
                        getApplicationContext(),
                        recyclerViewComponent,
                        new ClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Produto produto  = produtoList.get(position);
                                Toast.makeText(getApplicationContext(),produto.getName() + " está a R$ " + produto.getPrice(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Produto produto = produtoList.get(position);
                                removeProduto(produto.getName());
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                ));
    }


    public void configurarRecycle() {
        //Configurando Adapter
        adaptador = new AdapterProdutos(produtoList);

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

        nomeProduto = captalizar(nomeProduto);
        if(nomeProduto.length() != 0) {

            produto = new Produto(nomeProduto);
            produto.setPrice(preco);
            produto.setTotal(produto.addOneProduct());
            this.produtoList.add(produto);
            configurarRecycle();
            Toast.makeText(getApplicationContext(), nomeProduto + " adicionado", Toast.LENGTH_SHORT).show();
            editTextProduto.setText("");
            focusViews.get(1).requestFocus();
            editTextPreco.setText("");

        } else {
            Toast.makeText(getApplicationContext(), "Digite o nome do Produto", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeProduto(String nomeProduto) {
        Produto produtoComparacao;

        nomeProduto = captalizar(nomeProduto);

        for (int i = 0; i < this.produtoList.size(); i++) {
            produtoComparacao = produtoList.get(i);
            System.out.println("Valor de i:" + i);
            if (produtoComparacao.getName().equals(nomeProduto)) {
                System.out.println("If: " + produtoList.size());
                editTextProduto.setText("");
                editTextPreco.setText("");
                produtoList.remove(i);
                configurarRecycle();
                snackBar(recyclerViewComponent, nomeProduto + " removido", "OK");
                //Toast.makeText(getApplicationContext(), "Produto Removido", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        System.out.println("Else: " + produtoList.size());
        Toast.makeText(getApplicationContext(), "Produto não existe", Toast.LENGTH_LONG).show();
    }


    public void removeProduto(View view) {
        String nomeProduto = editTextProduto.getText().toString();
        Produto produtoComparacao;

        nomeProduto = captalizar(nomeProduto);

        for (int i = 0; i < this.produtoList.size(); i++) {
            produtoComparacao = produtoList.get(i);
            System.out.println("Valor de i:" + i);
            if (produtoComparacao.getName().equals(nomeProduto)) {
                System.out.println("If: " + produtoList.size());
                editTextProduto.setText("");
                editTextPreco.setText("");
                produtoList.remove(i);
                configurarRecycle();
                snackBar(view, nomeProduto, "OK");

                //Toast.makeText(getApplicationContext(), "Produto Removido", Toast.LENGTH_SHORT).show();
                return;
            }

        }
        System.out.println("Else: " + produtoList.size());
        Toast.makeText(getApplicationContext(), "Produto não existe", Toast.LENGTH_LONG).show();
    }

    public String captalizar(String text) {
        if (text.length() != 0) {
            text = text.substring(0, 1).toUpperCase().concat(text.substring(1));
        }

        return text;
    }

    public void editPrecoListener() {
        editTextPreco.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (editTextProduto.length() == 0) {
                    btnAdd.setVisibility(View.GONE);
                    btnRemove.setVisibility(View.GONE);
                    layoutBtns.setVisibility(View.GONE);
                    if(v.hasFocus() == false) {
                        btnAdd.setVisibility(View.VISIBLE);
                        btnRemove.setVisibility(View.VISIBLE);
                        layoutBtns.setVisibility(View.VISIBLE);
                    }
                }
                else {
                    btnAdd.setVisibility(View.VISIBLE);
                    btnRemove.setVisibility(View.VISIBLE);
                    layoutBtns.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    public void snackBar(View v, String mensagem, String textoBtn) {
        Snackbar.make(v,mensagem +" removido",Snackbar.LENGTH_SHORT)
                .setAction(textoBtn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }
}
