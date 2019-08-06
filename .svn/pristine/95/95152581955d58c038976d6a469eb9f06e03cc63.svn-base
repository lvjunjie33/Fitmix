package com.business.core.utils;

import com.business.core.constants.ApplicationConfig;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
/**
  * RSA非对称型加密的公钥和私钥
  */
public class KeyRSAUtil {

    private static final String ALGORITHM = "RSA";
    private static final Integer KEYSIZE = 2048;
    private static final String RSA = "RSA/ECB/PKCS1Padding";



    private static void generateKeyPair(String pubAddress, String priAddress) throws Exception{
        /** RSA算法要求有一个可信任的随机数源 */
        SecureRandom sr = new SecureRandom();
        /** 为RSA算法创建一个KeyPairGenerator对象 */
        KeyPairGenerator kpg = KeyPairGenerator.getInstance(ALGORITHM);
        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        kpg.initialize(KEYSIZE, sr);
        /** 生成密匙对 */
        KeyPair kp = kpg.generateKeyPair();
        /** 得到公钥 */
        Key publicKey = kp.getPublic();
        /** 得到私钥 */
        Key privateKey = kp.getPrivate();
        /** 用对象流将生成的密钥写入文件 */
        ObjectOutputStream oos1 = new ObjectOutputStream(new FileOutputStream(pubAddress));
        ObjectOutputStream oos2 = new ObjectOutputStream(new FileOutputStream(priAddress));
        oos1.writeObject(publicKey);
        oos2.writeObject(privateKey);
        /** 清空缓存，关闭文件输出流 */
        oos1.close();
        oos2.close();
    }

    public static Key loadPubKey(String path) throws IOException, ClassNotFoundException {
        /** 将文件中的公钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Key key = (Key) ois.readObject();
        ois.close();
        return key;
    }

    public static String encrypt(String source) throws Exception{
        /** 将文件中的公钥对象读出 */
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PUB_ADDRESS));
//        Key key = (Key) ois.readObject();
//        ois.close();
        Key key = ApplicationConfig.pubKey;
        /** 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] b = source.getBytes("utf-8");
        /** 执行加密操作 */
        byte[] b1 = cipher.doFinal(b);
        return Base64Util.IBase64.encode(b1);
//        BASE64Encoder encoder = new BASE64Encoder();
//        return encoder.encode(b1);
    }

    public static Key loadPriKey(String path) throws IOException, ClassNotFoundException {
        /** 将文件中的私钥对象读出 */
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
        Key key = (Key) ois.readObject();
        ois.close();
        return key;
    }

    public static String decrypt(String cryptograph) throws Exception{
        /** 将文件中的私钥对象读出 */
//        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PRI_ADDRESS));
//        Key key = (Key) ois.readObject();
//        ois.close();
        Key key = ApplicationConfig.priKey;

        /** 得到Cipher对象对已用公钥加密的数据进行RSA解密 */
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, key);
//        BASE64Decoder decoder = new BASE64Decoder();
//        byte[] b1 = decoder.decodeBuffer(cryptograph);

        byte[] b1 = Base64Util.IBase64.decode(cryptograph);

        /** 执行解密操作 */
        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    public static void main(String[] args) throws Exception {

        String hj = "W";//W T N
        generateKeyPair("D:/key/" + hj + "/publickey.keystore", "D:/key/" + hj + "/privatekey.keystore");
        ApplicationConfig.pubKey = KeyRSAUtil.loadPubKey("D:/key/" + hj + "/publickey.keystore");
        ApplicationConfig.priKey = KeyRSAUtil.loadPriKey("D:/key/" + hj + "/privatekey.keystore");
//
//
        String source = "_tp==1500540111999^^uid==1323684^^unionid==oIoQTs_12mO0gNPEVKfOYV1zjC2g^^openid==oBmGhuCuJ64liZgaHjZA3riLL2I8^^step==211";//要加密的字符串
        source = encrypt(source);//生成的密文
        System.out.println("生成的密文--->"+source);

        source = decrypt(source);//解密密文
        System.out.println("解密密文--->"+source);
    }
}
