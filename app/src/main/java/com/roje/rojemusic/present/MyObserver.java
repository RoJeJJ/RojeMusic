package com.roje.rojemusic.present;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.roje.rojemusic.utils.NetWorkUtil;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class MyObserver<T> implements Observer<T> {
        private Disposable d;
        protected abstract void next(T t);
        private Context mContext;
        protected MyObserver(Context context){
            this.mContext = context;
        }
        protected void error(){}
        @Override
        public void onSubscribe(Disposable d) {
            this.d = d;
            if (!NetWorkUtil.isNetWorkAvailable(mContext)) {
                disposable();
            }
        }

        @Override
        public void onNext(T t) {
            next(t);
        }

        @Override
        public void onError(Throwable e) {
            e.printStackTrace();
            error();
            if (e instanceof MyException){
                Toast.makeText(mContext,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onComplete() {
            d.dispose();
        }

        public void disposable(){
            if (d != null && !d.isDisposed())
                d.dispose();
        }
    }