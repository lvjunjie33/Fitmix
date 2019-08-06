package com.business.work.file;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.file.File;
import com.business.core.entity.file.Voice;
import com.business.core.mongo.BaseMongoDaoSupport;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.*;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by edward on 2016/8/17.
 */
@Repository
public class FileDao extends BaseMongoDaoSupport {

    /**
     * 保存文件
     *
     * @param file 文件
     */
    public void saveFile(File file) {
        insertToMongo(file);
    }

    /**
     * 通过主键修改文件
     * @param id 文件编号
     * @param update 修改的内容
     */
    public void updateFile(Long id, Update update) {
        updateEntityFieldsById(File.class, id, update);
    }

    /**
     * 文件分页
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void page(Page<File> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("title")) {
            criteria.and("title").is(filter.get("title"));
        }
        if (filter.containsKey("fileType")) {
            criteria.and("fileType").is(filter.get("fileType"));
        }
        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }
        if (filter.containsKey("voiceId")) {
            criteria.and("other.voiceId").is(filter.get("voiceId"));
        }
        if (filter.containsKey("uid")) {
            criteria.and("uid").is(filter.get("uid"));
        }
        if (filter.containsKey("chipID")) {
            criteria.and("other.chipID").is(filter.get("chipID"));
        }
        if (filter.containsKey("beginTime")) {
            if (filter.containsKey("endTime")) {
                criteria.and("addTime").gte(((Date)filter.get("beginTime")).getTime()).lte(((Date)filter.get("endTime")).getTime());
            } else {
                criteria.and("addTime").gte(((Date)filter.get("beginTime")).getTime());
            }
        } else if (filter.containsKey("endTime")) {
            criteria.and("addTime").lte(((Date)filter.get("endTime")).getTime());
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "title", "addTime"));
        findEntityPage(File.class, page, query, fields);
    }

    /**
     * 文件分页Api
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void getWatchVersion(Page<File> page,String lan, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = Criteria.where("fileType").in(File.FILE_TYPE_WATCH,File.FILE_TYPE_WATCH_MODE_ABO,File.FILE_TYPE_WATCH_MODE_HEARTRATE).and("other.isNew").is(Constants.IS_NEW_TRUE);
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC,"addTime"));
        findEntityPage(File.class, page, query, fields);
        if(lan!=null){
            //2018-09-06循环去除数据，重新组装实体，IronCloud临时判断，后续阿波罗升级和心率升级组件加上英文注释，统一去掉判断
            for(int i=0;i<page.getResult().size();i++) {
                if (lan.equals(page.getResult().get(i).getDes_lan()) && "IronCloud".equals(page.getResult().get(i).getOther().get("serial"))) {
                    File files = new File();
                    files.setDes(page.getResult().get(i).getDes());
                    files.setId(page.getResult().get(i).getId());
                    files.setFileDesUrl(page.getResult().get(i).getFileDesUrl());
                    files.setFileLink(page.getResult().get(i).getFileLink());
                    files.setIsForce(page.getResult().get(i).getIsForce());
                    files.setTitle(page.getResult().get(i).getTitle());
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("number", page.getResult().get(i).getOther().get("number"));
                    map.put("serial", page.getResult().get(i).getOther().get("serial"));
                    map.put("version", page.getResult().get(i).getOther().get("version"));
                    map.put("isNew", page.getResult().get(i).getOther().get("isNew"));
                    files.setOther(map);
                    page.getResult().remove(i);
                    page.getResult().add(files);
                }
                if (lan.equals(page.getResult().get(i).getDes_lan_en()) && "IronCloud".equals(page.getResult().get(i).getOther().get("serial"))) {
                    File files = new File();
                    files.setDes(page.getResult().get(i).getDes_en());
                    files.setId(page.getResult().get(i).getId());
                    files.setFileDesUrl(page.getResult().get(i).getFileDesUrl());
                    files.setFileLink(page.getResult().get(i).getFileLink());
                    files.setIsForce(page.getResult().get(i).getIsForce());
                    files.setTitle(page.getResult().get(i).getTitle());
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("number", page.getResult().get(i).getOther().get("number"));
                    map.put("serial", page.getResult().get(i).getOther().get("serial"));
                    map.put("version", page.getResult().get(i).getOther().get("version"));
                    map.put("isNew", page.getResult().get(i).getOther().get("isNew"));
                    files.setOther(map);
                    page.getResult().remove(i);
                    page.getResult().add(files);
                }
             }
        }

    }


    /**
     * 文件分页Api
     *
     * @param page 分页对象
     * @param fields 查询的字段
     */
    public void pageApi(Page<File> page, String...fields) {
        Map<String, Object> filter = page.getFilter();
        Criteria criteria = new Criteria();
        if (filter.containsKey("title")) {
            criteria.and("title").is(filter.get("title"));
        }
        if (filter.containsKey("fileType")) {
            criteria.and("fileType").in((List<Integer>)filter.get("fileType"));
        }
        if (filter.containsKey("status")) {
            criteria.and("status").is(filter.get("status"));
        }
        if (filter.containsKey("voiceId")) {
            criteria.and("other.voiceId").is(filter.get("voiceId"));
        }
        criteria.and("other.isNew").is(1);
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "title", "addTime"));
        findEntityPage(File.class, page, query, fields);
    }



    /**
     * 查询指定的文件
     *
     * @param id
     * d 文件编号
     * @param fields 查询的字段
     */
    public File findFile(Long id, String...fields) {
        return findEntityById(File.class, id, fields);
    }

    /**
     * 查询指定的文件
     *
     * @param id
     * @param lan
     * d 文件编号
     * @param fields 查询的字段
     */
    public File findFileAjax(Long id,String lan, String...fields) {
        Criteria criteria = new Criteria();
        if("zh".equals(lan)){
            criteria.and("_id").is(id).and("des_lan").is(lan);
        }
        if("en".equals(lan)){
            criteria.and("_id").is(id).and("des_lan_en").is(lan);
        }
        Query query = new Query(criteria);
        includeFields(query, fields);
        return getRoutingMongoOps().findOne(query, File.class);
    }

    /**
     * 修改文件
     */
    public void modify(Long id, Update update) {
        getRoutingMongoOps().updateFirst(Query.query(Criteria.where("id").is(id)), update, File.class);
    }

    /**
     * 删除文件
     * */
    public void deleteFile(Long id) {
        getRoutingMongoOps().remove(Query.query(Criteria.where("id").is(id)), File.class);
    }
    /**
     * 耳机取消设置 最新包
     *
     * @param serial 序列号
     */
    public void unsetIsNewBySerial(String serial) {
        getRoutingMongoOps().updateMulti(Query.query(Criteria.where("other.serial").is(serial)), new Update().unset("other.isNew"), File.class);
    }

    /**
     * 查询语音清单
     */
    public List<Voice> voiceList() {
        return getRoutingMongoOps().findAll(Voice.class);
    }

    /**
     * 保存语音文件
     */
    public void voiceAdd(Voice voice) {
        insertToMongo(voice);
    }

    /**
     * 查询语音清单
     *
     * @param id 编号
     */
    public Voice findVoice(Integer id, String...fields) {
        return findEntityById(Voice.class, id, fields);
    }

    /**
     * 修改语音包清单
     *
     * @param id 编号
     * @param update 修改的内容
     */
    public void updateVoice(Integer id, Update update) {
        updateEntityFieldsById(Voice.class, id, update);
    }
}
