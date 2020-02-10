package com.example.listadecompras.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listadecompras.R;
import com.example.listadecompras.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class AdapterProdutos extends RecyclerView.Adapter<AdapterProdutos.ViewHolderListaProdutos> {
    List<Produto> listaDeProdutos = new ArrayList<>();


    //Construtor
    public AdapterProdutos(List<Produto> listaDeProdutos) {
        this.listaDeProdutos = listaDeProdutos;
    }


    @NonNull
    @Override
    public ViewHolderListaProdutos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View listaLayout = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_adapter, parent, false);
        return new ViewHolderListaProdutos(listaLayout);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderListaProdutos holder, int position) {
        Produto produto = this.listaDeProdutos.get(position);
        holder.produto.setText(produto.getName());
        holder.valor.setText("R$ " + produto.getTotal());
        holder.qtd.setText(String.valueOf(produto.getQtd()));
    }

    @Override
    public int getItemCount() {
        return listaDeProdutos.size();
    }


    //ViewHolder
    public class ViewHolderListaProdutos extends RecyclerView.ViewHolder {

        TextView produto;
        TextView valor;
        EditText qtd;

        public ViewHolderListaProdutos(@NonNull View itemView) {
            super(itemView);

            produto = itemView.findViewById(R.id.textViewProduto);
            valor   = itemView.findViewById(R.id.textViewValor);
            qtd     = itemView.findViewById(R.id.editTextQuantidade);
        }
    }

}
