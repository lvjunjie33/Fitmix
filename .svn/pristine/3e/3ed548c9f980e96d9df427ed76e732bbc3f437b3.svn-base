package com.business.core.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by sin on 2015/11/18.
 * 吊值 加密
 */
public class DickUtil {



    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private static final String MD5_CODE = "MD5";

    /**
     * 六十二进制
     */
    public static final int BINARY_SIX_TWO = 62;
    /**
     * 随即类型的最大值.
     */
    public static final int RANDOM_TYPE_MAX = 62;
    /**
     * 加密最大值
     */
    private static final int DICK_MAX_NUMBER = 14;
    /**
     * 加密值
     */
    private static final char[][] C = new char[BINARY_SIX_TWO][RANDOM_TYPE_MAX];
//    private static final Map<Integer, Map<Integer, Integer>> CM = new LinkedHashMap(BINARY_SIX_TWO);
    /**
     * 加密乱序
     */
    private static final int[][] R = new int[BINARY_SIX_TWO][DICK_MAX_NUMBER - 1];
    /**
     * 随机
     */
//    private static final int[] Z = new int[BINARY_SIX_TWO];
    /**
     * 随机Map
     */
    private static final Map<Integer, Map<Character, Integer>> Z = new LinkedHashMap(BINARY_SIX_TWO);
//    private static final Map<Integer, Integer> ZM = new LinkedHashMap(BINARY_SIX_TWO);
    /**
     * 保证里面的每个值在md5的字节范围内，即[0, 15]
     *
     * @see #doHash(String, int, char[], int, int)
     */
    private static final int[] HASH_RANDOM = new int[3];

    ///
    /// 随机值：0

    /**
     * 用户id  [0] [4]
     */
    private static final int USER_ID_LENGTH = 5;
    /**
     * 渠道信息  [5]
     */
    private static final int CHANNEL_LENGTH = 1;
    /**
     * 终端  [6]
     */
    private static final int TERMINAL_LENGTH = 1;
    /**
     * 国籍语音 (UserType) [7]
     */
    private static final int USER_TYPE_LENGTH = 1;
    /**
     * 版本长度 [8][9]
     */
    private static final int VERSION_LENGTH = 2;
    /**
     * 用户密码 [10][12]
     */
    private static final int USER_PASSWORD_LENGTH = 3;
    /**
     * 加密随机类型 [13]
     */
    private static final int RANDOM_TYPE_LENGTH = 1;


    static {
        PropertiesLoader loader = new PropertiesLoader("application-dickvalue.properties");
        String[] strC = loader.getProperty("C").split(",");
        String[] strR = loader.getProperty("R").split(",");
        String[] strZ = loader.getProperty("Z").split(",");
        String[] hashStr = loader.getProperty("HASH_RANDOM").split(",");
        int c = 0;
        for (int i = 0; i < C.length; i++) {
            Map<Character, Integer> z = new LinkedHashMap<>(RANDOM_TYPE_MAX);
            for (int j = 0; j < C[i].length; j++) {
                C[i][j] = (char)Integer.valueOf(strC[c++]).intValue();
                z.put(C[i][j], j);
            }
            Z.put(i, z);
        }
        int r = 0;
        for (int i = 0; i < R.length; i++) {
            for (int j = 0; j < R[i].length; j++) {
                R[i][j] = Integer.valueOf(strR[r++]).intValue();
            }
        }
        for (int i = 0; i < HASH_RANDOM.length; i++) {
            HASH_RANDOM[i] = Integer.valueOf(hashStr[i]);
        }
    }


    public static DickInfo encode(Integer uid, Integer channel, Integer terminal, Integer userType, Integer version, String password) {
        DickInfo dickInfo = new DickInfo();
        dickInfo.uid = uid;
        dickInfo.channel = channel;
        dickInfo.terminal = terminal;
        dickInfo.language = userType;
        dickInfo.version = version;
        dickInfo.password = password;
        return dickInfo;
    }

