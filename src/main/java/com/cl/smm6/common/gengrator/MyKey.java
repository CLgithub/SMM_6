package com.cl.smm6.common.gengrator;

/**
 * Created by chenlei on 2017/2/6.
 */
public class MyKey {

    private String className;
    private String methodName;
    private String params;

    public MyKey(String className, String methodName, String params) {
        this.className = className;
        this.methodName = methodName;
        this.params = params;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        MyKey myKey = (MyKey) o;
//        boolean b=false;
//        if(className!=null){
//            if(className.equals(myKey.className)){  //如果是同一个方法
//                if(methodName.startsWith("add")
//                    ||methodName.startsWith("save")
//                    ||methodName.startsWith("update")
//                    ||methodName.startsWith("delete")
//                    ||methodName.startsWith("new")
//                    ||methodName.startsWith("insert")){       //如果是这些方法,直接命中,清除改类的下面的方法的缓存
//                    b=true;
//                }else {         //如果不是那些更新方法
//                    if (methodName != null ? !methodName.equals(myKey.methodName) : myKey.methodName != null) {     //如果方法名不同
//                        b=false;
//                    }else {
//                        if (params != null ? !params.equals(myKey.params) : myKey.params == null) {     //如果参数不同
//                            b=false;
//                        }else {
//                            b=true;
//                        }
//                    }
//                }
//            }else {
//                b=false;
//            }
//        }else {
//            b=false;
//        }
//        System.out.println(b+"_"+this.toString()+"——比较——"+myKey.toString());
//        return b;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyKey myKey = (MyKey) o;

        if (className != null ? !className.equals(myKey.className) : myKey.className != null) return false;
        if (methodName != null ? !methodName.equals(myKey.methodName) : myKey.methodName != null) return false;
        return params != null ? params.equals(myKey.params) : myKey.params == null;
    }

    @Override
    public int hashCode() {
        int result = className != null ? className.hashCode() : 0;
        result = 31 * result + (methodName != null ? methodName.hashCode() : 0);
        result = 31 * result + (params != null ? params.hashCode() : 0);
        return result;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "MyKey{" +
                className  +
                "," + methodName +
                ", " + params +
                '}';
    }
}

