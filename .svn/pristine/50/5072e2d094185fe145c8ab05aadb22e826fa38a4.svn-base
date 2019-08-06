package com.business.work.base.upload;

import com.business.core.entity.UploadEntity;
import org.apache.commons.fileupload.ProgressListener;

import javax.servlet.http.HttpSession;

/**
 * Created by sin on 2015/11/16.
 */
public class FileUploadProgressListener implements ProgressListener {

    private HttpSession session;

    public FileUploadProgressListener(HttpSession session) {
        this.session = session;
    }

    @Override
    public void update(long pBytesRead, long pContentLength, int pItems) {
        System.out.println((double)pBytesRead/pContentLength);
        session.setAttribute("progress", (double)pBytesRead / pContentLength);
    }
}
