package com.business.live.live;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.mix.Mix;
import com.business.core.entity.user.User;
import com.business.core.operations.mix.MixCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.live.socket.SocketMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/6/19 0019.
 */
@Service
public class LiveService {

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private MixCoreDao mixCoreDao;

    public SocketMessage live (Integer uid, Integer mid) {
        User user = userCoreDao.findUserById(uid, "name", "avatar");
        Mix mix = mixCoreDao.findMixById(mid, "name", "author", "url", "albumUrl_2");
        SocketMessage socketMessage = new SocketMessage();
        socketMessage.setUi(uid);
        socketMessage.setMi(mid);
        if (user != null) {
            socketMessage.setNa(user.getName());
            // 用户头像处理 （第三方登陆头像带http）
            if (null != user.getAvatar() && user.getAvatar().indexOf("http") == -1) {
                socketMessage.setUa(FileCenterClient.buildUrl(user.getAvatar()));
            }
            else {
                socketMessage.setUa(user.getAvatar());
            }
        }
        if (mix != null) {
            socketMessage.setMa(mix.getAuthor());
            socketMessage.setMn(mix.getName());
            socketMessage.setMu(FileCenterClient.buildUrl(mix.getUrl()));
            socketMessage.setMau(FileCenterClient.buildUrl(mix.getAlbumUrl_2()));
        }
        return socketMessage;
    }
}
