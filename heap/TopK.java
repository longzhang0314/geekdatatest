package com.zl.geekdata.heap;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Create by zhanglong on 2019/12/20
 */
public class TopK {

    public static void main(String[] args) {
        Integer[] arr = {2, 3, 5, 8, 11, 22, 97, 98, 33, 44, 55, 66, 77, 88, 99, 100, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        Queue<Integer> topK = getTopK(arr, 5);

        for (Integer i : topK) {
            System.out.print(i + " ");
        }
    }

    private static Queue<Integer> getTopK(Integer[] arr, int k) {
        List<Integer> a = new ArrayList<>(k);
        for(int i=0;i<k;i++){
            a.add(i);
        }
        Queue<Integer> heap = new PriorityQueue<Integer>(k);
        heap.addAll(a);
        for (Integer i : arr) {
            Integer top = heap.peek();
            if (i > top){
                heap.poll();
                heap.add(i);
            }
        }
        return heap;

    }
}
