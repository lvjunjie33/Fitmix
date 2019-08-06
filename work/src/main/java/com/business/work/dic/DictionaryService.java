package com.business.work.dic;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Dictionary;
import com.business.core.utils.DicUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/4/29.
 */
@Service
public class DictionaryService {

    @Autowired
    private DictionaryDao dictionaryDao;

    /**
     * dictionary 添加
     * @param type 类型
     * @param value 值
     * @param name 名称
     * @param sort 排序
     */
    public void addDictionary (Integer type, Integer value, String name, String nameEn, Integer sort, String des) {
        if (!DicUtil.isDictionary(type, value)) {
            Dictionary dictionary = new Dictionary();
            dictionary.setType(type);
            dictionary.setValue(value);
            dictionary.setName(name);
            dictionary.setDes(des);
            if (StringUtils.isEmpty(nameEn)) {
                dictionary.setNameEn(name);
            }
            else {
                dictionary.setNameEn(nameEn);
            }
            dictionary.setSort(sort);
            dictionary.setNameEn(nameEn);
            dictionaryDao.insertDictionary(dictionary);
            DicUtil.init();
        }
    }

    /**
     * modify dictionary
     * @param id 编号
     * @param value 值
     * @param name 名称
     * @param sort 排序
     */
    public void modifyDictionary (Integer id, Integer value, String name, String nameEn, Integer sort, String des) {
        if (dictionaryDao.findDictionaryById(id, "id") != null) {
            dictionaryDao.updateDictionaryById(id, Update.update("value", value).set("name", name).set("nameEn", nameEn).set("sort", sort).set("des", des));
            DicUtil.init();
        }
    }

    /**
     * 删除 dictionary
     * @param id 编号
     */
    public void removeDictionary (Integer id) {
        if (dictionaryDao.findDictionaryById(id, "id") != null) {
            dictionaryDao.removeDictionaryById(id);
            DicUtil.init();
        }
    }



    ///
    /// dictionary other

    /**
     * 设置 other 信息
     * @param type 字典类型
     * @param value 字典信息
     * @param image other 信息
     */
    public String other(Integer id,Integer type, Integer value, MultipartFile image) {
        Dictionary dic = DicUtil.getDictionary(type, value);

        Map<String, Object> otherMap = new HashMap<>();
        if (null != dic.getOther()) {
            otherMap = dic.getOther();
        }

        String imagePathNew = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_MIX_DIC_BACK_IMAGE.toString(), image);

        if (dic.getOther() != null && dic.getOther().get("image") != null) {
            AliyunCenterClient.deleteFile(dic.getOther().get("image").toString());
        }

        otherMap.put("image", imagePathNew);
        dictionaryDao.updateDictionaryById(id, Update.update("other", otherMap));

        /// 刷新缓存
        DicUtil.init();
        return imagePathNew;
    }

    public void otherInfo(Integer id, String paceSpeed, String rhythm, String desc) {
        Dictionary dictionary = dictionaryDao.findDictionaryById(id);
        Map<String, Object> otherMap = null == dictionary.getOther() ? new HashMap<String, Object>() : dictionary.getOther();
        otherMap.put("paceSpeed", paceSpeed);
        otherMap.put("rhythm", rhythm);
        otherMap.put("desc", desc);
        dictionaryDao.updateDictionaryById(id, Update.update("other", otherMap));

        /// 刷新缓存
        DicUtil.init();
    }

    /**
     * 设置字典是否下发
     *
     * @param id 编号
     * @param appDisplay 是否显示
     */
    public void modifyAppDisplay(Integer id, Integer appDisplay) {
        dictionaryDao.updateDictionaryById(id, Update.update("appDisplay", appDisplay));

        /// 刷新缓存
        DicUtil.init();
    }

    /**
     * 查询所有的字典
     *
     * @return 查询所有的字典
     */
    public List<Dictionary> findAll() {
        return dictionaryDao.findAllDictionary();
    }
}
