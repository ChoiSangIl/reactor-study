package bart.study.chapter01.flow;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class TempSubscription implements Subscription {

    private final Subscriber<? super TempInfo> subscriber;
    private final String town;
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    public TempSubscription(Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    @Override
    public void request(long n) {
        executor.submit(() -> {
            for (long l = 0L; l < n; l++) {
                try {
                    subscriber.onNext(TempInfo.fetch(town)); // 현재 온도를 subscriber로 전달
                } catch (Exception e) {
                    subscriber.onError(e); // 실패하면 subscriber로 에러 전달
                    break;
                }
            }
        });
    }

    @Override
    public void cancel() {
        subscriber.onComplete(); // 구독취소되면 subscriber로 전달
    }
}