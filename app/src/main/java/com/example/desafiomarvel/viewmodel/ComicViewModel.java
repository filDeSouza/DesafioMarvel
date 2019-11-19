package com.example.desafiomarvel.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.desafiomarvel.model.Result;
import com.example.desafiomarvel.repository.ComicRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.desafiomarvel.viewmodel.AppUtils.md5;

public class ComicViewModel extends AndroidViewModel {

    private MutableLiveData<List<Result>> listaComics = new MutableLiveData<>();
    private MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private CompositeDisposable disposable = new CompositeDisposable();
    private ComicRepository repository = new ComicRepository();
    public static final String PRIVATE_KEY = "e14bbda3febb4ca26b6dacc098430754cc7e62cc";
    public static final String PUBLIC_KEY = "d374c335eff01bf5809078f9ffbfdf82";
    String ts = Long.toString(System.currentTimeMillis()/1000);
    String hash = md5(ts+PRIVATE_KEY+PUBLIC_KEY);

    public ComicViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Result>> getListaHqs(){
        return this.listaComics;
    }

    public LiveData<Boolean> getLoading(){
        return this.loading;
    }

    public void getAllComics(int pagina){
        disposable.add(
                repository.getHqs(pagina, "magazine", "comic", true, "focDate", ts, hash, PUBLIC_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable1 -> loading.setValue(true))
                .doOnTerminate(() -> loading.setValue(false))
                .subscribe(response -> {

                    listaComics.setValue(response.getData().getResults());
                    Log.i("LOG", "API: " + response.getData().getResults() );

                }, throwable -> {
                    Log.i("LOG", "Error: " + throwable.getMessage());
                })
        );
    }

}
