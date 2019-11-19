package com.example.desafiomarvel.repository;

import com.example.desafiomarvel.model.ComicsResult;

import io.reactivex.Observable;

import static com.example.desafiomarvel.data.remote.RetrofitService.getApiService;

public class ComicRepository {

    public Observable<ComicsResult> getHqs(int pagina, String format, String formatType, boolean noVariants, String orderBy, String ts, String hash, String apiKey){
        return getApiService().getAllComics(pagina, format, formatType, noVariants, orderBy, ts, hash, apiKey);
    }

}
