package com.example.desafiomarvel.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desafiomarvel.R;
import com.example.desafiomarvel.model.Result;
import com.squareup.picasso.Picasso;

public class DetalheActivity extends AppCompatActivity {

    private ImageView imgViewBannerTopo;
    private ImageView imgViewComic;
    private TextView txtViewTitulo;
    private TextView txtViewDetalhe;
    private TextView txtViewDataPublicacao;
    private TextView txtViewPreco;
    private TextView txtViewPaginas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        initViews();

        if (getIntent() != null){

            Result result = getIntent().getParcelableExtra("Result");
            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imgViewComic);
            Picasso.get().load(result.getThumbnail().getPath() + ".jpg").into(imgViewBannerTopo);
            txtViewTitulo.setText(result.getTitle());
            System.out.println("Valor do título: " + result.getTitle());
            txtViewDetalhe.setText(result.getDescription());
            System.out.println("Valor da descricao: " + result.getDescription());

            String dataISO = result.getDates().get(0).getDate().split("T")[0];
            String[] dates = dataISO.split("-");
            String dataUsuario = dates[2] + "/" + dates[1] + "/" + dates[0];
            txtViewDataPublicacao.setText(dataUsuario);


            System.out.println("Valor da data: " + dataUsuario);
            txtViewPreco.setText("US$ " + String.format("%.2f", result.getPrices().get(0).getPrice()));
            System.out.println("Valor dos precos: " + "US$ " + String.format("%.2f", result.getPrices().get(0).getPrice()));
            txtViewPaginas.setText(result.getPageCount().toString() + " pages");
            System.out.println("Valor das paginas: " + result.getPageCount().toString() + " pages");

        }

    }

    private void initViews() {

        imgViewBannerTopo = findViewById(R.id.imageViewTopo);
        imgViewComic = findViewById(R.id.imageViewComic);
        txtViewTitulo = findViewById(R.id.textViewTitulo);
        txtViewDetalhe = findViewById(R.id.textViewDescricao);
        txtViewDataPublicacao = findViewById(R.id.textViewDataPublicacao);
        txtViewPreco = findViewById(R.id.textViewPreco);
        txtViewPaginas = findViewById(R.id.textViewPaginas);

    }
}
