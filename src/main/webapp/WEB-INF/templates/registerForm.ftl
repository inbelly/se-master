<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>

					<div id="login-editing" class="editing clearfix">
                        <@form.form id="login" modelAttribute="user" cssClass="register clearfix">
                            <div class="field clearfix">
                    			<@form.label cssErrorClass="errorMsg" path="nick"><@spring.message code="registerForm.name"/>:</@form.label>
                                <div class="input">
                                	<@form.input path="nick" size="30" maxlength="400" cssErrorClass="error"/>
									<@form.errors path="nick" element="span" cssClass="red"/>
                                </div>
                            </div>
                            <div class="field clearfix">
                    			<@form.label cssErrorClass="errorMsg" path="email"><@spring.message code="registerForm.email"/>:</@form.label>
                                <div class="input">
                                	<@form.input path="email" size="30" maxlength="400" cssErrorClass="error"/>
									<@form.errors path="email" element="span" cssClass="red"/>	
                                </div>
                            </div>
                            <div class="field clearfix">
                    			<@form.label cssErrorClass="errorMsg" path="password"><@spring.message code="registerForm.password"/>:</@form.label>
                                <div class="input">
                    				<@form.password path="password" size="30" maxlength="400" cssErrorClass="error"/>
									<@form.errors path="password" element="span" cssClass="red"/>
                                </div>
                            </div>
                            <div class="field clearfix">
                                <@form.label cssErrorClass="errorMsg" path="passwordVerify"><@spring.message code="registerForm.passwordRepeat"/>:</@form.label>
                                <div class="input">
                    				<@form.password path="passwordVerify" size="30" maxlength="400" cssErrorClass="error"/>
									<@form.errors path="passwordVerify" element="span" cssClass="red"/>
                                </div>
                            </div>
                            <div class="field clearfix">
                                <div id="tAc" class="input">
                                    <@form.checkbox id="f-register" path="aggree" />
                                    <@form.label cssErrorClass="errorMsg" path="aggree">Jag håller med <a href="javascript:void(0);" class="toggle-handle js">villkoren</a></@form.label>
                                    <ul id="termsandconditions" class="hidden"><@spring.message code="createproduct.form.terms" /></ul>
	                                <@form.errors path="aggree" element="span" cssClass="red"/>
                                </div>
								<script type="text/javascript">
		                        //<![CDATA[
		                            lib.ready(function() {
		                                lib.widget.termsAndConditions.run(lib.dom.byId("tAc"), {});
		                            })
		                        //]]>
		                        </script>
                            </div>
                            <div class="field nmb clearfix">
                                <div class="input">
                                	<input type="submit" name="_eventId_submit" value="<@spring.message code="registerForm.register"/>"/>
									<a href="${flowExecutionUrl}&amp;_eventId_cancel"><@spring.message code="registerForm.cancel"/></a>
                                </div>
                            </div>
                        </@form.form>
					</div>
