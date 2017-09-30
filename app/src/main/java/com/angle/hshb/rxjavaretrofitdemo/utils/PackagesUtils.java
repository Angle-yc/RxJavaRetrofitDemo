package com.angle.hshb.rxjavaretrofitdemo.utils;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;


/**
 * 应用程序相关工具类
 */
public class PackagesUtils {
    /**
     * 获取当前程序路径
     *
     * @return
     */
    public static String getAppDirPath() {
        return BaseAppUtil.getAppContext().getFilesDir().getAbsolutePath();
    }

    /**
     * 获取程序安装包路径
     * @return
     */
    public static String getAppPackageResourcePath(){
        return BaseAppUtil.getAppContext().getPackageResourcePath();
    }

    /**
     * 获取应用包名字
     * @return
     */
    public static String getAppPackgeName(){
        return BaseAppUtil.getAppContext().getPackageName();
    }

    /**
     * 获取应用版本名称
     * @return
     */
    public static String getAppVersionName(){
        String versionName="";
        if(BaseAppUtil.getAppContext()==null){
            return versionName;
        }
        try {
            PackageManager packageManager= BaseAppUtil.getAppContext().getPackageManager();
            PackageInfo packageInfo=packageManager.getPackageInfo(getAppPackgeName(),0);
            versionName=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 获取应用版本号
     * @return
     */
    public static int getAppVersionCode(){
        int versionCode=0;
        if(BaseAppUtil.getAppContext()==null){
            return versionCode;
        }
        try {
            PackageManager packageManager= BaseAppUtil.getAppContext().getPackageManager();
            PackageInfo packageInfo=packageManager.getPackageInfo(getAppPackgeName(),0);
            versionCode=packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     *
     * 检查包名所在的程序是否有某项权限
     *
     * @param permName
     *            权限名称
     * @param pkgName
     *            程序所在的包名
     * @return
     */
    public static boolean checkPermission(String permName, String pkgName) {
        PackageManager pm = BaseAppUtil.getAppContext()
                .getPackageManager();
        try {
            boolean isHave = (PackageManager.PERMISSION_GRANTED == pm
                    .checkPermission(permName, pkgName));
            return isHave;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 检查当前应用是否有某项权限
     *
     * @param permName
     * @return
     */
    public static boolean checkPermission(String permName) {
        return checkPermission(permName, BaseAppUtil.getAppContext()
                .getPackageName());
    }


    /**
     * 启动到app详情界面
     *
     * @param appPkg
     *            App的包名
     * @param marketPkg
     *            应用商店包名 ,如果为""则由系统弹出应用商店列表供用户选择,否则调转到目标市场的应用详情界面，某些应用商店可能会失败
     */
    public static void goToAppDetailInMarket(String appPkg, String marketPkg) {
        try {
            if (TextUtils.isEmpty(appPkg))
                return;
            Uri uri = Uri.parse("market://details?id=" + appPkg);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            if (!TextUtils.isEmpty(marketPkg))
                intent.setPackage(marketPkg);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            BaseAppUtil.getAppContext().startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
