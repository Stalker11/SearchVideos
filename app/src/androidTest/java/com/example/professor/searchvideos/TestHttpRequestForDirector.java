package com.example.professor.searchvideos;

import android.support.test.runner.AndroidJUnit4;

import com.example.professor.searchvideos.callback.RequestCallBack;
import com.example.professor.searchvideos.http.RequestForServer;
import com.example.professor.searchvideos.models.DescriptionFilm;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@RunWith(AndroidJUnit4.class)
public class TestHttpRequestForDirector {
    private CountDownLatch lock = new CountDownLatch(1);
    @Test
    public void request(){
       new RequestForServer().requestFilmForDirector("Nicolas", new RequestCallBack() {
            @Override
            public DescriptionFilm onSuccess(List<DescriptionFilm> films) {
                assertEquals(null,films);
                return films.get(0);
            }

            @Override
            public String onError(String error) {
                assertEquals("String",error);
                return error;
            }
        });
        try {
            lock.await(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
