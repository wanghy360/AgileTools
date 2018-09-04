package com.github.wanghy360.base.log;

import android.support.annotation.NonNull;
import android.util.Log;

import com.github.wanghy360.base.BuildConfig;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Wanghy
 * Log类，可在build.gradle文件里开闭log,方便测试。
 */
public final class LogcatUtil {
    private LogcatUtil() {
    }

    //是否打印Log
    private static final boolean SHOW_LOG = BuildConfig.DEBUG;
    //默认配置，是否记录到日志文件里
    private static final boolean WRITE_TO_FILE = false;
    //默认配置，Error级别的，只要是‘客户端测试环境’，就需要记录到日志文件里
    private static final boolean WRITE_TO_FILE_E = BuildConfig.DEBUG;
    //一行最大字符数
    private static final int LOG_LINE_MAX_LENGTH = 4000;

    public static void v(String tag, String msg) {
        v(tag, msg, WRITE_TO_FILE);
    }

    public static void d(String tag, String msg) {
        d(tag, msg, WRITE_TO_FILE);
    }

    /***
     * 使用前要进行{@code BuildConfig.ENV_CLIENT_TEST}判断,以提高正式环境的性能
     */
    public static void d(String tag, Object... msgArr) {
        StringBuilder stringBuilder = new StringBuilder("###").append(" ");
        for (Object object : msgArr) {
            appendObject(object, stringBuilder);
        }
        stringBuilder.append("$$$");
        int cutTime = stringBuilder.length() / LOG_LINE_MAX_LENGTH;
        if (cutTime > 0) {
            //分段输出
            for (int i = 0; i <= cutTime; i++) {
                if (i == cutTime) {
                    d(tag, stringBuilder.substring(i * LOG_LINE_MAX_LENGTH), WRITE_TO_FILE);
                } else {
                    d(tag, stringBuilder.substring(i * LOG_LINE_MAX_LENGTH, (i + 1) * LOG_LINE_MAX_LENGTH), WRITE_TO_FILE);
                }
            }
        } else {
            d(tag, stringBuilder.toString(), WRITE_TO_FILE);
        }
    }

    /**
     * 打印Map Log
     */
    public static <K, V> void d(String tag, Map<K, V> map) {
        StringBuilder stringBuilder = new StringBuilder();
        appendMap(map, stringBuilder);
        d(tag, stringBuilder.toString(), WRITE_TO_FILE);
    }

    /**
     * 将Map分解到StringBuilder
     */
    private static <K, V> void appendMap(Map<K, V> map, @NonNull StringBuilder stringBuilder) {
        if (map == null) {
            stringBuilder.append("Your map is null.").append(" ");
            return;
        }

        if (map.size() == 0) {
            stringBuilder.append("Your map size is 0.").append(" ");
            return;
        }
        stringBuilder.append("{").append(" ");
        Iterator<Map.Entry<K, V>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<K, V> entry = iterator.next();
            appendObject(entry.getKey(), stringBuilder);
            stringBuilder.append("= ");
            appendObject(entry.getValue(), stringBuilder);
            if (iterator.hasNext()) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("}").append(" ");
    }

    /**
     * 将Object分解到StringBuilder
     */
    private static void appendObject(Object object, @NonNull StringBuilder stringBuilder) {
        if (object == null) {
            stringBuilder.append("null").append(" ");
            return;
        }
        if (object instanceof Map) {
            appendMap((Map) object, stringBuilder);
        } else {
            Class<?> msgClass = object.getClass();
            if (msgClass.isArray()) {
                stringBuilder.append("[ ");
                int length = Array.getLength(object);
                for (int j = 0; j < length; j++) {
                    if (j != 0) {
                        stringBuilder.append(", ");
                    }
                    appendObject(Array.get(object, j), stringBuilder);
                }
                stringBuilder.append("]").append(" ");
            } else {
                stringBuilder.append(object).append(" ");
            }
        }
    }

    public static void i(String tag, String msg) {
        i(tag, msg, WRITE_TO_FILE);
    }

    public static void w(String tag, String msg) {
        w(tag, msg, WRITE_TO_FILE);
    }

    public static void e(String tag, String msg) {
        e(tag, msg, WRITE_TO_FILE_E);
    }

    //**************************************分割线********************************************

    public static void v(String tag, String msg, boolean writeToFile) {
        if (SHOW_LOG) {
            Log.v(tag, msg);
            if (writeToFile) {
                LogUtil.getInstance().writeLog(Log.VERBOSE, tag, msg);
            }
        }
    }

    public static void d(String tag, String msg, boolean writeToFile) {
        if (SHOW_LOG) {
            Log.d(tag, msg);
            if (writeToFile) {
                LogUtil.getInstance().writeLog(Log.DEBUG, tag, msg);
            }
        }
    }

    public static void i(String tag, String msg, boolean writeToFile) {
        if (SHOW_LOG) {
            Log.i(tag, msg);
            if (writeToFile) {
                LogUtil.getInstance().writeLog(Log.INFO, tag, msg);
            }
        }
    }

    public static void w(String tag, String msg, boolean writeToFile) {
        if (SHOW_LOG) {
            Log.w(tag, msg);
            if (writeToFile) {
                LogUtil.getInstance().writeLog(Log.WARN, tag, msg);
            }
        }
    }

    public static void e(String tag, String msg, boolean writeToFile) {
        if (SHOW_LOG) {
            Log.e(tag, msg);
            if (writeToFile) {
                LogUtil.getInstance().writeLog(Log.ERROR, tag, msg);
            }
        }
    }

    //**************************************分割线********************************************

    public static void e(String tag, Throwable throwable) {
        e(tag, throwable, WRITE_TO_FILE_E);
    }

    /**
     * 打印Throwable
     */
    public static void e(String tag, Throwable throwable, boolean writeToFile) {
        e(tag, convertThrowableToString(throwable), writeToFile);
    }

    public static String convertThrowableToString(Throwable throwable) {
        StackTraceElement[] stackElements = throwable.getStackTrace();//通过Throwable获得堆栈信息
        StringBuilder sb = new StringBuilder(throwable.toString()).append("\n");
        if (stackElements != null) {
            for (StackTraceElement stackElement : stackElements) {
                sb.append("在")
                        .append(stackElement.getClassName())
                        .append("，第：")
                        .append(stackElement.getLineNumber()).append("行，调用")
                        .append(stackElement.getMethodName()).append("方法。\n");
            }
        }
        return sb.toString();
    }
}