package bart.study.chapter01.flow;

import java.util.concurrent.Flow;

public class Main {
    public static void main(String[] args) {
        getCelsiusTemperatures("New York").subscribe(new TempSubscriber());
    }

    private static Flow.Publisher<TempInfo> getCelsiusTemperatures(String town) {
        return subscriber -> {
            TempProcessor processor = new TempProcessor();
            processor.subscribe(subscriber);
            processor.onSubscribe(new TempSubscription(processor, town));
        };
    }
}