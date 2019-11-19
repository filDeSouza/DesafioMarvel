package com.example.desafiomarvel.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.model.Date;
import com.example.desafiomarvel.model.Price;
import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.model.TextObject;
import com.example.desafiomarvel.model.Thumbnail;
import com.example.desafiomarvel.view.adapter.RecyclerViewComicAdapter;
import com.example.desafiomarvel.view.interfaces.OnClick;
import com.example.desafiomarvel.viewmodel.ComicViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnClick{

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private RecyclerViewComicAdapter adapter;
    private List<Result> resultList = new ArrayList<>();
    private ComicViewModel comicViewModel;

    private int pagina = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        setScrollView();

        comicViewModel.getAllComics(pagina);

        comicViewModel.getListaHqs().observe(this, resultaLista -> {
            adapter.atualizaLista(resultaLista);
        });

        comicViewModel.getLoading().observe(this, loading -> {
            if (loading){
                progressBar.setVisibility(View.VISIBLE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void initViews() {

        recyclerView = findViewById(R.id.recyclerViewComics);
        progressBar = findViewById(R.id.progressBar);
        adapter = new RecyclerViewComicAdapter(resultList, this);
        comicViewModel = ViewModelProviders.of(this).get(ComicViewModel.class);

    }

    @Override
    public void click(Result result){
        Intent intent = new Intent(MainActivity.this, DetalheActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable("Result", result);
        intent.putExtras(bundle);
        startActivity(intent);

    }

    private void setScrollView(){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState){
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);

                GridLayoutManager gridLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

                int totalItemCount = gridLayoutManager.getItemCount();

                int lastVisible = gridLayoutManager.findLastVisibleItemPosition();

                boolean ultimoItem = lastVisible + 5 >= totalItemCount;

                if (totalItemCount > 0 && ultimoItem){
                    pagina++;
                    comicViewModel.getAllComics(pagina);
                }
            }

        });

    }
}
