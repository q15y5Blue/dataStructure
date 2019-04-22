package custom.data;

import java.util.ArrayList;

public class CreatDemo {

    final static ArrayList<Integer> list = new ArrayList<>();
    static final Integer MAX_SIZE = 100;

    public static void main(String[] args) {
        Thread thread1 = new Consumer();
        Thread thread2 = new Creater();
        thread1.start();
        thread2.start();
    }
}

class Consumer extends Thread {
    @Override
    public void run() {
        synchronized (CreatDemo.list) {
            while (true){
                if (CreatDemo.list.size() > 0) {
                    CreatDemo.list.remove(0);
                }
                if(CreatDemo.list.size()==0){
                    try {
                        System.out.println("消费线程等待:" + CreatDemo.list.size());
                        CreatDemo.list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                CreatDemo.list.notify();
            }
        }
    }
}

class Creater extends Thread {
    @Override
    public void run() {
        synchronized (CreatDemo.list) {
           while (true){
               if (CreatDemo.list.size() < CreatDemo.MAX_SIZE) {
                   CreatDemo.list.add(0);
               }
               if(CreatDemo.list.size()==CreatDemo.MAX_SIZE){
                   try {
                       System.out.println("生产线程等待,已生产满了" + CreatDemo.list.size());
                       CreatDemo.list.wait();
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
               }
               CreatDemo.list.notify();
           }
        }

    }
}