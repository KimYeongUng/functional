package reactive.streams;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

@Slf4j
public class PubSub {
    public static void main(String[] args) {
        Publisher<Integer> pub = sub-> sub.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                sub.onNext(1);
                sub.onNext(2);
                sub.onNext(3);
                sub.onComplete();
            }

            @Override
            public void cancel() {

            }
        });

        pub.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                log.debug("OnSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                log.debug("#onNext: {}",integer);
            }

            @Override
            public void onError(Throwable t) {
                log.error("#onError");
            }

            @Override
            public void onComplete() {
                log.debug("#OnComplete");
            }
        });

        log.debug("#Exit");
    }
}
