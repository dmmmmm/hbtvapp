package com.hongbao.util;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
/**
 * 接口参数Bean
 */ 
public class IParams <T>{
	
	/*********操作   o（增删该查）************/
    public static int ADD = 1;
    public static int UPDATE = 2;
    public static int DELETE = 3;
    public static int SELECT = 4;
    
    /*********返回状态   g************/
    public static int SUCCESS = 1;
    public static int ERROR = 2;
    public static int PARAMS_ERROR = 3;
    public static int EMPTY_RECORD = 4;
    
    /*********终端   t************/
    public static int PC = 1;
    public static int WX_SERVICE = 2;
    public static int WX_SUBSRIBE = 3;
    public static int APP_ANDROID = 4;
    public static int APP_IOS = 5;
    
    /*********业务系统   s************/
    public static int HUODADA = 1;
    public static int MEIDADA = 2;
    public static int APP_SIJI = 3;
    
    public static String Version = "2.5.18";
	
	/**
     * 操作
     */
    private int o;
    /**
     * 返回状态
     */
    private int g;
    /**
     * 终端 
     */
    private int t;
    /**
     * 业务系统
     */
    private int s;
    /**
     * 接口标识
     */
    private int p;
   
    /**
     *	提示信息
     */
    private String a;
    /**
     *	提示信息
     */
    private String v;
    
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	/**
	 * 分页参数（当前页currentPage，每页请求的数据showCount）
	 */
    private TreeMap<String,Object> u=new TreeMap<String, Object>();
    /**
	 * 增删改查等条件（EQ_tokenId,EQ_tokenTel,EQ_userId,EQ_lat,EQ_lon,EQ_pid）
	 */
    private TreeMap<String,Object> m=new TreeMap<String, Object>();
    
    /**
     * 集合/对象
     */
    private List<T> l=Lists.newArrayList();
    
	public int getO() {
		return o;
	}
	public void setO(int o) {
		this.o = o;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getT() {
		return t;
	}
	public void setT(int t) {
		this.t = t;
	}
	public int getS() {
		return s;
	}
	public void setS(int s) {
		this.s = s;
	}
	public int getP() {
		return p;
	}
	public void setP(int p) {
		this.p = p;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	public Map<String, Object> getU() {
		return u;
	}
	public void setU(TreeMap<String, Object> u) {
		this.u = u;
	}
	public TreeMap<String, Object> getM() {
		return m;
	}
	public void setM(TreeMap<String, Object> m) {
		this.m = m;
	}
	public List<T> getL() {
		return l;
	}
	public void setL(List<T> l) {
		this.l = l;
	}
	
}

