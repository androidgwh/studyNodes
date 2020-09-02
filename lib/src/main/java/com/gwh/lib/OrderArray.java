package com.gwh.lib;

/**
 * Created by Guo Wenhui
 * 2020/8/12
 **/
public class OrderArray {
    private long[] a;
    private int nElems;

    public OrderArray(int max) {
        a = new long[max];
        nElems = 0;
    }

    public int size() {
        return nElems;
    }

    /**
     * 二分查找
     *
     * @param searchKey
     * @return
     */
    public int find(long searchKey) {
        int lowerBound = 0;
        int upperBound = nElems - 1;
        int curIn;
        while (true) {
            curIn = (lowerBound + upperBound) / 2;
            if (a[curIn] == searchKey) {
                return curIn;
            } else if (lowerBound > upperBound)
                //cant't find
                return nElems;
            else {
                if (a[curIn] > searchKey) {
                    upperBound = curIn - 1;
                } else {
                    lowerBound = curIn + 1;
                }
            }
        }
    }

    public void insert(long value) {
        int j;
        for (j = 0; j < nElems; j++) {
            if (a[j] > value)
                break;
        }
        for (int k = nElems; k > j; k--) {
            a[k] = a[k - 1];
        }
        a[j] = value;
        nElems++;
    }

    public boolean delete(long value) {
        int j = find(value);
        if (j == nElems)
            return false;
        else {
            for (int k = j; k < nElems; k++) {
                a[k] = a[k + 1];
            }
            nElems--;
            return true;
        }
    }

    public void display() {
        System.out.println("size " + nElems);
        for (int j = 0; j < nElems; j++) {
            System.out.println(a[j] + "   ");
        }
    }
}
