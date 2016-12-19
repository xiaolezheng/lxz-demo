package com.lxz.xxx.common.util;

import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import com.lxz.xxx.api.exception.XxxException;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by xiaolezheng on 16/9/1.
 */
public final class ResourceUtil {
    public static List<String> readClassPathFileToLineList(String fileName) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(fileName), "文件名称不能为空");

        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            List<String> lineList = CharStreams.readLines(new InputStreamReader(inputStream));

            return lineList;
        } catch (Exception e) {
            throw new XxxException(e, "加载文件[%s]失败", fileName);
        }

    }

}
