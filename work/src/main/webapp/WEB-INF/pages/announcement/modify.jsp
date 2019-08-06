<%--
  Created by IntelliJ IDEA.
  User: edward
  Date: 2017/9/25
  Time: 12:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/pages/common/nav.jsp" %>
<div>
    <div>

        <c:choose>
            <c:when test="${announcement.type == 1}">
                <div class="uk-form-row">
                    <h1>修改系统公告</h1>
                    <form action="/announcement/modify.json" method="post" class="uk-form" isform>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="file" name="img" title = "upload file" placeholder = "公告图">
                        </div>
                        <div class="uk-form-row">
                            <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull value="${announcement.displayNum}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" value="<lch:parse-date time='${announcement.bTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" value="<lch:parse-date time='${announcement.eTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="公告描述" notnull>${announcement.desc}</textarea>
                        </div>
                        <div class="uk-form-row">
                            <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${announcement.status}">
                                <option value="0">未发布</option>
                                <option value="1">发布</option>
                            </select>
                        </div>
                        <div class="uk-form-row uk-text-right">
                            <input type="hidden" name="id" value="${announcement.id}" />
                            <input type="hidden" name = "type" value="1" />
                            <button class="uk-button uk-button-primary" id="role-add-submit-1" target="role-add-form">add</button>
                            <button class="uk-button uk-modal-close">cancel</button>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:when test="${announcement.type == 2}">
                <div class="uk-form-row">
                    <h1>修改广告外链公告</h1>
                    <form action="/announcement/modify.json" method="post" class="uk-form" isform>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="file" name="img" title = "upload file" placeholder = "公告图">
                        </div>
                        <div class="uk-form-row">
                            <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull value="${announcement.displayNum}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="链接地址" notnull value="${announcement.body}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" value="<lch:parse-date time='${announcement.bTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" value="<lch:parse-date time='${announcement.eTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="公告描述" notnull>${announcement.desc}</textarea>
                        </div>
                        <div class="uk-form-row">
                            <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${announcement.status}">
                                <option value="0">未发布</option>
                                <option value="1">发布</option>
                            </select>
                        </div>
                        <div class="uk-form-row uk-text-right">
                            <input type="hidden" name="id" value="${announcement.id}" />
                            <input type="hidden" name = "type" value="2" />
                            <button class="uk-button uk-button-primary" id="role-add-submit-2" target="role-add-form">add</button>
                            <button class="uk-button uk-modal-close">cancel</button>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:when test="${announcement.type == 3}">
                <div class="uk-form-row">
                    <h1>修改话题公告</h1>
                    <form action="/announcement/modify.json" method="post" class="uk-form" isform>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="file" name="img" title = "upload file" placeholder = "公告图">
                        </div>
                        <div class="uk-form-row">
                            <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull value="${announcement.displayNum}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="话题编号" notnull value="${announcement.body}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" value="<lch:parse-date time='${announcement.bTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" value="<lch:parse-date time='${announcement.eTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="公告描述" notnull>${announcement.desc}</textarea>
                        </div>
                        <div class="uk-form-row">
                            <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${announcement.status}">
                                <option value="0">未发布</option>
                                <option value="1">发布</option>
                            </select>
                        </div>
                        <div class="uk-form-row uk-text-right">
                            <input type="hidden" name="id" value="${announcement.id}" />
                            <input type="hidden" name = "type" value="3" />
                            <button class="uk-button uk-button-primary" id="role-add-submit-3" target="role-add-form">add</button>
                            <button class="uk-button uk-modal-close">cancel</button>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:when test="${announcement.type == 4}">
                <div class="uk-form-row">
                    <h1>修改电台音乐公告</h1>
                    <form action="/announcement/modify.json" method="post" class="uk-form" isform>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="file" name="img" title = "upload file" placeholder = "公告图">
                        </div>
                        <div class="uk-form-row">
                            <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull value="${announcement.displayNum}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="Mix编号" notnull value="${announcement.body}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" value="<lch:parse-date time='${announcement.bTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" value="<lch:parse-date time='${announcement.eTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="公告描述" notnull>${announcement.desc}</textarea>
                        </div>
                        <div class="uk-form-row">
                            <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${announcement.status}">
                                <option value="0">未发布</option>
                                <option value="1">发布</option>
                            </select>
                        </div>
                        <div class="uk-form-row uk-text-right">
                            <input type="hidden" name="id" value="${announcement.id}" />
                            <input type="hidden" name = "type" value="4" />
                            <button class="uk-button uk-button-primary" id="role-add-submit-4" target="role-add-form">add</button>
                            <button class="uk-button uk-modal-close">cancel</button>
                        </div>
                    </form>
                </div>
            </c:when>
            <c:when test="${announcement.type == 5}">
                <div class="uk-form-row">
                    <h1>修改赛事公告</h1>
                    <form action="/announcement/modify.json" method="post" class="uk-form" isform>
                        <div class="uk-form-row">
                            <input class="uk-width-1-1 uk-form-large" type="file" name="img" title = "upload file" placeholder = "公告图">
                        </div>
                        <div class="uk-form-row">
                            <input type="number" class="uk-width-1-1 uk-form-large" name="displayNum"  placeholder="显示次数" notnull value="${announcement.displayNum}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="body"  placeholder="赛事编号" notnull value="${announcement.body}">
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="beginTime" value="<lch:parse-date time='${announcement.bTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="开始时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <input type="text" class="uk-width-1-1 uk-form-large" name="endTime" value="<lch:parse-date time='${announcement.eTime}' pattern='yyyy-MM-dd HH:mm'/>" data-uk-datepicker="{format:'YYYY-MM-DD HH:mm'}" placeholder="结束时间" notnull>
                        </div>
                        <div class="uk-form-row">
                            <textarea class="uk-width-1-1 uk-form-large" name="desc" placeholder="公告描述" notnull>${announcement.desc}</textarea>
                        </div>
                        <div class="uk-form-row">
                            <select class="uk-width-1-1 uk-form-large" name="status" placeholder="状态" data-value="${announcement.status}">
                                <option value="0">未发布</option>
                                <option value="1">发布</option>
                            </select>
                        </div>
                        <div class="uk-form-row uk-text-right">
                            <input type="hidden" name="id" value="${announcement.id}" />
                            <input type="hidden" name = "type" value="5" />
                            <button class="uk-button uk-button-primary" id="role-add-submit-5" target="role-add-form">add</button>
                            <button class="uk-button uk-modal-close">cancel</button>
                        </div>
                    </form>
                </div>
            </c:when>
        </c:choose>


    </div>
</div>


<script>
    // 设置  status releaseStatus 选项
    $(function(){
        $("[name=status]").val($("[name=status]").attr("data-value"));
    });

</script>