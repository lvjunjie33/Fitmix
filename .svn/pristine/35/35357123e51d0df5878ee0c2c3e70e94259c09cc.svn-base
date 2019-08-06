package com.business.ugc.base.tag;

import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.utils.DicUtil;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/7/17 0017.
 */
public class MixGenreParentTag extends AbstractTagSupport {

    /**
     * dictionary genreParentType
     */
    private Integer genreParentType = DicConstants.DIC_TYPE_MIX_PARENT_GENRE;
    /**
     * dictionary genreType
     */
    private Integer genreType = DicConstants.DIC_TYPE_MIX_GENRE;
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
        if (genreParentType == null || !DicUtil.isDictionary(genreParentType)) {
            return html.toString();
        }


        // 两种情况 : value and values == null ？ 就直接取 genreParentType : 根据 genreParentType 取value or values
        if (value == null && values == null) {
            List<Dictionary> dicGenreParentList = DicUtil.getDictionary(genreParentType);
            List<Dictionary> dicGenreList = DicUtil.getDictionary(genreType);

            for (int i = 0; i < dicGenreParentList.size(); i++) {

                Dictionary dic = dicGenreParentList.get(i);
                //<br/><label>%s：</label><br/>
                html.append("<div class=\"uk-form-row uk-grid uk-grid-collapse\">");
                html.append(String.format("<div><label>%s：</label></div>", dic.getName()));
                html.append("<div class=\"uk-width-9-10 uk-grid uk-grid-collapse\">");
                switch (dic.getValue()) {
                    case 1:
                        for (Dictionary genreDic : dicGenreList) {
                            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_POP_CHILDREN).contains(genreDic.getValue())) {
                                buildHtml(html, genreDic);
                                html.append(split);
                            }
                        }
                        break;
                    case 2:
                        for (Dictionary genreDic : dicGenreList) {
                            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_ROCK_CHILDREN).contains(genreDic.getValue())) {
                                buildHtml(html, genreDic);
                            }
                        }
                        break;
                    case 3:
                        for (Dictionary genreDic : dicGenreList) {
                            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_ELECTRONIC_CHILDREN).contains(genreDic.getValue())) {
                                buildHtml(html, genreDic);
                            }
                        }
                        break;
                    case 4:
                        for (Dictionary genreDic : dicGenreList) {
                            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_HIP_HOP_CHILDREN).contains(genreDic.getValue())) {
                                buildHtml(html, genreDic);
                            }
                        }
                        break;
                    case 5:
                        for (Dictionary genreDic : dicGenreList) {
                            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_JAZZ_CHILDREN).contains(genreDic.getValue())) {
                                buildHtml(html, genreDic);
                            }
                        }
                        break;
                    case 6:
                        for (Dictionary genreDic : dicGenreList) {
                            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_CLASSICAL_CHILDREN).contains(genreDic.getValue())) {
                                buildHtml(html, genreDic);
                            }
                        }
                        break;
                    case 7:
                        for (Dictionary genreDic : dicGenreList) {
                            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_WORLD_MUSIC_CHILDREN).contains(genreDic.getValue())) {
                                buildHtml(html, genreDic);
                            }
                        }
                        break;
                }
                html.append("</div>");
                if (i != dicGenreParentList.size() - 1) {
                    html.append("<br/>");
                }
                html.append("</div>");
            }
        }

        return html.toString();
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
        html.append(split);
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


    public void setValue(Integer value) {
        this.value = value;
    }

    public void setValues(Integer[] values) {
        this.values = values;
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


}
