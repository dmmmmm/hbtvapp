package com.hongbao.util;
import android.util.Log;
/**
 * ��־������
 */
public class LU {
	private static String sTAG = "dm";
    // ��־�ļ��ܿ���
    private static Boolean sLogOpen = true;
    public static String getTAG(){
        return sTAG;
    }
    public static void setTAG(String tag){
        sTAG = tag;
    }

    /**
     * �ж���־�Ƿ��
     * @return
     */
    public static boolean isLogOpen() {
        return sLogOpen;
    }
    /**
     * ������־�Ƿ��
     * @param open
     */
    public static void setLogOpen(boolean open){
        sLogOpen = open;
    }

    /**
     * log�����ö�� 
     */
    public enum LOG_ENUM {
        INFO, WARN, ERROR
    }

    public static void log(String msg) {
        if (sLogOpen) {
            Log.i(sTAG, msg);
        }
    }

    /**
     * ����log�����ӡlog��Ϣ
     * @param level log����
     * @param msg   log��Ϣ
     */
    public static void log(LOG_ENUM level, String msg) {
        if (sLogOpen) {
            switch (level) {
            case INFO:
                Log.i(sTAG, msg);
                break;
            case WARN:
                Log.w(sTAG, msg);
                break;
            case ERROR:
                Log.e(sTAG, msg);
                break;
            default:
                break;
            }
        }
    }

    /**
     * Ĭ��tagΪdm��log
     * @param msg
     */
    public static void i(String msg) {
        if (sLogOpen) {
            Log.i(sTAG, msg);
        }
    }
    /**
     * �Զ���log
     * @param msg
     */
    public static void i(String tag,String msg) {
        if (sLogOpen) {
            Log.i(tag, msg);
        }
    }
    /**
     * ��¼Debug
     * @param msg
     */
    public static void d(String msg) {
        if (sLogOpen) {
            Log.d(sTAG, msg);
        }
    }

    /**
     * ��¼����
     * @param msg
     */
    public static void w(String msg) {
        if (sLogOpen) {
            Log.w(sTAG, msg);
        }
    }

    /**
     * ��¼����
     * @param msg
     */
    public static void e(String msg) {
        if (sLogOpen) {
            Log.e(sTAG, msg);
        }
    }
    /**
     * ��¼����
     * @param msg
     */
    public static void e(String tag,String msg) {
        if (sLogOpen) {
            Log.e(tag, msg);
        }
    }
	
}
