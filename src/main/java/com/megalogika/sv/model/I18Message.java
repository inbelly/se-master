/**
 * 
 */
package com.megalogika.sv.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Single localization message.
 * 
 * @author rodu
 *
 */
@Entity
@IdClass(I18MessagePK.class)
@Cache(usage = CacheConcurrencyStrategy.NONE)
public class I18Message implements Comparable<I18Message> {
    
    @Id
    @Column(length = 16, updatable = false)
    protected String locale;

    @Id
    @Column(length = 256, updatable = false)
    protected String code;
    
    
    @Basic
    @Column(length = 2000)
    protected String message;

    
    public I18Message() {
        //default c-tor
    }
    
    public I18Message(String locale, String code, String message) {
        this.locale = locale;
        this.code = code;
        this.message = message;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String getDisplayText() {
        if (StringUtils.isNotBlank(message))
            return message;
        return code;
    }

    @Override
    public int compareTo(I18Message o) {
        if (null == code)
            return null == o.code ? 0 : -1;
        if (null == o.code)
            return 1;
        return code.compareTo(o.code);
    }

    @Override
    public int hashCode() {
        int hc = null == code ? 0 : code.hashCode();
        hc += null == locale ? 0 : locale.hashCode();
        return hc;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof I18Message))
            return false;
        I18Message m = (I18Message)obj;
        String cmp = null == code ? "" : code;
        cmp += null == locale ? "" : locale;
        
        String mcmp = null == m.code ? "" : m.code;
        cmp += null == m.locale ? "" : m.locale;
        
        return cmp.equals(mcmp);
    }
    
    
    
    
}
