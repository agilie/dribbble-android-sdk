package com.agilie.dribbblesdk.sample.printable;

import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class PrintableCallback<T> implements Callback<T>, Printable {

    static final String LINE_WRAP = "\n";

    private TextView textView;

    public PrintableCallback(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void onResponse(Response<T> response) {
        print(displayResponseOnScreen(response.body(), response));
    }

    @Override
    public void onFailure(Throwable t) {
        if (t != null) {
            print(t.toString());
        }
    }

    @Override
    public void print(String text) {
        textView.setText(text);
        onCompleted();
    }

    private String displayResponseOnScreen(T t, Response response) {
        StringBuffer textViewText = new StringBuffer();
        textViewText.append(String.format("<--- HTTP code %d (%sms)",
                        response.code(),
                        TimeUnit.NANOSECONDS.toMillis(System.nanoTime()))
        );

        textViewText.append(LINE_WRAP);

        Headers headers = response.headers();
        for (String name : headers.names()) {
            textViewText.append(name).append(" --> ").append(headers.get(name)).append(LINE_WRAP);
        }

        if (t != null) {
            textViewText.append(getResponseBody(t)).append(LINE_WRAP);
        }

        textViewText.append(LINE_WRAP).append("<--- END HTTP");

        return textViewText.toString();
    }

    abstract String getResponseBody(T t);
    abstract void onCompleted();
}
