/**
 * 
 */
package com.megalogika.sv.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.megalogika.sv.model.I18Message;
import com.megalogika.sv.model.ProductCategory;
import com.megalogika.sv.model.User;
import com.megalogika.sv.service.I18MessagesService;

/**
 * @author rodu
 *
 */
@Controller("i18MessagesController")
@RequestMapping("/i18Messages")
public class I18MessagesController {
    
    private transient final Logger logger = Logger.getLogger(I18MessagesController.class);
    
    @Autowired(required = true)
    private I18MessagesService messagesSrv;
    
    @Autowired(required = true)
    private FrontendService frontendService;
    
    
    @ModelAttribute(FrontendService.KEY_CONTEXT_PATH)
    public String getContextPath(HttpServletRequest request) {
        return frontendService.getContextPath(request);
    }

    @ModelAttribute(FrontendService.KEY_HAZARD_DESCRIPTIONS)
    public Map<String, String> getHazardDescriptions() {
        return frontendService.getHazardDescriptions();
    }

    @ModelAttribute(FrontendService.KEY_CATEGORY_LIST)
    public List<ProductCategory> getCategoryList() {
        return frontendService.getCategoryList();
    }

    @ModelAttribute(FrontendService.KEY_CURRENT_USER)
    public User getCurrentUser() {
        return frontendService.getUserService().getCurrentUser();
    }
    
    @ModelAttribute(FrontendService.SETTINGS_MENU_MARKER)
    public String settingsMenuMark() {
        return "true";
    }
    
    @RequestMapping(method = RequestMethod.GET)
    @Secured({ "ROLE_ADMIN" })
    public ModelAndView messagesList(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        return new ModelAndView("messagesList", "messagesList", messagesSrv.getAllMessages());
    }

    @RequestMapping(method = RequestMethod.POST)
    @Secured({ "ROLE_ADMIN" })
    public ModelAndView modifyMessage(@RequestParam(value="code") String code,
                                      @RequestParam(value="locale") String locale,
                                      @RequestParam(value="message") String message,
                                      HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {
        
        I18Message msg = messagesSrv.findMessage(locale, code);
        if (null == msg)
            throw new IllegalArgumentException("Message not found for code=" + code + " and locale=" + locale);
        msg.setMessage(message);
        messagesSrv.modifyMessage(msg);
        return new ModelAndView(new RedirectView(getContextPath(request) + "spring/i18Messages"), new HashMap<String, Object>());
    }


    

    public void setMessagesSrv(I18MessagesService messagesSrv) {
        this.messagesSrv = messagesSrv;
    } 
    
    
    
    
}
