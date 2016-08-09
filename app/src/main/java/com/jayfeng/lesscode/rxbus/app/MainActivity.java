package com.jayfeng.lesscode.rxbus.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jayfeng.lesscode.rxbus.RxBus;
import com.jayfeng.lesscode.rxbus.app.event.MessageEvent;

import java.util.Random;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private Button mSendButton;

    private CompositeSubscription mEventSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSendButton = (Button) findViewById(R.id.send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxBus.getInstance().post(new MessageEvent(new Random().nextInt(10000)));
            }
        });

        mEventSubscription = new CompositeSubscription();

        // 事件总线
        onEvent();
    }

    private void onEvent() {
        mEventSubscription.add(RxBus.toSubscription(MessageEvent.class, new Action1<MessageEvent>() {
            @Override
            public void call(MessageEvent messageEvent) {
                Toast.makeText(MainActivity.this, messageEvent.getRandomNum() + "", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        mEventSubscription.unsubscribe();
    }
}