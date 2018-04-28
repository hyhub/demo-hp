package com.example.log4j2.demolog4j2.disruptor.basic.personal;

public class EventMain {
    public static void main(String[] args) throws Exception {
//        ExecutorService executor = Executors.newFixedThreadPool(1);
//
//        ExecutorService executorService = Executors.newFixedThreadPool(20);
//
//        int BUFF_SIZE = 1024;
//
//
//        Disruptor<EventData> spring = new Disruptor<>(EventData.EVENT_FACTORY, BUFF_SIZE, executor);
//
//        spring.handleEventsWith(new EventHandler());
//        spring.start();
//
//        RingBuffer<EventData> ringBuffer = spring.getRingBuffer();
//        Future<Void> future = null;
//        for (int i = 0; i < 40; i++) {
//            future = executorService.submit(new Callable<Void>() {
//                @Override
//                public Void call() throws Exception {
//                    Thread.sleep(1000);
//                    String id = String.valueOf(System.nanoTime());
//                    System.out.println("发布者发布ID：" + id);
//                    //给这个区块放入 数据  如果此处不理解，想想RingBuffer的结构图
//                    long seq = ringBuffer.next();
//                    ringBuffer.get(seq).setId(id);
//                    ringBuffer.publish(seq);
//
//                    return null;
//                }
//            });
//        }
//        future.get();//等待生产者结束
//        //Thread.sleep(1000);//等上1秒，等消费都处理完成
//        executorService.shutdown();//终止线程
//        ByteBuffer bb = ByteBuffer.allocate(8);
//        for (long l = 0; true; l++) {
//            bb.putLong(0, l);
//            ringBuffer.publishEvent((event, sequence, buffer) -> event.setId(String.valueOf(buffer.getLong(0))), bb);
//            Thread.sleep(1000);
//        }
    }
}
