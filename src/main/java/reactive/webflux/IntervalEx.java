package reactive.webflux;

import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IntervalEx {
    public static void main(String[] args) {
        Publisher<Integer> pub = sub ->{
            sub.onSubscribe(new Subscription() {
                int no = 0;
                boolean cancelled = false;
                @Override
                public void request(long n) {
                    ScheduledExecutorService exec = Executors.newSingleThreadScheduledExecutor();
                    exec.scheduleAtFixedRate(()->{
                        if(cancelled){
                            exec.shutdown();
                            return;
                        }
                        sub.onNext(no++);
                    },0, 500, TimeUnit.MILLISECONDS);
                }

                @Override
                public void cancel() {
                    log.info("#cancel");
                    cancelled = true;
                }
            });
        };

        Publisher<Integer> takePub = sub -> {
           pub.subscribe(new Subscriber<>() {
               int cnt = 0;
               Subscription subscription;
               @Override
               public void onSubscribe(Subscription s) {
                   subscription = s;
                   sub.onSubscribe(s);
               }

               @Override
               public void onNext(Integer integer) {
                    sub.onNext(integer);
                    if(++cnt >= 10){
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

        takePub.subscribe(new Subscriber<>() {
            @Override
            public void onSubscribe(Subscription s) {
                log.info("onSubscribe");
                s.request(Long.MAX_VALUE);
            }

            @Override
            public void onNext(Integer integer) {
                log.info("onNext: {}",integer);
            }

            @Override
            public void onError(Throwable t) {
                log.info("onError: {}",t.getMessage());
            }

            @Override
            public void onComplete() {
                log.info("onComplete");
            }
        });
        log.info("EXIT");
    }
}
