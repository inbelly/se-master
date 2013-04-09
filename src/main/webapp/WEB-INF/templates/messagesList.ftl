<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
    
    <script type='text/javascript'>
       $(document).ready(function(){
         $('.popbox').popbox();
       });
    </script>
    
    <div id="breadcrumbs" class="clear">
        <span><@spring.message code="messagesList.title"/></span>
    </div>
    <hr class="clear mbh">
    <div id="messagesList" class="clearfix">
        <#list messagesList as msg>
            <div class='popbox'>
              <a class='open' href='#'><@spring.message code="messagesList.edit"/></a>
              <span>${msg.getDisplayText()}</span>
              <div class='collapse'>
                <div class='box'>
                  <div class='arrow'></div>
                  <div class='arrow-border'></div>
                  <div class="editing">
                    <form id="saveMessage" class="clearfix" action="${cp}spring/i18Messages" method="post">
                        <p class="clearfix">
                            <input type="hidden" name="code" value="${msg.code}"></input>
                            <input type="hidden" name="locale" value="${msg.locale}"></input>
                            <textarea rows="3" cols="100" name="message" maxlength="200"csserrorclass="error">${msg.message}</textarea>
                            <p><@spring.message code="messages.field.locale"/>:&nbsp;&nbsp;${msg.locale}</p>
                            <p><@spring.message code="messages.field.code"/>:&nbsp;&nbsp;${msg.code}</p>
                            <button id="newMessageSubmit" type="submit" name="newMessageSubmit" onclick=""><@spring.message code="messages.save"/></button>
                        </p>
                    </form>
                  </div>
                  <a href="#" class="close"><@spring.message code="messages.close"/></a>
                </div>
              </div>
            </div>
        </#list>
    </div>
    