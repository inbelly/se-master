<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#function shortenString str len=20>
	<#if str??>
		<#if str?length <= len>
			<#return str />
		<#else>
			<#return str?substring(0, len) + "..." />
		</#if>
	<#else/>
		<#return "..." />
	</#if>
</#function>

<@compress>
			<div class="product<#if !p.approved> notapproved</#if><#if !p.approvedContent> notapprovedcontent</#if>">
				<img alt="<@spring.message code="${hazardDescriptions[p.hazard]}"/>" title=<@spring.message code="${hazardDescriptions[p.hazard]}"/>" src="../img/mobile/hazard${p.hazard}.png" class="category"/> 
				<a href="product?id=${p.id}" class="name">${p.name?xhtml}</a><#if p.company??> - ${shortenString(p.company, 28)?html}</#if>
			</div>
</@compress>