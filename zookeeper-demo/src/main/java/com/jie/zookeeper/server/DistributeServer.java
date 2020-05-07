package com.jie.zookeeper.server;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @version 1.0
 * @author: Jie Zhang
 * @date: 2020/5/7 9:48
 */
public class DistributeServer {

    private String connectString="192.168.222.20:2181,192.168.222.30:2181,192.168.222.40:2181";
    private int sessionTimeOut=2000;
    private ZooKeeper zooKeeper;
    private CountDownLatch lock = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        DistributeServer server = new DistributeServer();

        server.initConnect();

        server.register(args[0]);

        System.in.read();
    }

    private void register(String arg) throws KeeperException, InterruptedException {
        lock.await();
        zooKeeper.create("/servers/server", arg.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        System.out.println("注册成功, " + arg);
    }

    private void initConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeOut, watchedEvent -> {
            if (watchedEvent.getType() == Watcher.Event.EventType.None) {
                lock.countDown();
            }
        });
    }
}
