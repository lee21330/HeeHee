package com.shinhan.heehee.config;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AuctionLockManager {
    private static final ConcurrentHashMap<Integer, Lock> lockMap = new ConcurrentHashMap<>();

    public static Lock getLock(int aucSeq) {
        return lockMap.computeIfAbsent(aucSeq, k -> new ReentrantLock());
    }
}