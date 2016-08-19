package com.is.health.util;

import com.is.health.entity.Classify;

import java.util.Comparator;

/**
 * Created by Administrator on 2016/8/18.
 */
public class SortComparator implements Comparator {
    @Override
    public int compare(Object lhs, Object rhs) {
        Classify a = (Classify) lhs;
        Classify b = (Classify) rhs;

        return (b.getId() - a.getId());
    }
}