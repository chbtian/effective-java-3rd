package io.chbtian.effectivejava3rd.thread;

/**
 * 双重校验锁的单例实现
 * 双重检查是关键
 * volatile是关键，可见性和有序性
 */
public class SingletenDemo {
    private volatile SingletenDemo instance;

    private SingletenDemo() {
    }

    public SingletenDemo getInstance() {
        if (instance == null) {
            synchronized (SingletenDemo.class) {
                if (instance == null) {
                    instance = new SingletenDemo();
                }
            }
        }
        return this.instance;
    }

}
