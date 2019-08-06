package com.business.core.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/12/2.
 */
public class XmlUtil {

    private static SAXReader reader = new SAXReader();

    /**
     * 将集 Map 转成 xml 文件格式字符串
     * @param paraMap  集合信息
     * @return xml 字符串
     */
    public static String toXml(Map<String, String> paraMap) {
        String xmlStr = "<xml>";
        for (Map.Entry<String, String> entry : paraMap.entrySet()) {
            xmlStr += "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">";
        }
        xmlStr += "</xml>";
        return xmlStr;
    }

    /**
     * 将xml 转换成 map
     * @param xml xml字符串
     * @return map 集合对应的 element
     */
    public static Map<String, Element> parseMap(String xml) {
        return parseMap(new ByteArrayInputStream(xml.getBytes()));
    }

    /**
     * 将xml 转换成 map
     * @param inputStream 输入流转
     * @return map 集合对应的 element
     */
    public static Map<String, Element> parseMap(InputStream inputStream) {
        //读取输入流
        Map<String, Element> elementMap = new HashMap<>();
        try {
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();
            for (Element element : elementList) {
                elementMap.put(element.getName(), element);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return elementMap;
    }
}
