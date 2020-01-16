package com.zl.geekdata.hashtable;

/**
 * Create by zhanglong on 2019/12/12
 */
public class HashTable<K, V> {

    //默认长度
    private static final int DEFAULT_INIT_CAPACITY = 8;

    //装载因子
    private static final float LOAD_FACTOR = 0.75f;

    //散列表数组
    private Entry<K, V>[] table;

    //元素个数
    private int size = 0;

    //散列表索引数量
    private int use = 0;

    //构造
    public HashTable() {
        this.table = (Entry<K, V>[]) new Entry[DEFAULT_INIT_CAPACITY];
    }

    //散列表数组中每个节点元素
    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    //散列函数
    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * 插入
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int index = hash(key);
        //该位置空
        if (table[index] == null) {
            //哨兵节点
            table[index] = new Entry<>(null, null, null);
        }
        //该位置的头节点
        Entry<K, V> temp = table[index];
        if (temp.next == null) {
            //新增在该索引的节点
            temp.next = new Entry<>(key, value, null);
            size++;
            use++;
            if (use >= table.length * LOAD_FACTOR) {
                //扩容
                resize();
            }
        } else {
            do {
                //从哨兵节点开始后移
                temp = temp.next;
                if (temp.key == key) {
                    temp.value = value;
                    return;
                }
            } while (temp.next != null);

            //如果遍历完链表没有重复，插入哨兵节点后的第一个节点
            Entry<K, V> insert = table[index].next;
            table[index].next = new Entry<>(key, value, insert);
            size++;
        }
    }

    //扩容
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[oldTable.length * 2];
        use = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null || oldTable[i].next == null) {
                continue;
            }
            //临时节点,从该位置的哨兵开始
            Entry<K, V> kv = oldTable[i];
            while (kv.next != null) {
                kv = kv.next;
                int index = hash(kv.key);
                if(table[index]==null){
                    use++;
                    table[index] = new Entry<>(null,null,null);
                }
                table[index].next = new Entry<>(kv.key,kv.value,null);
            }
        }
    }

    /**
     * 删除
     */
    public void remove(K key) {
        int index = hash(key);
        //待删除节点
        Entry<K, V> kv = table[index];
        //该节点上没有元素
        if (kv == null || kv.next == null) {
            return;
        }
        //哨兵
        Entry<K, V> head = table[index];
        //待删除节点的前一个节点
        Entry<K, V> pre;
        do {
            pre = kv;
            kv = kv.next;
            if (key == kv.key) {
                pre.next = kv.next;
                size--;
                //删除后判断是否删除了该位置唯一节点
                if (head.next == null) {
                    use--;
                }
                return;
            }

        } while (kv.next != null);
    }

    /**
     * 通过key获取值
     *
     * @param key
     * @return
     */
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> kv = table[index];
        if (kv == null || kv.next == null) {
            return null;
        }
        while (kv.next != null) {
            kv = kv.next;
            if (key == kv.key) {
                return kv.value;
            }
        }
        return null;
    }


}
