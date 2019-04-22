package custom.util;

import java.util.*;

/**
 * 最近最少使用
 * LFU 最近最不经常使用
 */
public class LRULinkedList extends LinkedHashMap {
    private final int maxCapacity;

    public LRULinkedList(int maxCapacity) {
        super(maxCapacity);
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry eldest) {
        return size() > maxCapacity*0.75;
    }


}
