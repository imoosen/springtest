package com.imoosen.log;

/**
 * Created by Administrator on 2017/6/27 0027.
 */
/**
 * 日志编号生成器
 * @author : Qiulingdong
 */
public class LogGenerIdContainer {
    private static final ThreadLocal<String> logUidThreadLocal = new ThreadLocal<String>();
    private static LogGenerIdContainer container = null;
    private static Object obj;

    public static LogGenerIdContainer getInstance() {
        if (container == null) {
            synchronized (obj) {
                if (container == null) {
                    container = new LogGenerIdContainer();
                }
            }
        }
        return container;
    }

    private LogGenerIdContainer() {

    }

    public static String getGenerId() {
        return logUidThreadLocal.get();
    }

    public static void setGenerId(String uuid) {
        logUidThreadLocal.set(uuid);
    }

}