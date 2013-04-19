/**
 * 
 */
package com.megalogika.sv.service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.LocaleUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalogika.sv.model.I18Message;
import com.megalogika.sv.util.DbMessageSource;

/**
 *  Stores and serves messages (aka translations) to/from DB.
 *  Uses {@link DbMessageSource} to provide message resolve services to Spring context. 
 * 
 * @author rodu
 *
 */
@Service("i18MessagesService")
@Repository
//@Transactional(readOnly = true)
public class I18MessagesService /*implements InitializingBean*/ {
    
    private static final transient Logger log_ = Logger.getLogger(I18MessagesService.class);
    
    @PersistenceContext
    protected EntityManager em;
    
    @Autowired(required = true)
    protected MessageSource messageSource;
    
    protected DbMessageSource dbMessageSource;
    
    
    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        dbMessageSource = resolveDbMessageSource(messageSource);
        if (null == dbMessageSource) {
            log_.warn("DbMessageSource not found in context, I18 messages edit functions are disabled.");
        } else {
            log_.info("Loading custom messages from DB...");
            @SuppressWarnings("unchecked")
            List<I18Message> messages = em.createQuery("select m from I18Message m").getResultList();
            int c = 0;
            for (I18Message msg: messages) {
                dbMessageSource.addDbMessageToCache(msg.getCode(), LocaleUtils.toLocale(msg.getLocale()), msg.getMessage());
                c++;
            }
            log_.info("Loaded " + c + " custom messages from DB");
        }
    }
    
    /**Resolves {@link DbMessageSource} instance (if any) from hierarchy of parent message sources.*/
    protected DbMessageSource resolveDbMessageSource(MessageSource ms) {
        if (null == ms)
            return null;
        if (ms instanceof DbMessageSource)
            return (DbMessageSource)ms;
        if (ms instanceof AbstractMessageSource) 
            return resolveDbMessageSource(((AbstractMessageSource)ms).getParentMessageSource());
        if (ms instanceof DelegatingMessageSource) 
            return resolveDbMessageSource(((DelegatingMessageSource)ms).getParentMessageSource());
        return null;
    }
    
    public SortedSet<I18Message> getAllMessages() {
        if (null == dbMessageSource)
            return new TreeSet<I18Message>();
        SortedSet<I18Message> retVal = new TreeSet<I18Message>();
        for (String code: dbMessageSource.getAllCodesSet()) {
            retVal.add(new I18Message(dbMessageSource.getMainLocale().toString(), code, dbMessageSource.getMessage(code, null, dbMessageSource.getMainLocale())));
        }
        return retVal;
    }
    
    
    public I18Message findMessage(String locale, String code) {
        I18Message criteria = new I18Message(locale, code, null);
        Object[] values = getAllMessages().toArray();
        int pos = Arrays.binarySearch(values, criteria);
        if (pos < 0 || pos >= values.length)
            return null;
        return (I18Message)values[pos];
    }
    
    @Transactional
    public void modifyMessage(I18Message message) {
        message = em.merge(message);
        if (null == dbMessageSource) {
            log_.warn("Editing message on non-editable message source????");
        } else {
            dbMessageSource.addDbMessageToCache(message.getCode(), LocaleUtils.toLocale(message.getLocale()), message.getMessage());
        }
    }
    
    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    
    
}
