package com.jie.zookeeper.client;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @version 1.0
 * @author: Jie Zhang
 * @date: 2020/5/7 9:57
 */
public class DistributeClient {

    private String connectString="192.168.222.20:2181,192.168.222.30:2181,192.168.222.40:2181";
    private int sessionTimeOut=2000;
    private ZooKeeper zooKeeper;
    private CountDownLatch lock = new CountDownLatch(1);

    public static void main(String[] args) throws Exception {

        DistributeClient client = new DistributeClient();

        client.initConnect();

        client.getChilds();

        System.in.read();
    }

    private void getChilds() throws KeeperException, InterruptedException {

        lock.await();

        List<String> children = zooKeeper.getChildren("/servers", true);

        System.out.println("---------start-----------");
        children.forEach(item -> {
            byte[] data = new byte[0];
            try {
                data = zooKeeper.getData("/servers/" + item, true, null);
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new String(data) + " is online.....");
        });
        System.out.println("---------end-------------");
    }



    private void initConnect() throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                if (watchedEvent.getType() == Event.EventType.None) {
                    lock.countDown();
                }
                System.out.println(watchedEvent);

                if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged ||
                        watchedEvent.getType() == Event.EventType.NodeDataChanged) {
                    try {
                        getChilds();
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
