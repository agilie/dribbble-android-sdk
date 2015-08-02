package agilie.dribbblesdkexample.printable;

import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Header;
import retrofit.client.Response;

public abstract class PrintableCallback<T> implements Callback<T>, Printable {

    static final String LINE_WRAP = "\n";

    private TextView textView;

    public PrintableCallback(TextView textView) {
        this.textView = textView;
    }

    @Override
    public void success(T t, Response response) {
        print(displayResponseOnScreen(t, response));
    }

    @Override
    public void failure(RetrofitError error) {
        if (error != null) {
            print(displayResponseOnScreen(null, error.getResponse()));
        }
    }

    @Override
    public void print(String text) {
        textView.setText(text);
        onCompleted();
    }

    private String displayResponseOnScreen(T t, Response response) {
        StringBuffer textViewText = new StringBuffer();
        textViewText.append(String.format("<--- HTTP %s %s (%sms)",
                        response.getStatus(),
                        response.getUrl(),
                        TimeUnit.NANOSECONDS.toMillis(System.nanoTime()))
        );

        textViewText.append(LINE_WRAP);

        for (Header header : response.getHeaders()) {
            textViewText.append(header.toString()).append(LINE_WRAP);
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
