package reactive.webflux;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class FluxIntervalEx {
    public static void main(String[] args) {
        Publisher<Integer> pub = sub -> {

            sub.onSubscribe(new Subscription() {
                volatile boolean isCancelled = false;
                int no = 0;
                @Override
                public void request(long n) {
                    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
                    exec.scheduleAtFixedRate(()-> {
                        if(isCancelled)
                            exec.shutdown();
                        sub.onNext(no++);
                    },0,300, TimeUnit.MILLISECONDS);
                }

                @Override
                public void cancel() {
                    isCancelled = true;
                }
            });

        };

        Publisher<Integer> takePub = sub->{
            pub.subscribe(new Subscriber<>() {
                int counter = 0;
                Subscription subscription;
                @Override
                public void onSubscribe(Subscription s) {
                    subscription = s;
                    sub.onSubscribe(s);
                }

                @Override
                public void onNext(Integer integer) {
                    sub.onNext(integer);
                    if(++counter > 4){
                        subscription.cancel();
                    }
                }

                @Override
                public void onError(Throwable t) {
                    sub.onError(t);
                }

                @Override
                public void onComplete() {
                    sub.onComplete();
                }
            });
        };

        Flux<Integer> myflux = Flux.just(1,2,3,4,5);

        myflux.subscribe(System.out::println);

        takePub.subscribe(new Subscriber<>() {
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
    }
}
