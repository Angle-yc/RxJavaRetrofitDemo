package com.angle.hshb.rxjavaretrofitdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.angle.hshb.rxjavaretrofitdemo.api.RxRfService;
import com.angle.hshb.rxjavaretrofitdemo.bean.MovieData;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import rx.Subscriber;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.tv_text)
    TextView tvText;
    @InjectView(R.id.btn_download)
    Button btnDownLoad;
    Subscriber subscriber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
    }
    @OnClick(R.id.btn_download)
    public void onClick(View view){
        getMovie();
    }

    private void getMovie() {
        subscriber = new Subscriber<MovieData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MovieData movieData) {
                tvText.setText(movieData.toString());
            }
        };
        RxRfService.getInstance().getTopMovie(subscriber,0,10);
    }
}
