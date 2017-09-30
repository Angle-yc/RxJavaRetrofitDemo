package com.angle.hshb.rxjavaretrofitdemo.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 本地存储基类
 *
 */
public class SharedPreferencesUtils {
    private SharedPreferences.Editor mEditor;
    private SharedPreferences mSharedPreferences;

    public SharedPreferencesUtils(){
        mSharedPreferences= BaseAppUtil.getAppContext()
                .getSharedPreferences(BaseAppUtil.getAppContext().getPackageName()
                        , Context.MODE_PRIVATE);
        mEditor=mSharedPreferences.edit();
    }

    /**
     * 存储boolean类型的数据
     * @param key
     * @param value
     */
    public void saveBoolean(String key, boolean value){
        mEditor.putBoolean(key,value).commit();
    }

    /**
     * 获取boolean类型的数据
     * @param key
     * @param defValue
     * @return
     */
    public boolean getBoolean(String key, boolean defValue){
        return mSharedPreferences.getBoolean(key,defValue);
    }

    /**
     * 存储int类型的数据
     * @param key
     * @param value
     */
    public void saveInt(String key, int value){
        mEditor.putInt(key,value).commit();
    }

    /**
     * 获取int类型的数据
     * @param key
     * @param defValue
     * @return
     */
    public int getInt(String key, int defValue){
        return mSharedPreferences.getInt(key,defValue);
    }

    /**
     * 存储float类型的数据
     * @param key
     * @param value
     */
    public void saveFloat(String key, float value){
        mEditor.putFloat(key,value).commit();
    }

    /**
     * 获取float类型的数据
     * @param key
     * @param defValue
     * @return
     */
    public float getFloat(String key, float defValue){
        return mSharedPreferences.getFloat(key,defValue);
    }

    /**
     * 存储String类型的数据
     * @param key
     * @param value
     */
    public void saveString(String key, String value){
        mEditor.putString(key,value).commit();
    }

    /**
     * 获取String类型的数据
     * @param key
     * @param defValue
     * @return
     */
    public String getString(String key, String defValue){
        return mSharedPreferences.getString(key,defValue);
    }
    /**
     * 存储long类型的数据
     * @param key
     * @param value
     */
    public void saveLong(String key, long value){
        mEditor.putLong(key,value).commit();
    }

    /**
     * 获取long类型的数据
     * @param key
     * @param defValue
     * @return
     */
    public long getLong(String key, long defValue){
        return mSharedPreferences.getLong(key,defValue);
    }

    /**
     * 存储object对象，object对象必须实现Serializable接口
     * @param key
     * @param object
     */
    public void saveObject(String key, Object object){
        if(object instanceof Serializable ==false){
            throw  new RuntimeException("Class object where the class needs to be implemented Serializable");
        }
        ByteArrayOutputStream baos=null;
        ObjectOutputStream oos=null;
        try {
            baos=new ByteArrayOutputStream();
            oos=new ObjectOutputStream(baos);
            oos.writeObject(object);
            String strBase64=new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
            saveString(key,strBase64);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                oos.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取object对象
     * @param key
     * @param defObject
     * @return 获取成功则返回存储的object，否则返回默认值
     */
    public Object getObject(String key, Object defObject){
        Object object=null;
        String strBase64=getString(key,"");
        if(TextUtils.isEmpty(strBase64)){
            return defObject;
        }
        byte[] base64= Base64.decode(strBase64.getBytes(), Base64.DEFAULT);
        ByteArrayInputStream bais=null;
        ObjectInputStream ots=null;
        try {
            bais=new ByteArrayInputStream(base64);
            ots=new ObjectInputStream(bais);
            object=ots.readObject();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ots.close();
                bais.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return defObject;
    }
}
