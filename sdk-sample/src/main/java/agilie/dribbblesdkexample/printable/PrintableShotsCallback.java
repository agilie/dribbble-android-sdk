package agilie.dribbblesdkexample.printable;

import android.widget.TextView;

import java.util.List;

import agilie.dribbblesdk.domain.Shot;

public class PrintableShotsCallback extends PrintableCallback<List<Shot>> {

    public PrintableShotsCallback(TextView textView) {
        super(textView);
    }

    @Override
    String getResponseBody(List<Shot> shots) {
        StringBuilder body = new StringBuilder();
        for (Shot shot : shots) {
            body.append(shot.toGson()).append(LINE_WRAP);
        }

        return body.toString();
    }

    @Override
    protected void onCompleted() {
        // TODO: override if required
    }
}
