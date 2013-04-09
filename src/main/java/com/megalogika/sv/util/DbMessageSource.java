/**
 * 
 */
package com.megalogika.sv.util;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.context.support.StaticMessageSource;

/**
 *
 * Uses default {@link org.springframework.context.support.ResourceBundleMessageSource} as back store.
 * Only edited in GUI messages are stored in DB. 
 * 
 * @author rodu
 *
 */
public class DbMessageSource extends ResourceBundleMessageSource implements InitializingBean {
    
    private static final transient Logger log_ = Logger.getLogger(DbMessageSource.class);
    
    
    protected String[] basenames = new String[0];
    
    protected Locale mainLocale;
    
    protected StaticMessageSource localCache = new StaticMessageSource();
    
    protected Set<String> allCodesSet = new TreeSet<String>();
    
    

    @Override
    public void afterPropertiesSet() throws Exception {
        localCache.setAlwaysUseMessageFormat(this.isAlwaysUseMessageFormat());
        localCache.setUseCodeAsDefaultMessage(false);
        
        log_.info("Loading available keys from resource files...");
        for (String basename: basenames) {
            ResourceBundle rb = getResourceBundle(basename, mainLocale);
            if (null != rb) {
                Enumeration<String> keys = rb.getKeys();
                while (keys.hasMoreElements()) {
                    allCodesSet.add(keys.nextElement());
                }
            }
        }
        log_.info("Loaded " + allCodesSet.size() + " keys");
    }
    
    public void addDbMessageToCache(String code, Locale locale, String msg) {
        localCache.addMessage(code, locale, msg);
    }
    

    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String msg = null;
        try { 
            msg = localCache.getMessage(code, null, locale);
        } catch (NoSuchMessageException nme) {
            //niam niam
        }
        if (null == msg)
            msg = super.resolveCodeWithoutArguments(code, locale);
        return msg;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String msg = resolveCodeWithoutArguments(code, locale);
        if (null != msg)
            return new MessageFormat(msg, locale);
        return null;
    }

    
    @Override
    public void setBasenames(String[] basenames) {
        super.setBasenames(basenames);
        this.basenames = basenames;
    }

    @Override
    public void setBasename(String basename) {
        super.setBasename(basename);
        this.basenames = new String[] { basename };
    }

    public void setMainLocale(Locale mainLocale) {
        this.mainLocale = mainLocale;
    }


    public String[] getBasenames() {
        return basenames;
    }


    public Locale getMainLocale() {
        return mainLocale;
    }


    public StaticMessageSource getLocalCache() {
        return localCache;
    }


    public Set<String> getAllCodesSet() {
        return allCodesSet;
    }
    
    
    
    
}
