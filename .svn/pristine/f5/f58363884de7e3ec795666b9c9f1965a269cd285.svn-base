package com.business.core.utils;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.business.core.constants.ApplicationConfig;
import sun.misc.BASE64Decoder;

/**
 * Created by edward on 2017/7/19.
 */
public class Base64Util {

    private static char[] base64EncodeChars = new char[] {
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f',
            'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/'};

    private static byte[] base64DecodeChars = new byte[]{
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
            52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
            -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14,
            15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
            -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
            41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1};

    public static String getBASE64(byte[] b) {
        String s = null;
        if (b != null) {
            s = new sun.misc.BASE64Encoder().encode(b);
        }
        return s;
    }

    public static byte[] getFromBASE64(String s) {
        byte[] b = null;
        if (s != null) {
            BASE64Decoder decoder = new BASE64Decoder();
            try {
                b = decoder.decodeBuffer(s);
                return b;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return b;
    }

    /**
     * （二）自己写加密算法
     */
    public static class IBase64 {

        public static String encode(byte[] data) {
            StringBuffer sb = new StringBuffer();
            int len = data.length;
            int i = 0;
            int b1, b2, b3;
            while (i < len) {
                b1 = data[i++] & 0xff;
                if (i == len) {
                    sb.append(base64EncodeChars[b1 >>> 2]);
                    sb.append(base64EncodeChars[(b1 & 0x3) << 4]);
                    sb.append("==");
                    break;
                }
                b2 = data[i++] & 0xff;
                if (i == len) {
                    sb.append(base64EncodeChars[b1 >>> 2]);
                    sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                    sb.append(base64EncodeChars[(b2 & 0x0f) << 2]);
                    sb.append("=");
                    break;
                }
                b3 = data[i++] & 0xff;
                sb.append(base64EncodeChars[b1 >>> 2]);
                sb.append(base64EncodeChars[((b1 & 0x03) << 4) | ((b2 & 0xf0) >>> 4)]);
                sb.append(base64EncodeChars[((b2 & 0x0f) << 2) | ((b3 & 0xc0) >>> 6)]);
                sb.append(base64EncodeChars[b3 & 0x3f]);
            }
            return sb.toString();
        }


        public static byte[] decode(String str) throws UnsupportedEncodingException {
            StringBuffer sb = new StringBuffer();
            byte[] data = str.getBytes("US-ASCII");
            int len = data.length;
            int i = 0;
            int b1, b2, b3, b4;
            while (i < len) {
                do {
                    b1 = base64DecodeChars[data[i++]];
                } while (i < len && b1 == -1);

                if (b1 == -1) break;

                do {
                    b2 = base64DecodeChars[data[i++]];
                } while (i < len && b2 == -1);

                if (b2 == -1) break;
                sb.append((char) ((b1 << 2) | ((b2 & 0x30) >>> 4)));

                do {
                    b3 = data[i++];
                    if (b3 == 61) return sb.toString().getBytes("iso8859-1");
                    b3 = base64DecodeChars[b3];
                } while (i < len && b3 == -1);

                if (b3 == -1) break;
                sb.append((char) (((b2 & 0x0f) << 4) | ((b3 & 0x3c) >>> 2)));

                do {
                    b4 = data[i++];
                    if (b4 == 61) return sb.toString().getBytes("iso8859-1");
                    b4 = base64DecodeChars[b4];
                } while (i < len && b4 == -1);

                if (b4 == -1) break;
                sb.append((char) (((b3 & 0x03) << 6) | b4));
            }
            return sb.toString().getBytes("iso8859-1");
        }
    }


    public static void main(String[] args) throws Exception {

        String source = "_tp==1500540111999^^uid==27^^unionid==oIoQTs_12mO0gNPEVKfOYV1zjC2g^^openid==oBmGhuCuJ64liZgaHjZA3riLL2I8^^step==211";//要加密的字符串

        ApplicationConfig.pubKey = KeyRSAUtil.loadPubKey("D:/key/W/publickey.keystore");
        ApplicationConfig.priKey = KeyRSAUtil.loadPriKey("D:/key/W/privatekey.keystore");
//
//        source = KeyRSAUtil.encrypt(source);//生成的密文
//        System.out.println("生成的密文--->" + source);
//
//
//        System.out.println("加密前：" + source);
//        source = Base64Util.IBase64.encode(source.getBytes());
//        System.out.println("加密后：" + source);

//        System.out.println("http://localhost:8080/wx-run/set/step.htm?parameter=" + source);

        source = "YWdxbnhGNDFUd1NaR2VLZ0EwK0lOUlBtQ3lYdG5nazBLWlF0bGlUTkJyMWVob2pNc0dRMUFsNjdudGQ5Sy85N0lLYWJRQTdhbHFnR0ZITzVkMGYyK1Uyd3cwNjNUVnViWmhyR0xOdFd1elBydkJmWFNhdnNyb09Ccnd1cVc2clQ1RTcwdmd6d2dGYS9TV0ZQRTM5U2lUUUM4MTJhUU5tSllQcEh3dDNhM09FPQ==";

        source = new String(Base64Util.IBase64.decode(source));
        System.out.println("解密后：" + source);

        source = KeyRSAUtil.decrypt(source);//解密密文
        System.out.println("解密密文--->" + source);

        Map<String, String> s = decodeParameter(source);
        System.out.println("");

    }

    protected static Map<String, String> decodeParameter(String parameter) throws Exception {
        String[] parent = parameter.split("\\^\\^");
        if (parent.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, String> maps = new HashMap<>();
        for (String node : parent) {
            if(!node.contains("==")){
                continue;
            }
            String[] keyVal = node.split("==");
            if (keyVal.length == 0) {
                continue;
            }
            if (keyVal.length > 1) {
                maps.put(keyVal[0], keyVal[1]);
            } else {
                maps.put(keyVal[0], null);
            }
        }
        return maps;
    }

}

