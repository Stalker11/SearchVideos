package com.example.professor.searchvideos;

import android.support.test.espresso.DataInteraction;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;

import com.example.professor.searchvideos.callback.RequestCallBack;
import com.example.professor.searchvideos.http.RequestForServer;
import com.example.professor.searchvideos.models.DescriptionFilm;

import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class TestHttpRequestForTitle {
    private static final String TAG = TestHttpRequestForTitle.class.getSimpleName();
    private CountDownLatch lock = new CountDownLatch(1);
    private List<DescriptionFilm> list;
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);
    @Test
    public void request(){
        try {
            mActivityRule.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d(TAG, "request: "+1100);
                    new RequestForServer().requestFilmForTitle("A", new RequestCallBack() {
                        @Override
                        public DescriptionFilm onSuccess(List<DescriptionFilm> films) {
                           list = films;
                            Log.d(TAG, "onSuccess: "+100);
                            assertEquals(1000,films.get(1000));
                            return films.get(0);
                        }

                        @Override
                        public String onError(String error) {
                            Log.d(TAG, "onError: "+error);
                            assertNotEquals(null,error);
                            return error;
                        }
                    });
                }

            });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        try {
            lock.await(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
