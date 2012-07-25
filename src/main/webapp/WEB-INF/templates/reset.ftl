<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
    
    <div id="login-editing" class="editing clearfix">
        <h3 class="ac"><@spring.message code="reset.title"/></h3>
        <@form.form id="loginForm" cssClass="reset clearfix">
            <p id="passwordReset" style="display:none;" class="red">
				<@spring.message code="resetSuccess.message"/>
			</p> 
			
            <div class="field clearfix">
                <label for="f-email"><@spring.message code="reset.email"/>: </label>
                <div class="input">
					<input type="text" name="email" onfocus="bUsernameHasFocus = true;" onblur="bUsernameHasFocus = false;" id="f-email" value="" maxlength="256" size="30" />
					<#if emailNotFound?? && emailNotFound >
					    <span class="red"><@spring.message code="reset.badEmail"/></span>
					</#if>
                </div>
            </div>
            
            <div class="field clearfix">
                <div class="input">
                	<input name="_eventId_submit" type="submit" value="<@spring.message code="reset.sendPassword"/>" />
					<a href="${flowExecutionUrl}&amp;_eventId_cancel"><@spring.message code="reset.cancel"/></a>
                </div>
            </div>
		</@form.form>
	</div>
