<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<@compress>
	<#if conservants??>
		<#assign haveNotApproved = false />
		<#list conservants as e>
			<p>
			<#assign haveNotApproved = haveNotApproved || ! e.approved />
			<img alt="<@spring.message code="${hazardDescriptions[e.category]}"/>" title="<@spring.message code="${hazardDescriptions[e.category]}"/>" src="../img/mobile/hazard${e.category}.png"/>
			
			<span class="number">${e.number}</span>
							<#if e.vegan>
								<img title="<@spring.message code="eTable.notVegan"/>" alt="<@spring.message code="eTable.notVegan"/>" src="../img/mobile/notVegan.png"/>
							</#if>
							<#-- if e.gmo>
								<img src="../img/gmo.png" width="39" height="17"/>
							</#if -->
							<strong>${e.name?xhtml}</strong>
				<#if (e.diseases?? && e.diseases?length>0)><@spring.message code="eTable.diseases"/>: ${e.diseases?xhtml}</#if>
				<#if e.bannedInUsa || e.bannedInCanada || e.bannedInAustralia>
					<br/><b><@spring.message code="eTable.bannedIn"/>: </b>
				</#if> 
				<#if e.bannedInUsa>
						<@spring.message code="eTable.bannedIn.usa"/>
				</#if>
				<#if e.bannedInCanada>
					<@spring.message code="eTable.bannedIn.canada"/>
				</#if>
				<#if e.bannedInAustralia>
					<@spring.message code="eTable.bannedIn.australia"/>
				</#if>
				</p>
		</#list>
	</#if>
</@compress>