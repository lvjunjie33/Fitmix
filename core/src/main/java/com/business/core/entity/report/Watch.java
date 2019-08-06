package com.business.core.entity.report;

import com.business.core.annotation.MongoDocumentDB;
import com.business.core.entity.IncIdEntity;
import com.business.core.mongo.MongoType;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by edward on 2017/6/28.
 */
@Document(collection = "Watch")
@MongoDocumentDB(MongoType.BUSINESS_LOG)
public class Watch extends IncIdEntity<Integer>{

    /**
     * 标题
     */
    private String title;
    /**
     * 测试时间
     */
    private Long testTime;
    /**
     * 添加时间
     */
    private Long addTime;
    /**
     * 备注
     */
    private String des;
    /**
     * 应答，握手
     */
    private String answer;
    /**
     * 手表 ChipId号
     * AT+IRON_CHIPID=? 数据
     * AT+ IRON_CHIPID=ID 号
     */
    private String test1;
    /**
     * FM 屏幕
     *
     * 查询 AT+IRON_LCD=RED 屏幕红色
     * AT+IRON_LCD=GREEN 屏幕绿色
     * AT+IRON_LCD=BLUE 屏幕蓝色
     * AT+IRON_LCD=WHITE 屏幕白色
     * AT+IRON_LCD=BLACK 屏幕黑色
     * AT+IRON_LCD=CALIBRATION 屏幕校准
     * AT+IRON_LCD=? 查询人工验屏结果
     */
    private String test2;
    /**
     * FM 按键
     *
     * 查询 AT+IRON_KEYPAD=?
     * 数据 AT+IRON_KEYPAD=测试结果
     */
    private String test3;
    /**
     * FM 触摸屏
     *
     * 查询 AT+IRON_TP=?
     * 数据 AT+IRON_TP=测试结果
     */
    private String test4;
    /**
     * FM 马达
     *
     * AT+IRON_VIBRAT 震动
     * 查询 AT+IRON_VIBRATE=?
     * 数据 AT+IRON_VIBRATE =测试结果
     */
    private String test5;
    /**
     * FM 蜂鸣器
     *
     * AT+IRON_BUZZER 蜂鸣
     * 查询 AT+IRON_BUZZER=?
     * 数据 AT+IRON_BUZZER=测试结果
     */
    private String test6;
    /**
     * FM 背光
     *
     * AT+IRON_BL=1 一级背光
     * AT+IRON_BL=2 二级背光
     * AT+IRON_BL=3 三级背光
     * AT+IRON_BL=4 四级背光
     * AT+IRON_BL=5 吴级背光
     * 查询 AT+IRON_BL=?
     * 数据 AT+IRON_BL=测试结果
     */
    private String test7;
    /**
     * FM GPS
     *
     * 查询 AT+IRON_GPS=?
     * 数据 AT+IRON_GPS=经度,纬度,海拔,精度
     */
    private String test8;
    /**
     * FM GSENSOR
     *
     * 查询 AT+IRON_GSENSOR=?
     * 数据 AT+IRON_GSENSOR=x数据,y数据,z数据,G数据
     */
    private String test9;
    /**
     * FM 心率
     *
     * 查询 AT+IRON_HR=?
     * 数据 AT+IRON_HR=心率数据值
     */
    private String test10;
    /**
     * FM 指南针
     *
     * 查询 AT+IRON_COMPASS=?
     * 数据 AT+IRON_COMPASS=方位角
     */
    private String test11;
    /**
     * FM 气温
     *
     * 查询 AT+IRON_TEMPRATURE=?
     * 数据 AT+IRON_TEMPRATURE=气温值
     */
    private String test12;
    /**
     * FM 气压
     *
     * 查询 AT+IRON_PRESSURE=?
     * 数据 AT+IRON_PRESSURE=气压值
     */
    private String test13;
    /**
     * FM 蓝牙
     *
     * 查询 AT+IRON_BT=?
     * 数据 AT+IRON_BT=蓝牙信号强度
     */
    private String test14;
    /**
     * FM 测试
     *
     * 数据 AT+IRON_TEST=数据(0=开始,1=结束)
     */
    private String test15;
    /**
     * FM 湿度
     *
     * 查询 AT+IRON_HUMITY=?
     * 数据 AT+IRON_HUMITY=湿度
     */
    private String test16;
    /**
     * FM 陀螺仪
     *
     * 查询 AT+IRON_GYRO=?
     * 数据 AT+IRON_GYRO=数据
     */
    private String test17;
    /**
     * 注意事项
     * Sensor 数据都是不停发送的
     * 测试结果0=失败,1=成功
     */
    private String test18;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTestTime() {
        return testTime;
    }

    public void setTestTime(Long testTime) {
        this.testTime = testTime;
    }

    public Long getAddTime() {
        return addTime;
    }

    public void setAddTime(Long addTime) {
        this.addTime = addTime;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTest1() {
        return test1;
    }

    public void setTest1(String test1) {
        this.test1 = test1;
    }

    public String getTest2() {
        return test2;
    }

    public void setTest2(String test2) {
        this.test2 = test2;
    }

    public String getTest3() {
        return test3;
    }

    public void setTest3(String test3) {
        this.test3 = test3;
    }

    public String getTest4() {
        return test4;
    }

    public void setTest4(String test4) {
        this.test4 = test4;
    }

    public String getTest5() {
        return test5;
    }

    public void setTest5(String test5) {
        this.test5 = test5;
    }

    public String getTest6() {
        return test6;
    }

    public void setTest6(String test6) {
        this.test6 = test6;
    }

    public String getTest7() {
        return test7;
    }

    public void setTest7(String test7) {
        this.test7 = test7;
    }

    public String getTest8() {
        return test8;
    }

    public void setTest8(String test8) {
        this.test8 = test8;
    }

    public String getTest9() {
        return test9;
    }

    public void setTest9(String test9) {
        this.test9 = test9;
    }

    public String getTest10() {
        return test10;
    }

    public void setTest10(String test10) {
        this.test10 = test10;
    }

    public String getTest11() {
        return test11;
    }

    public void setTest11(String test11) {
        this.test11 = test11;
    }

    public String getTest12() {
        return test12;
    }

    public void setTest12(String test12) {
        this.test12 = test12;
    }

    public String getTest13() {
        return test13;
    }

    public void setTest13(String test13) {
        this.test13 = test13;
    }

    public String getTest14() {
        return test14;
    }

    public void setTest14(String test14) {
        this.test14 = test14;
    }

    public String getTest15() {
        return test15;
    }

    public void setTest15(String test15) {
        this.test15 = test15;
    }

    public String getTest16() {
        return test16;
    }

    public void setTest16(String test16) {
        this.test16 = test16;
    }

    public String getTest17() {
        return test17;
    }

    public void setTest17(String test17) {
        this.test17 = test17;
    }

    public String getTest18() {
        return test18;
    }

    public void setTest18(String test18) {
        this.test18 = test18;
    }
}
