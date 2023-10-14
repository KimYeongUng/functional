package reactive.streams;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class PubSub {
    public static void main(String[] args) {
        Publisher<Integer> pub = sub-> sub.onSubscribe(new Subscription() {
            @Override
            public void request(long n) {
                log.debug("#request");
                sub.onNext(1);
                sub.onNext(2);
                sub.onNext(3);
                sub.onComplete();
            }

            @Override
            public void cancel() {

            }
        });

        // processing using other Thread
        Publisher<Integer> subOnPub = sub->{
            ExecutorService es = Executors.newSingleThreadExecutor(new CustomizableThreadFactory(){
                @Override
                public String getThreadNamePrefix(){
                    return "subOn-";
                }
            });

            es.execute(()->pub.subscribe(sub));
            es.shutdown();
        };

        Publisher<Integer> pubOnPub = sub->{
            subOnPub.subscribe(new Subscriber<>() {
                ExecutorService es = Executors.newSingleThreadExecutor(new CustomizableThreadFactory(){
                    @Override
                    public String getThreadNamePrefix(){
                        return "pubOn-";
                    }
                });

                @Override
                public void onSubscribe(Subscription s) {
                    sub.onSubscribe(s);
                }

                @Override
                public void onNext(Integer integer) {
                    es.execute(()->sub.onNext(integer));
                }

                @Override
                public void onError(Throwable t) {
                    es.execute(()->sub.onError(t));
                }

                @Override
                public void onComplete() {
                    es.execute(()->sub.onComplete());
                    es.shutdown();
                }
            });
        };

        pubOnPub.subscribe(new Subscriber<>() {
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
