package com.feibai.study.demos.multithread.cooperation;

/**
 * https://www.bilibili.com/video/BV1ct411n7oG/?t=42.2&p=224&vd_source=dcc95fcbd30595aaef4fea8f566a3de4
 * <p>
 * 协作模型:生产者消费者实现
 * 方式二:信号灯法 借助标志位
 * 适用场景：交替执行
 * 1） 交替循环打印0，1，0，1
 * 2） 做了动作A, 再做动作B，循环往复
 * <p>
 * 生产者:player
 * 消费者:watcher
 *
 * @author feibai
 */
public class TV_SignalLight {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();
    }
}

//生产者 演员
class Player extends Thread {
    private TV tv;

    public Player(TV tv) {
        this.tv = tv;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                this.tv.play("奇葩说");
            } else {
                this.tv.play("太污了，喝瓶立白洗洗嘴");
            }
        }
    }
}

//消费者 观众
class Watcher extends Thread {
    private TV tv;

    public Watcher(TV tv) {
        this.tv = tv;
    }

    public void run() {
        for (int i = 0; i < 20; i++) {
            this.tv.watch();
        }
    }
}

//同一个资源 电视
class TV {
    String content;

    /**
     * 信号灯
     * T 表示演员表演 观众等待
     * F 表示观众观看 演员等待
     */
    boolean flag = true;

    // 表演
    public synchronized void play(String content) {
        if (!flag) {
            // 演员等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 表演
        System.out.println("表演了:" + content);
        this.content = content;
        // 唤醒
        this.notifyAll();
        // 切换标志
        this.flag = !this.flag;
    }

    // 观看
    public synchronized void watch() {
        if (flag) {
            // 观众等待
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 观看
        System.out.println("听到了:" + content);
        // 唤醒
        this.notifyAll();
        // 切换标志
        this.flag = !this.flag;
    }
}