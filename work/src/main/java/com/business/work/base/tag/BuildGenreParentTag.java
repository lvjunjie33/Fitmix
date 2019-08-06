package com.business.work.base.tag;

import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.utils.DicUtil;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2015/7/20 0020.
 */
public class BuildGenreParentTag extends AbstractTagSupport {

    private Integer[] genre;

    private String label;

    private String split;

    @Override
    protected String generateHTML() {
        StringBuffer sb = new StringBuffer();
        for (Integer value : genre) {
            if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_POP_CHILDREN).contains(value)) {
                Dictionary dictionary = DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_PARENT_GENRE, 1);
                sb.append(String.format(label, dictionary.getValue(), dictionary.getName()));
            } else if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_ROCK_CHILDREN).contains(value)) {
                Dictionary dictionary = DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_PARENT_GENRE, 2);
                sb.append(String.format(label, dictionary.getValue(), dictionary.getName()));
            } else if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_ELECTRONIC_CHILDREN).contains(value)) {
                Dictionary dictionary = DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_PARENT_GENRE, 3);
                sb.append(String.format(label, dictionary.getValue(), dictionary.getName()));
            } else if (Arrays.asList(DicConstants.MIX_GENRE_PARENT_HIP_HOP_CHILDREN).contains(4)) {
                Dictionary dictionary = DicUtil.getDictionary(DicConstants.DIC_TYPE_MIX_PARENT_GENRE, 4);
                sb.append(String.format(label, dictionary.getValue(), dictionary.getName()));
            }
            if (!StringUtils.isEmpty(split)) {
                sb.append(split);
            }
        }
        return sb.toString();
    }

    public void setSplit(String split) {
        this.split = split;
    }

    public void setGenre(Integer[] genre) {
        this.genre = genre;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
