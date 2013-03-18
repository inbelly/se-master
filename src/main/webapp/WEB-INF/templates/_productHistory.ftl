<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#macro historyList eventList messageCode>
    <#if eventList?size < 2 >
    	<#assign entries = eventList?size />
    <#else/>
    	<#assign entries = 2 />
    </#if>
    
    <p>
        <span><@spring.message code="${messageCode}" /></span>
        <#list eventList as event>
        	<#if entries <= event_index>
        		&hellip;<#break/>
        	</#if>
			<span><#-->a href="user?id=${change.actor.id}"-->${event.actor.nick}<#--/a--> (${event.eventTime?string("yyyy-MM-dd")})<#if event_index < (entries - 1)>, </#if></span>
        </#list>
    </p>    
</#macro>

	                            <div class="history">
	                            	<#if product.user?? && product.user.nick??>
	                                <p>
	                                    <span><@spring.message code="product.postedBy" /></span>
	<#--                                        
	                                        <span><a href="user?id=${product.user.id}">${product.user.nick}</a> (${product.entryDate?string("yyyy-MM-dd")})</span>
	-->                                        
										<span>${product.user.nick} (${product.entryDate?string("yyyy-MM-dd")})</span>
	                                </p>
	                                </#if>
	                                <#if product.changes?? && 0 < product.changes?size>
	                                	<@historyList product.changes "product.editedBy" />
	                                </#if>
	                                <#if product.confirmations?? && 1 < product.confirmations?size>
	                                    <span><@spring.message code="product.confirmedBy" /></span>
	                                    <span>${product.latestConfirmation.actor.nick} (${product.latestConfirmation.eventTime?string("yyyy-MM-dd")})</span>
	                                </#if>
	                                <#--
	                                <#if product.reports?? && 0 < product.reports?size>
	                                	<@historyList product.reports "product.reportedBy" />
	                                </#if>
	                                -->   
	                            </div>
