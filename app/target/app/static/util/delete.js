/**
 * Created by wjcaozhi on 16/4/8.
 *
 * 删除页面上一些不必要的东西 比如被植入的广告等
 */

/***
 * 网页端一般植入广告 都是在body中植入
 * 一般植入的位置是在body下的第一个元素开始 或者是body的最后一个元素开始
 * 所以此方法通过判断body中的第一个元素和最后一个元素是否为正确的开始和结束标签来屏蔽广告
 *
 * @param bigId
 * @param endId
 */
function deleteAdvertising(bigId,endId){

    var boot = false;

    if (bigId != $("body *").eq(0).attr("id")) {
        $("body *").eq(0).remove();//删除这个元素
        boot = true;
        console.log("顶部出现了广告 ... ... ");
    }
    if(endId != $("body *").eq(-1).attr("id")){
        $("body *").eq(-1).remove();//删除这个元素
        boot = true;
        console.log("底部出现了广告 ... ... ");
    }

    if (boot) deleteAdvertising(bigId,endId);

}

