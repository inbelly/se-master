<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="friends" class="clearfix">
                        <div class="left">
                            <h2><@spring.message code="friends.partners"/></h2>
                            
                            <#if partners?? && 0 < partners?size>
                            	<#list partners as partner>
	                            <div class="friend clearfix">
	                                <#if partner.thumb??><img src="${cp}files/${partner.thumb}" width="110" height="80" alt="${partner.title}" /></#if>
	                                <div class="text">
	                                    <p>${partner.description}</p>
	                                    <p>
	                                    	<a href="${partner.url}">${partner.url}</a>
		                                <@sec.authorize ifAllGranted="ROLE_ADMIN">
		                                	[<a href="${cp}spring/partner/delete?id=${partner.id}"><@spring.message code="friends.delete" /></a>]
	                            		</@sec.authorize>
	                            		</p>
	                                </div>
	                            </div>                            	
                            	</#list>
                            <#else/>
                            	<@spring.message code="friends.partners.none" />
                            </#if>
                            <@sec.authorize ifAllGranted="ROLE_ADMIN">
                        	<div id="partnerForm" class="addFriend clear clearfix">
                            	<hr />
                            	<div class="clear clearfix">
			                        <span class="toggle fl">
			                            <a href="javascript:void(0);" class="toggle-handle js"><h4><@spring.message code="friends.partners.add" /></h4></a>
			                        </span>
		                       	</div>                            	
								<@form.form id="addPartner" action="partner/add" modelAttribute="partner" enctype="multipart/form-data">
									<div class="row">
										<@form.label path="title" cssErrorClass="errorMsg"><@spring.message code="friends.form.name"/>:</@form.label>
										<@form.input path="title" size="50" maxlength="400" cssErrorClass="error"/>
										<@form.errors path="title" element="p" cssClass="errorMsg"/>
									</div>
									<div class="row">
										<@form.label path="url" cssErrorClass="errorMsg"><@spring.message code="friends.form.url"/>:</@form.label>
										<@form.input path="url" size="50" maxlength="400" cssErrorClass="error"/>
										<@form.errors path="url" element="p" cssClass="errorMsg"/>
									</div>
									<div class="clear">&nbsp;</div>
									<div class="row">
										<@form.label path="file" cssErrorClass="errorMsg"><@spring.message code="friends.form.picture"/>:</@form.label>
										<input type="file" name="file"/>
										<@form.errors path="file" element="p" cssClass="errorMsg"/>
									</div>
									<div class="clear">&nbsp;</div>
									<div class="row">
										<@form.label path="description" cssErrorClass="errorMsg"><@spring.message code="friends.form.description"/>:</@form.label>
										<@form.textarea path="description" cols="50" rows="10" cssErrorClass="error"/>
										<@form.errors path="description" element="p" cssClass="errorMsg"/>
									</div>
									<div class="clear">&nbsp;</div>
									<div class="buttonGroup">
										<p>
										<input type="submit" name="_eventId_submit" value="<@spring.message code="friends.form.save"/>" id="submitButton"/>
										</p>
									</div>
								</@form.form>
								<script type="text/javascript">
		                        //<![CDATA[
		                            lib.ready(function() {
		                                lib.widget.addFriend.run(lib.dom.byId("partnerForm"), {});
		                            })
		                        //]]>
		                        </script>
	                        </div>								                         
                            </@sec.authorize>
                        </div>
						<div class="right">
                            <h2><@spring.message code="friends.donators"/></h2>
                            <#if donators?? && 0 < donators?size>
                            	<#list donators as donator>
	                            <div class="donator clearfix">
	                            	<div class="image">
		                                <#if donator.thumb??><img src="${cp}files/${donator.thumb}" width="40" height="40" alt="${donator.title}" /></#if>
		                                <p class="green">${donator.amount}${donator.currency}</p>
		                            </div>
                                    <div class="text">
                                    	<p><em class="date">${donator.donationDate?string("yyyy-MM-dd")}</em></p>
	                                    <p>
	                                    	<a href="${donator.url}">${donator.title}</a>
		                                    <@sec.authorize ifAllGranted="ROLE_ADMIN">
		                                		[<a href="${cp}spring/donator/delete?id=${donator.id}"><@spring.message code="friends.delete" /></a>]
			                                </@sec.authorize>
	                                    </p>
	                                    <p>${donator.description}</p>
	                                </div>
	                            </div>
	                            </#list>                               
                            <#else/>
                            	<@spring.message code="friends.donators.none" />
                            </#if>
                            <@sec.authorize ifAllGranted="ROLE_ADMIN">
                            <div id="donatorForm" class="addFriend clear clearfix">
                            	<hr />
                            	<div class="clear clearfix">
			                        <span class="toggle fl">
			                            <a href="javascript:void(0);" class="toggle-handle js"><h4><@spring.message code="friends.donators.add" /></h4></a>
			                        </span>                     
		                        </div>       	
								<@form.form id="addDonator" action="donator/add" modelAttribute="donator" enctype="multipart/form-data">
									<div class="row">
										<@form.label path="title" cssErrorClass="errorMsg"><@spring.message code="friends.form.name"/>:</@form.label>
										<@form.input path="title" size="50" maxlength="400" cssErrorClass="error"/>
										<@form.errors path="title" element="p" cssClass="errorMsg"/>
									</div>
									<div class="row">
										<@form.label path="url" cssErrorClass="errorMsg"><@spring.message code="friends.form.url"/>:</@form.label>
										<@form.input path="url" size="50" maxlength="400" cssErrorClass="error"/>
										<@form.errors path="url" element="p" cssClass="errorMsg"/>
									</div>
									<div class="row">
										<@form.label path="donationDate" cssErrorClass="errorMsg"><@spring.message code="friends.form.donationDate"/>:</@form.label>
										<@form.input path="donationDate" size="50" maxlength="400" cssErrorClass="error" onclick="jQuery('#donationDate').DatePickerShow()"/>
										<@form.errors path="donationDate" element="p" cssClass="errorMsg"/>
									</div>
									<div class="row">
										<@form.label path="amount" cssErrorClass="errorMsg"><@spring.message code="friends.form.amount"/>:</@form.label>
										<@form.input path="amount" size="50" maxlength="400" cssErrorClass="error"/>
										<@form.errors path="amount" element="p" cssClass="errorMsg"/>
									</div>
									<div class="row">
										<@form.label path="currency" cssErrorClass="errorMsg"><@spring.message code="friends.form.currency"/>:</@form.label>
										<@form.input path="currency" size="50" maxlength="400" cssErrorClass="error"/>
										<@form.errors path="currency" element="p" cssClass="errorMsg"/>
									</div>	
									<div class="row">
										<@form.label path="description" cssErrorClass="errorMsg"><@spring.message code="friends.form.description"/>:</@form.label>
										<@form.input path="description" size="50" maxlength="400" cssErrorClass="error"/>
										<@form.errors path="description" element="p" cssClass="errorMsg"/>
									</div>
									<div class="clear">&nbsp;</div>
									<div class="row">
										<@form.label path="file" cssErrorClass="errorMsg"><@spring.message code="friends.form.picture"/>:</@form.label>
										<input type="file" name="file"/>
										<@form.errors path="file" element="p" cssClass="errorMsg"/>
									</div>
									<div class="clear">&nbsp;</div>
									<div class="buttonGroup">
										<p>
										<input type="submit" name="_eventId_submit" value="<@spring.message code="friends.form.save"/>" id="submitButton"/>
										</p>
									</div>
								</@form.form>
								<script type="text/javascript">
		                        //<![CDATA[
		                            lib.ready(function() {
		                                lib.widget.addFriend.run(lib.dom.byId("donatorForm"), {});
										jQuery('#donationDate').DatePicker({
											date: jQuery('#donationDate').val(),
											current: jQuery('#donationDate').val(),
											starts: 1,
											position: 'right',
											onBeforeShow: function(){
												jQuery('#donationDate').DatePickerSetDate("${donator.donationDate?string("yyyy-MM-dd")}", true);
											},
											onChange: function(formated, dates){
												jQuery('#donationDate').val(formated);
												jQuery('#donationDate').DatePickerHide();
											}
										});
										jQuery('#donationDate').DatePickerHide();
		                            })
		                        //]]>
		                        </script>								
							</div>                          
                            </@sec.authorize>                            
                        </div>
                    </div>