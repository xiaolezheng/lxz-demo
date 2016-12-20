package com.lxz.xxx.common.util;

import org.apache.commons.collections.MapUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 过滤字段
 *
 */
public class BeanUtil {

    public static Object filter(Object obj, Set<String> exclude) {
        if (obj instanceof List) {
            return filterList((List) obj, exclude);
        } else if (obj instanceof Map) {
            return filterMap((Map) obj, exclude);
        } else {
            return null;
        }
    }

    public static List filterList(List obj, Set<String> exclude) {
        if (obj == null || obj.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        List list = new ArrayList();
        for (int i = 0; i < obj.size(); i++) {
            if (obj.get(i) instanceof List) {
                list.add(filterList((List) obj.get(i), exclude));
            } else if (obj.get(i) instanceof Map) {
                list.add(filterMap((Map) obj.get(i), exclude));
            } else {
                list.add(obj.get(i));
            }
        }
        return list;
    }

    public static Map filterMap(Map map, Set<String> exclude) {
        if (map == null || map.keySet().size() == 0) {
            return MapUtils.EMPTY_MAP;
        }
        Object[] keys = map.keySet().toArray();
        for (int i = 0; i < keys.length; i++) {
            if (exclude.contains(keys[i].toString()) || map.get(keys[i]) == null) {
                map.remove(keys[i]);
                continue;
            }
            if (map.get(keys[i]) instanceof Map) {
                map.put(keys[i], filterMap((Map) map.get(keys[i]), exclude));
            } else if (map.get(keys[i]) instanceof List) {
                map.put(keys[i], filterList((List) map.get(keys[i]), exclude));
            }

        }
        return map;
    }

}