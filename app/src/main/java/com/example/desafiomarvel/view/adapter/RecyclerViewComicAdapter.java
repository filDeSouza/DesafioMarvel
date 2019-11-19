package com.example.desafiomarvel.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.view.interfaces.OnClick;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewComicAdapter extends RecyclerView.Adapter<RecyclerViewComicAdapter.ViewHolder> {

    private List<Result> resultList;
    private OnClick listener;

    public RecyclerViewComicAdapter(List<Result> resultList, OnClick listener) {
        this.resultList = resultList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result result = resultList.get(position);
        holder.onBind(result);

        holder.itemView.setOnClickListener(v -> listener.click(result));
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public void atualizaLista(List<Result> novaLista){

        if (this.resultList.isEmpty()){
            this.resultList = novaLista;
        }else{
            this.resultList.addAll(novaLista);
        }

        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imgComic);
            textView = itemView.findViewById(R.id.txtTitulo);
        }

        public void onBind(Result result){

            textView.setText(result.getTitle());
            String valorImagem = result.getThumbnail().getPath()+".jpg";
            Picasso.get().load(result.getThumbnail().getPath()+".jpg").into(imageView);
            System.out.println("Valor da imagem: " + valorImagem);

        }
    }
}
