package com.huutho.photo;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void test() {



        Observable.fromIterable(getList())
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(@NonNull Integer integer) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private List<Integer> getList(){
        List<Integer> bitmaps = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            bitmaps.add(i);
        }

        return bitmaps;
    }
}