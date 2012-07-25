<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

				<@sec.authorize ifAnyGranted="ROLE_USER, ROLE_ADMIN">
					<#if comment??>
                        <h2 id="comment" class="mb"><@spring.message code="product.commentsForm.title"/></h2>
						<@form.form id="comment-post" cssClass="simple mb clearfix" modelAttribute="comment" action="comment/add" commandName="comment" method="post">
                            <div class="field clearfix">
                                <label for="f-name"><@spring.message code="product.commentsForm.name"/></label>
                                <div class="input">
                                	<@form.input path="name" maxlength="40" id="f-name" />
                                </div>
                            </div>
                            <div class="field clearfix">
                                <label for="f-email"><@spring.message code="product.commentsForm.email"/></label>
                                <div class="input">
                                	<@form.input path="email" maxlength="60" size="30" id="f-email" />
                                </div>
                            </div>
                            <div class="field clearfix">
                                <label for="f-text"><@spring.message code="product.commentsForm.text"/></label>
                                <div class="input">
                                    <@form.textarea path="body" rows="7" cols="75" id="f-text"/>
                                </div>
                            </div>
                            <div class="field nmb clearfix">
                                <span class="label">&nbsp;</span>
                                <div class="input">
                                    <button type="submit" name="submit"><@spring.message code="product.reportForm.send"/></button>
                                </div>
                            </div>
                            <input type="hidden" name="id" value="${product.id}" />
                        </@form.form>
					</#if>
				</@sec.authorize>
				
				<@sec.authorize ifNotGranted="ROLE_USER,ROLE_ADMIN">
				<p id="comment" class="mbh">
					<@spring.message code="product.loginToComment" arguments="login"/>
				</p>
				</@sec.authorize>
