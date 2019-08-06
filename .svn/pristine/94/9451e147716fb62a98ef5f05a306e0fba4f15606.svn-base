package com.business.live.base.view;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.business.core.constants.CodeConstants;
import com.business.core.constants.ViewConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

/**
 * 描述: JSON VIEW
 * 创建人: sin
 * 创建时间: 11-8-1 下午7:49
 */
public class MappingFastJsonView extends FastJsonJsonView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        if (!model.containsKey(ViewConstants.KEY_CODE)) {
            model.put(ViewConstants.KEY_CODE, CodeConstants.SUCCESS);
        }
        model.put(ViewConstants.KEY_K, UUID.randomUUID().toString().replace("-", ""));
        model.put(ViewConstants.KEY_SERVER_TIME, System.currentTimeMillis());
        super.renderMergedOutputModel(model, request, response);
    }

}
