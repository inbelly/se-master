package ml.filelist.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

/**
 * Decompiled from walrus-jar...
 * 
 * @author ml
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked"})
public class FilesController extends MultiActionController {
    private File destDir;

    public FilesController() {
        this.destDir = null;
    }

    public ModelAndView listFiles(HttpServletRequest request, HttpServletResponse response, FileListCommand command) {
        HashMap model = new HashMap();

        this.logger.debug("DEST DIR: " + this.destDir.getAbsolutePath());

        FilenameFilter filter = (null != command) && (command.isImage()) ? new UploadedImageFilter()
                : new UploadedFileFilter();
        String[] fileList = this.destDir.list(filter);
        List list = new ArrayList(Arrays.asList(fileList));
        model.put("list", list);
        model.put("type", null != command ? command.getType() : null);

        return new ModelAndView("fileList", "model", model);
    }

    private DateFormat getDateFormat() {
        return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    }

    private long yesterdayTimestamp() {
        return System.currentTimeMillis() - 86400000L;
    }

    public void sendFile(HttpServletRequest request,
            HttpServletResponse response) throws IOException {
        String uri = request.getRequestURI();
        response.addHeader("X-Sent-By", "mufc:1.2");
        if (!StringUtils.hasText(uri)) {
            response.sendError(400);
        } else {
            uri = URLDecoder.decode(uri.substring(uri.lastIndexOf("/") + 1),
                    "UTF-8");

            File res = new File(this.destDir.getPath() + File.separator + uri);
            if (!res.exists()) {
                response.sendError(404);
            } else if (0L == res.length()) {
                response.sendError(409);
            } else if ((res.isDirectory()) || (res.isHidden())) {
                response.sendError(403);
            } else {
                response.setContentType(getServletContext().getMimeType(
                        uri.toLowerCase()));
                response.setHeader("ETag", getETag(res));
                response.setDateHeader("Last-Modified", getLastModifiedHttp(res));
                response.setHeader("Cache-Control", "max-age=0, must-revalidate");
                response.setDateHeader("Expires", System.currentTimeMillis());
                if ((getLastModified(res)
                        .equals(browserCacheTimestamp(request)))
                        || (getLastModified(res)
                                .before(browserCacheTimestamp(request)))) {
                    response.setStatus(304);
                } else {
                    String etag = request.getHeader("If-None-Match");
                    if ((StringUtils.hasText(etag))
                            && (etag.equals(getETag(res)))) {
                        response.setStatus(304);
                    } else {
                        response.setHeader("Content-Length", Long.toString(res.length()));
                        try {
                            IOUtils.copyLarge(new FileInputStream(res), response.getOutputStream());
                        } catch (FileNotFoundException e) {
                            this.logger.warn("Suddenly, the file is not found: ", e);
                            response.sendError(404);
                        }
                    }
                }
            }
        }
    }

    private Date browserCacheTimestamp(HttpServletRequest request) {
        String sNotModifiedSince = request.getHeader("If-Modified-Since");
        Date notModifiedSince = Calendar.getInstance().getTime();
        if (StringUtils.hasText(sNotModifiedSince)) {
            sNotModifiedSince = sNotModifiedSince.trim();
            try {
                notModifiedSince = getDateFormat().parse(sNotModifiedSince);
            } catch (ParseException e) {
                this.logger.warn("PARSE EX: =" + sNotModifiedSince + "=", e);
                notModifiedSince = new Date(0L);
            } catch (NumberFormatException e) {
                this.logger.warn("NUMBER FORMAT EX: =" + sNotModifiedSince
                        + "=", e);
                notModifiedSince = new Date(0L);
            } catch (Exception e) {
                this.logger.warn("OTHER EX: =" + sNotModifiedSince + "=", e);
                notModifiedSince = new Date(0L);
            }
        } else {
            notModifiedSince = new Date(0L);
        }
        return notModifiedSince;
    }

    public File getDestDir() {
        return this.destDir;
    }

    public void setDestDir(File destDir1) {
        this.destDir = destDir1;
    }

    protected String getETag(File item) {
        return "W/\"" + item.length() + "-" + item.lastModified() + "\"";
    }

    protected Date getLastModified(File item) {
        Date ret = new Date();
        ret.setTime(item.lastModified());

        return ret;
    }

    protected long getLastModifiedHttp(File item) {
        long lastmodified = item.lastModified();
        if (0L == lastmodified) {
            this.logger.warn(item
                    + " says it was last modified at Epoch start...");
            lastmodified = yesterdayTimestamp();
        }
        //String ret = getDateFormat().format(d);
        return lastmodified;
    }

    class UploadedImageFilter extends FilesController.UploadedFileFilter {
        UploadedImageFilter() {
            super();
        }

        @Override
        public boolean accept(File dir, String name) {
            if (org.apache.commons.lang.StringUtils.isBlank(name))
                return false;
            name = name.toLowerCase();
            return super.accept(dir, name) && (
                        name.endsWith(".jpg")
                        || name.endsWith(".png")
                        || name.endsWith(".gif")
                    );        
            }
    }

    class UploadedFileFilter implements FilenameFilter {
        UploadedFileFilter() {
        }

        @Override
        public boolean accept(File dir, String name) {
            if (!StringUtils.hasText(name)) {
                return false;
            }
            File test = new File(dir.getAbsoluteFile() + File.separator + name);
            return (null != test) && (test.isFile()) && (!test.isHidden())
                    && (!test.isDirectory()) && (test.canRead());
        }
    }
}