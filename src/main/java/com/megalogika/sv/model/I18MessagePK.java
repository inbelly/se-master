/**
 * 
 */
package com.megalogika.sv.model;

import java.io.Serializable;

/**
 * Just some bureaucracy...
 * 
 * @author rodu
 *
 */
public class I18MessagePK implements Serializable {
    private static final long serialVersionUID = 6327539476176920978L;
    protected String locale;
    protected String code;
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((code == null) ? 0 : code.hashCode());
        result = prime * result + ((locale == null) ? 0 : locale.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        I18MessagePK other = (I18MessagePK) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        if (locale == null) {
            if (other.locale != null)
                return false;
        } else if (!locale.equals(other.locale))
            return false;
        return true;
    }
    
    
}