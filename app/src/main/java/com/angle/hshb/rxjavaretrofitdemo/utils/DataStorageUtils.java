package com.angle.hshb.rxjavaretrofitdemo.utils;

import android.text.TextUtils;

import java.util.List;

/**
 * 本地存储工具类
 */
public class DataStorageUtils {
    private static SharedPreferencesUtils sp = new SharedPreferencesUtils();

    /**
     * 获取服务端版本号
     *
     * @return
     */
    public static String getHttpVersion() {
        return sp.getString("base_http_version_code", "");
    }

    /**
     * 保存服务端版本号
     *
     * @param versionCode
     */
    public static void saveHttpVersion(String versionCode) {
        if (TextUtils.isEmpty(versionCode)) {
            return;
        }
        sp.saveString("base_http_version_code", versionCode);
    }

    /**
     * 获取用户id
     *
     * @return
     */
    public static String getUserId() {
        return sp.getString("agent_user_id", "");
    }

    /**
     * 保存用户id
     *
     * @param userId
     */
    public static void saveUserId(String userId) {
        if (TextUtils.isEmpty(userId)) {
            return;
        }
        sp.saveString("agent_user_id", userId);
    }

    /**
     * 获取邀请码
     *
     * @return
     */
    public static String getInvestCode() {
        return sp.getString("agent_invest_code", "");
    }

    /**
     * 存储邀请码
     *
     * @param investCode
     */
    public static void saveIncestCode(String investCode) {
        if (TextUtils.isEmpty(investCode)) {
            return;
        }
        sp.saveString("agent_invest_code", investCode);
    }

    /**
     * 获取token码
     *
     * @return
     */
    public static String getToken() {
        return sp.getString("app_token", "");
    }

    /**
     * 保存token码
     *
     * @param token
     */
    public static void saveToken(String token) {
        if (TextUtils.isEmpty(token)) {
            return;
        }
        sp.saveString("app_token", token);
    }

    /**
     * 清空历史搜索
     */
    public static void clearHistory() {
        sp.saveString("history_data", "");
    }

    /**
     * 保存用户名
     *
     * @param userName
     */
    public static void saveUserName(String userName) {
        if (TextUtils.isEmpty(userName)) {
            return;
        }
        sp.saveString("agent_user_name", userName);
    }

    /**
     * 获取用户名
     *
     * @return
     */
    public static String getUserName() {
        return sp.getString("agent_user_name", "");
    }

    /**
     * 头像
     *
     * @param userAvatar
     */
    public static void saveUserAvatar(String userAvatar) {
//        if (TextUtils.isEmpty(userAvatar)) {
//            return;
//        }
        sp.saveString("agent_user_avatar", userAvatar);
    }

    public static String getUserAvatar() {
        return sp.getString("agent_user_avatar", "");
    }

    /**
     * 部门
     *
     * @param userDepartment
     */
    public static void saveUserDepartment(String userDepartment) {
        if (TextUtils.isEmpty(userDepartment)) {
            return;
        }
        sp.saveString("agent_user_department", userDepartment);
    }

    public static String getUserDepartment() {
        return sp.getString("agent_user_department", "");
    }

    /**
     * 职级
     *
     * @param userRank
     */
    public static void saveUserRank(String userRank) {
        if (TextUtils.isEmpty(userRank)) {
            return;
        }
        sp.saveString("agent_user_rank", userRank);
    }

    public static String getUserRank() {
        return sp.getString("agent_user_rank", "");
    }

    public static void setHouseImg(String houseImg) {
        if (TextUtils.isEmpty(houseImg)) {
            return;
        }
        sp.saveString("house_img", houseImg);
    }

    public static String getHouseImg() {
        return sp.getString("house_img", "");
    }

    /**
     * 店名
     *
     * @param shopName
     */
    public static void saveUserShopName(String shopName) {
//        if (TextUtils.isEmpty(shopName)) {
//            return;
//        }
        sp.saveString("agent_user_shop_name", shopName);
    }

    public static String getUserShopName() {
        return sp.getString("agent_user_shop_name", "");
    }

    /**
     * 最后一次登录时间
     *
     * @param lastTime
     */
    public static void saveUserLastTime(String lastTime) {
        if (TextUtils.isEmpty(lastTime)) {
            return;
        }
        sp.saveString("agent_user_last_time", lastTime);
    }

    public static String getUserLastTime() {
        return sp.getString("agent_user_last_time", "");
    }



    /**
     * 保存最近添加过的驻守地点
     *
     * @param list
     */
    public static void saveHistoryDefendAddress(List<String> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        sp.saveObject("DEFEND_HISTORY_KEY_WORD", list);
    }

    /**
     * 获取最近添加过的驻守地点
     *
     * @return
     */
    public static List<String> getHistoryDefendAddress() {
        return (List<String>) sp.getObject("DEFEND_HISTORY_KEY_WORD", null);
    }

    /**
     * @param userType 用户类型
     */
    public static void saveUserType(int userType) {
        sp.saveInt("USER_TYPE", userType);
    }

    /**
     * 获取用户类型
     *
     * @return 用户类型
     */
    public static int getUserType() {
        return sp.getInt("USER_TYPE", 0);
    }

}
