package ml.filelist;

import org.springframework.web.servlet.mvc.multiaction.PropertiesMethodNameResolver;

/**
 * Decompiled from walrus-jar...
 * 
 * @author ml
 * 
 * */
public class DefaultPropertiesMethodNameResolver extends PropertiesMethodNameResolver {
    private String defaultMethod = null;

    @Override
    public String getHandlerMethodNameForUrlPath(String urlPath) {
        String ret = super.getHandlerMethodNameForUrlPath(urlPath);
        if (null == ret) {
            return getDefaultMethod();
        }
        return ret;
    }

    public void setDefaultMethod(String defaultMethod) {
        this.defaultMethod = defaultMethod;
    }

    public String getDefaultMethod() {
        return this.defaultMethod;
    }
}
