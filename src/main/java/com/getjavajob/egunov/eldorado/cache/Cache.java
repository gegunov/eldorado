package com.getjavajob.egunov.eldorado.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Cache {

    private long timeToLive = TimeUnit.HOURS.toMillis(1);
    private Map<String, CacheEntry> entries = new LinkedHashMap();
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock writeLock = lock.writeLock();
    private Lock readLock = lock.readLock();

    public Cache() {
        cleanCache();
    }

    private class CacheEntry {
        private String message;
        private long createdTime;

        public CacheEntry(String message) {
            this.message = message;
            this.createdTime = System.currentTimeMillis();
        }

        public String getMessage() {
            return message;
        }

        public long getCreatedTime() {
            return createdTime;
        }

        @Override
        public int hashCode() {
            return message.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            CacheEntry cacheEntry = (CacheEntry) o;
            if (!message.equals(cacheEntry.message)) return false;

            return true;
        }
    }

    public void addMessage(String str) {
        writeLock.lock();
        try {
            entries.put(str, new CacheEntry(str));
        } finally {
            writeLock.unlock();
        }
    }

    public String getMessage(String str) {
        readLock.lock();
        try {
            return entries.get(str) != null ? entries.get(str).getMessage() : null;
        } finally {
            readLock.unlock();
        }
    }

    public void cleanCache() {

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new Runnable() {
            @Override
            public void run() {
                writeLock.lock();
                try {
                    Iterator<Map.Entry<String, CacheEntry>> iterator = entries.entrySet().iterator();
                    while (iterator.hasNext()) {
                        CacheEntry currentEntry = iterator.next().getValue();
                        if (currentEntry.getCreatedTime() == System.currentTimeMillis() - timeToLive) {
                            iterator.remove();
                        } else {
                            break;
                        }
                    }
                } finally {
                    writeLock.unlock();
                }
            }
        };
        service.schedule(task, timeToLive, TimeUnit.MILLISECONDS);
    }

    public long getTimeToLive() {
        return timeToLive;
    }

}
