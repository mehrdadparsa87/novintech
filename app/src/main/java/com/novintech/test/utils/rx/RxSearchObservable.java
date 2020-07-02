package com.novintech.test.utils.rx;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class RxSearchObservable {

    private RxSearchObservable() {
        // no instance
    }

    public static Observable<String> fromView(EditText searchView) {

        final PublishSubject<String> subject = PublishSubject.create();

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                subject.onNext(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                subject.onNext(s);
//                return true;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String text) {
//                subject.onNext(text);
//                return true;
//            }
//        });

        return subject;
    }
}
