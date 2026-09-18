import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

public class LinkedTransferQueueTest01 {
    static void main(String[] args) throws InterruptedException {
//        ConcurrentLinkedQueue, SynchronousQueue, LinkedBlockingQueue
        TransferQueue<Object> tq = new LinkedTransferQueue<>();
        System.out.println(tq.add("william"));
        System.out.println(tq.offer("william"));
        System.out.println(tq.offer("william", 10, TimeUnit.SECONDS));
        tq.put("DevDojo");

        if (tq.hasWaitingConsumer()){
            tq.transfer("DevDojo");
        }

        System.out.println(tq.tryTransfer("Academy"));
        System.out.println(tq.tryTransfer("Academy", 5, TimeUnit.SECONDS));
        System.out.println(tq.element());
        System.out.println(tq.peek());
        System.out.println(tq.poll());
        System.out.println(tq.remove());
        System.out.println(tq.take());
        System.out.println(tq.remainingCapacity());


    }
}
