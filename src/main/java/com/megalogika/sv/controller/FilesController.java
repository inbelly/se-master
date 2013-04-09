/**
 * 
 */
package com.megalogika.sv.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.support.RequestContext;

/**
 * Serves files from ${destdir}.
 * 
 * @author rodu
 **/
public class FilesController extends AbstractController implements InitializingBean {
    
    private static final Log log_ = LogFactory.getLog(FilesController.class);
    
    @Autowired(required = true)
    private FrontendService frontendService;
    
    protected MimetypesFileTypeMap mimeResolver = new MimetypesFileTypeMap();

    protected long cacheExpiresMinutes;
    
    protected transient String cacheControlStr;
    
    protected File destDir;
    
    
    @Override
    public void afterPropertiesSet() throws Exception {
        cacheControlStr = "max-age=" + (cacheExpiresMinutes * 60) + ", must-revalidate";
    }

    /**Serves files from ${destdir}.*/
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
        String urlPath = request.getRequestURI(); 
        getWebApplicationContext().getServletContext().getRealPath(urlPath);
        try {
            serveFile(new File(destDir, urlPath), response);
        } catch (FileNotFoundException fe) {
            try {
                response.sendError(404);
            } catch (IOException e) {
                throw new IOError(e);
            }
        } catch (Throwable t) {
            log_.error("Failed to serve file: " + t.getMessage(), t);
            try {
                response.sendError(500);
            } catch (IOException e) {
                throw new IOError(e);
            }
        }
        
        return null;
    }

    protected void serveFile(File f, HttpServletResponse response) throws IOException {
        if (!f.isFile()) {
            log_.warn("File '" + f.getAbsolutePath() + "' not found.");
            throw new FileNotFoundException();
        }
        
        InputStream fis = null, bfis = null;
        try {
            fis = new FileInputStream(f); 
            bfis = new BufferedInputStream(fis, 16384);
            response.setHeader("Cache-Control", cacheControlStr);
            response.setDateHeader("Expires", System.currentTimeMillis() + cacheExpiresMinutes * 60000);
            response.setDateHeader("Last-Modified", f.lastModified());
            response.setContentLength((int)f.length());
            response.setContentType(mimeResolver.getContentType(f));
            IOUtils.copy(bfis, response.getOutputStream());
        } finally {
            try {
                if (null != bfis)
                    bfis.close();
                if (null != fis)
                    fis.close();
            } catch (Throwable t) {
                log_.warn("Failed to close stream for file: " + f.getAbsolutePath());
            }
        }

    }
    
    

    public File getDestDir() {
        return destDir;
    }


    public void setDestDir(File destDir) {
        this.destDir = destDir;
    }

    public FrontendService getFrontendService() {
        return frontendService;
    }

    public void setFrontendService(FrontendService frontendService) {
        this.frontendService = frontendService;
    }

    public long getCacheExpiresMinutes() {
        return cacheExpiresMinutes;
    }

    public void setCacheExpiresMinutes(long cacheExpiresMinutes) {
        this.cacheExpiresMinutes = cacheExpiresMinutes;
    }
    
}