    public static void encode (DickInfo dickInfo) {
        char[] b = new char[DICK_MAX_NUMBER - 1];
        try {
            dickInfo.randomType = RANDOM.nextInt(RANDOM_TYPE_MAX);

            /// userId
            int[] u = toSixTwoBit(USER_ID_LENGTH, dickInfo.uid);
            fillChars(dickInfo.randomType, u, b, 0, USER_ID_LENGTH);
            /// channel
            b[5] = C[dickInfo.randomType][dickInfo.channel];
            /// terminal
            b[6] = C[dickInfo.randomType][dickInfo.terminal];
            /// Language
            b[7] = C[dickInfo.randomType][dickInfo.language];
            /// version
            int[] v = toSixTwoBit(VERSION_LENGTH, dickInfo.version);
            fillChars(dickInfo.randomType, v, b, 8, VERSION_LENGTH);

            doHash(dickInfo.password, dickInfo.randomType, b, 10, USER_PASSWORD_LENGTH);

            /// 生成加密
            char[] n = new char[DICK_MAX_NUMBER];
            for (int i = 0; i < b.length; i++) {
                n[R[dickInfo.randomType][i]] = b[i];
            }
            /// 随机
            n[n.length - 1] = C[0][dickInfo.randomType];
            dickInfo.dickValue = new String(n);
            dickInfo.passwordHash = String.valueOf(b[10]) + String.valueOf(b[11]) + String.valueOf(b[12]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean decode(DickInfo dickInfo) {
        try {
            if (!checkDick(dickInfo)){
                return false;
            }
            char[] n = dickInfo.dickValue.toCharArray();
            /// [解析随机类型]
            dickInfo.randomType = Z.get(0).get(n[n.length - 1]);
            char[] b = new char[DICK_MAX_NUMBER - 1];
            int[] s = new int[DICK_MAX_NUMBER - 1];
            for (int i = 0; i < b.length; i++) {
                b[i] = n[R[dickInfo.randomType][i]];
                s[i] = Z.get(dickInfo.randomType).get(b[i]);
            }
            /// uid
            dickInfo.uid = sixTwoBitToDec(s, 0, USER_ID_LENGTH);
            /// channel
            dickInfo.channel = sixTwoBitToDec(s, 5, CHANNEL_LENGTH);
            /// terminal
            dickInfo.terminal = sixTwoBitToDec(s, 6, TERMINAL_LENGTH);
            /// Language
            dickInfo.language = sixTwoBitToDec(s, 7, USER_TYPE_LENGTH);
            /// version
            dickInfo.version = sixTwoBitToDec(s, 8, VERSION_LENGTH);
            /// passwordHash
            dickInfo.passwordHash = String.valueOf(b[10]) + String.valueOf(b[11]) + String.valueOf(b[12]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    /**
     * 检查 dick 是否正确
     * @param dickInfo
     * @return true 正确 false 错误
     */
    public static boolean checkDick(DickInfo dickInfo) {
        if (dickInfo.dickValue.length() != DICK_MAX_NUMBER) {
            return false;
        }
        return true;
    }

    /**
     * 检查 dick 是否正确
     * @param dickVal
     * @return true 正确 false 错误
     */
    public static boolean checkDick(String dickVal){
        if (DICK_MAX_NUMBER != dickVal.length() || !dickVal.matches("[0-9 a-z A-Z]{14,14}")) {
            return false;
        }
        if (Z.containsKey(dickVal.substring(dickVal.length() -1))){
            return false;
        }
        return true;
    }


    /**
     * 将数组s的值赋值给数组b
     *
     * @param randomType 随即类型
     * @param s          数组s
     * @param b          数组b
     * @param start      数组s开始的位置
     * @param len        赋值的个数
     */
    private static void fillChars(int randomType, int[] s, char[] b, int start, int len) {
        for (int i = 0; i < len; i++) {
            b[start + i] = C[randomType][s[i]];
        }
    }

    /**
     * 将str字符串md5编码后，赋值给b
     *
     * @param str        需要md5编码的字符串
     * @param randomType 随即类型
     * @param b          字节数组
     * @param start      开始位置
     * @param len        长度
     * @throws java.io.UnsupportedEncodingException 当不支持UTF-8编码时抛出
     * @throws java.security.NoSuchAlgorithmException     当没MD5加密算法时
     */
    private static void doHash(String str, int randomType, char[] b, int start, int len) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(MD5_CODE);
        messageDigest.update(str.getBytes("UTF-8"));
        byte[] byteArray = messageDigest.digest();
        for (int i = 0; i < len; i++) {
            b[start + i] = C[randomType][byteArray[HASH_RANDOM[i]] & (BINARY_SIX_TWO - 1)];
        }
    }

    /**
     * 将用户编号转成62进制数组
     * @param l 长度
     * @param v int 值
     * @return 62进制数组
     */
    private static int[] toSixTwoBit(int l,int v) {
        int[] s = new int[l];
        for (int i = l - 1; i >= 0; i--) {
            s[i] = v % BINARY_SIX_TWO;
            v /= BINARY_SIX_TWO;
        }
        return s;
    }

    /**
     * 将62进制数组转化为十进制
     *
     * @param s     62进制数组
     * @param start 数组开始位置
     * @param len   数组长度
     * @return 十进制
     */
    private static int sixTwoBitToDec(int[] s, int start, int len) {
        int v = 0;
        for (int i = start; i < start + len; i++) {
            v = v * BINARY_SIX_TWO + s[i];
        }
        return v;
    }


    public static void main(String[] args) {

        for (int i = 0; i < 20; i++) {
            DickInfo dickInfo = new DickInfo();
            dickInfo.uid = 16278;
            dickInfo.channel = 0;
            dickInfo.terminal = 22;
            dickInfo.language = 1;
            dickInfo.version = 13;
            dickInfo.password = "123124";

            encode(dickInfo);


            DickInfo dickInfo2 = new DickInfo();
            dickInfo2.dickValue = dickInfo.dickValue;

            decode(dickInfo2);
            System.out.println(dickInfo.dickValue);
        }

        /// 数字 48 ~ 57
        /// 大写字母 65 ~ 90
        /// 小写字母 97 ~ 122
        char a = 'M';
        System.out.println('z' - '0' + 1);
    }
}
