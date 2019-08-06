package com.business.work.base.view;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.business.core.constants.CodeConstants;
import com.business.core.constants.ViewConstants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 描述: JSON VIEW
 * 创建人: jeff
 * 创建时间: 11-8-1 下午7:49
 */
public class MappingFastJsonView extends FastJsonJsonView {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {
        //logger.info(model);
        if (!model.containsKey(ViewConstants.KEY_CODE)) {
            model.put(ViewConstants.KEY_CODE, CodeConstants.SUCCESS);
        }
        super.renderMergedOutputModel(model, request, response);
    }

}
