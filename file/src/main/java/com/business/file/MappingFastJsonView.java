package com.business.file;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述: JSON VIEW
 * 创建人: sin
 * 创建时间: 11-8-1 下午7:49
 */
public class MappingFastJsonView extends FastJsonJsonView {

    public MappingFastJsonView() {
        setFeatures(SerializerFeature.DisableCircularReferenceDetect);
    }

    public static final int SUCCESS = 0;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        if (!model.containsKey(ViewConstants.KEY_CODE)) {
            model.put(ViewConstants.KEY_CODE, SUCCESS);
        }
        super.renderMergedOutputModel(model, request, response);
    }

}
