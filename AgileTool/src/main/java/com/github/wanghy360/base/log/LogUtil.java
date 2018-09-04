package com.github.wanghy360.base.log;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**
 * @author Wanghy
 */
public final class LogUtil {
    private Handler handler;
    private static final int LOG_WHAT = 1;
    private File logFile;
    private static volatile LogUtil logUtil;

    public static LogUtil getInstance() {
        if (logUtil == null) {
            synchronized (LogUtil.class) {
                if (logUtil == null) {
                    logUtil = new LogUtil();
                }
            }
        }
        return logUtil;
    }

    public void init(String filePath) {
        createFile(filePath);
        HandlerThread handlerThread = new HandlerThread("logcat");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper(), new LogCallBack());
    }

    private class LogCallBack implements Handler.Callback {

        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == LOG_WHAT) {
                LogItem logItem = (LogItem) msg.obj;
                recode(logItem);
            }
            return false;
        }
    }

    private void createFile(String filePathName) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd",
                Locale.CHINA);

        Date curDate = new Date(System.currentTimeMillis());
        String currentDate = formatter.format(curDate);

        File logcatPathName = new File(filePathName + "/logcat");
        if (!logcatPathName.exists()) {
            try {
                logcatPathName.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        logFile = new File(logcatPathName, "Log" + "_" + currentDate + ".txt");
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeLog(int priority, String tag, String msg) {
        Message message = new Message();
        message.what = LOG_WHAT;
        message.obj = new LogItem(priority, tag, msg);
        handler.sendMessage(message);
    }

    private static class LogItem {
        private String priorityStr = "";
        private String tag;
        private String msg;

        public LogItem(int priority, String tag, String msg) {
            switch (priority) {
                case Log.VERBOSE:
                    priorityStr = "VERBOSE";
                    break;
                case Log.DEBUG:
                    priorityStr = "DEBUG";
                    break;
                case Log.INFO:
                    priorityStr = "INFO";
                    break;
                case Log.WARN:
                    priorityStr = "WARN";
                    break;
                case Log.ERROR:
                    priorityStr = "ERROR";
                    break;
            }
            this.tag = tag;
            this.msg = msg;
        }

        public String getTag() {
            return tag;
        }

        public String getMsg() {
            return msg;
        }

        public String getPriorityStr() {
            return priorityStr;
        }
    }


    private void recode(LogItem logItem) {
        if (logFile == null)
            return;

        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (logFile == null || !logFile.exists())
            return;

        SimpleDateFormat format = new SimpleDateFormat("HH时mm分ss秒，E", Locale.CHINA);
        Date curTime = new Date(System.currentTimeMillis());
        String currentTime = format.format(curTime);
        StringBuilder sb = new StringBuilder("[")
                .append(currentTime)
                .append("，")
                .append(logItem.getPriorityStr())
                .append("，")
                .append(logItem.getTag())
                .append(" : ")
                .append(logItem.getMsg())
                .append("]\n\n");
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true), "utf-8"));
            out.write(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
