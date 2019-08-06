package com.business.bbs.base.tag;

import com.business.core.entity.Dictionary;
import com.business.core.utils.DicUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/4/30.
 *
 * <p>字典tag 工具类</p>
 * <strong>
 * 1、指定 type and (value or values) 构建 html
 * 2、可以指定 label ，每个 label 有个两值 （显示用户看的，隐藏的 [%s 来指定 String.format() 来格式化]）
 * 3、高亮显示， 给出你需要高丽的 value ，也可以自定 label 显示
 * 4、可以指定每个 label 之间的分隔值 （split），但是最后一个回去掉分隔值
 * </strong>
 */
public class DicTypeTag extends com.business.bbs.base.tag.AbstractTagSupport {
    /**
     * dictionary type
     */
    private Integer type;
    /**
     * dictionary value
     */
    private Integer value;
    /**
     * dictionary values
     */
    private Integer[] values;
    /**
     * 默认 label
     */
    private String label;
    /**
     * 高亮 higLabel
     */
    private String higLabel;
    /**
     * 高亮 value
     */
    private Object higValue;
    /**
     * label 分隔字符
     */
    private String split = "";

    @Override
    protected String generateHTML() {
        StringBuffer html = new StringBuffer();
        if (type == null || !DicUtil.isDictionary(type)) {
            return html.toString();
        }

        // 两种情况 : value and values == null ？ 就直接取 type : 根据 type 取value or values
        if (value == null && values == null) {
            List<Dictionary> dictionaryList = DicUtil.getDictionary(type);
            for (int i = 0; i < dictionaryList.size(); i++) {
                buildHtml(html, dictionaryList.get(i));
                if (i != dictionaryList.size() - 1) {
                    html.append(split);
                }
            }
        }
        else {
            // value 判断是否单个值
            if (value != null) {
                buildHtml(html, DicUtil.getDictionary(type, value));
                html.append(split);
            }
            if (values != null) {
                // values 情况
                for (int i = 0; i < values.length; i++) {
                    buildHtml(html, DicUtil.getDictionary(type, values[i]));
                    if (i != values.length - 1) {
                        html.append(split);
                    }
                 }
            }
        }
        return  html.toString();
    }

    /**
     * 构建 html
     * @param html StringBuffer html
     * @param dic 字典值
     */
    public void buildHtml (StringBuffer html, Dictionary dic) {
        if (!StringUtils.isEmpty(higLabel) && higValue != null) {
            List<Integer> valueList =  buildHigValue();
            if (valueList.contains(dic.getValue())) {
                html.append(String.format(higLabel, dic.getValue(), dic.getName()));
            }
            else {
                html.append(String.format(label, dic.getValue(), dic.getName()));
            }
        }
        else {
            html.append(String.format(label, dic.getValue(), dic.getName()));
        }
    }

    /**
     * 构建高亮 higValue，转换成 dictionary value 值
     * @return dictionary value 值
     */
    public List<Integer> buildHigValue () {
        List<Integer> valueList = new ArrayList<>();
        if (higValue instanceof Object[]) {
            Object[] objects = (Object[]) higValue;
            for (int i = 0; i < objects.length; i++) {
                valueList.add(Integer.valueOf(objects[i].toString()));
            }
        }
        else {
            valueList.add(Integer.valueOf(higValue.toString()));
        }
        return valueList;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setHigLabel(String higLabel) {
        this.higLabel = higLabel;
    }

    public void setHigValue(Object higValue) {
        this.higValue = higValue;
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setValues(Integer[] values) {
        this.values = values;
    }
}
