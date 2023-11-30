package boot.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class LoadTest {
    static AtomicInteger atomicInteger = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        ExecutorService service = Executors.newFixedThreadPool(100);

        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/rest?idx={idx}";

        CyclicBarrier barrier = new CyclicBarrier(101);
        StopWatch watch = new StopWatch();
        watch.start();

        for (int i=0;i<100;i++){
            service.submit(()->{
                int cnt = atomicInteger.addAndGet(1);
                barrier.await();
                log.info("thread-{}",cnt);

                StopWatch threadwatch = new StopWatch();
                threadwatch.start();
                template.getForObject(url,String.class);
                threadwatch.stop();

                String res = template.getForObject(url,String.class,cnt);

                log.info("Elapsed: {} {} {}",cnt,threadwatch.getTotalTimeSeconds()+"sec",res);
                return null; // callable - cyclicbarrier
            });
        }

        barrier.await();
        service.shutdown();
        watch.stop();

        log.info("TotalTime: {}",watch.getTotalTimeSeconds());

    }
}
