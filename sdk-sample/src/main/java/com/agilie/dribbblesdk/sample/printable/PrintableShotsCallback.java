package com.agilie.dribbblesdk.sample.printable;

import android.widget.TextView;

import com.agilie.dribbblesdk.domain.Shot;

import java.util.List;

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
