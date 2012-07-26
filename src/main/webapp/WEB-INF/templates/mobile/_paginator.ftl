<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#macro pageLink page label class="">
	<a class="${class}" href="${flowExecutionUrl}&amp;_eventId=page&amp;page=${page}" onclick="if($.browser.mozilla){Spring.remoting.submitForm('', 'list', {
			_eventId:'page', 
			fragments: 'body', 
			page: '${page}'
		});return false;}">${label}</a>
</#macro>

			<div id="paginator">
			<p>
				<span class="label"><@spring.message code="paginator.totalPages" arguments="${criteria.totalPages}"/>:</span>
				
				<#assign start = criteria.page - 7/>
				<#if (start < 0)>
					<#assign start = 0 />
				</#if>
				<#if (criteria.page > 0)>
					<#assign page = criteria.page -1 />
					<#assign label><@spring.message code="paginator.back"/></#assign>
					<@pageLink page label 'nextPrevious'/>
				</#if>
				<#list 0..14 as i>
					<#assign page = start + i/>
					<#if page==criteria.page>
						<span class="currPage">${page+1}</span>
					<#else/>
						<@pageLink page page+1/>
					</#if>
					<#if ((page + 1) == criteria.totalPages)>
						<#break/>
					</#if>
				</#list>
				<#if (criteria.page < criteria.totalPages-1)>
					<#assign page = criteria.page + 1 />
					<#assign label><@spring.message code="paginator.forward"/></#assign>
					<@pageLink page label 'nextPrevious'/>
				</#if>
				<span class="clear">&nbsp;</span>
			</p>
			</div>