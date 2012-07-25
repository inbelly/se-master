<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#function shortenString str len=16>
	<#if str??>
		<#if str?length <= len>
			<#return str />
		<#else>
			<#if isShoppingBasketPrint?? && isShoppingBasketPrint>
				<#return str />
			<#else/>
				<#return str?substring(0, len)?html + "&hellip;" />
			</#if>
		</#if>
	<#else/>
		<#return "&hellip;" />
	</#if>
</#function>

<@compress>
<#-- webflow interop hack!!! -->
<#if currentUser?? && currentUser.principal?? >
	<#assign currentUser = currentUser.principal >
</#if>

			<#-->div class="product<#if !p.approved> notapproved</#if><#if !p.approvedContent> notapprovedcontent</#if>"-->
                            <a href="${cp}spring/product?id=${p.id}" title="${p.name?xhtml}"><img src="<#if (p.label?? && p.label.photo?? && p.label.photo?length > 0)>${cp}files/${p.label.photo}<#else/>${cp}/images/product.png</#if>" width="215" height="215" alt="${p.name?xhtml} photo" class="picture" /></a>
                            <img src="${cp}images/hazard-${p.hazard}.png" width="45" height="45" alt="<@spring.message code="${hazardDescriptions[p.hazard]}" />" class="hazard" title="<@spring.message code="${hazardDescriptions[p.hazard]}" />"/>
                            <h3><a href="${cp}spring/product?id=${p.id}" title="${p.name?xhtml}">${shortenString(p.name, 38)}</a></h3>
                            <h4><a class="gray" href="${cp}spring/productList/filterByCompany?company=<#if p.company??>${p.company?url('utf-8')}</#if>" title="products by ${p.company?xhtml}"><#if p.company??>${shortenString(p.company, 56)}</#if></a></h4>
                            <p class="additives">
							<#if (p.conservants?? && p.conservants?size > 0)>
								<#assign eList = "" />
								<strong>Contains:</strong>
										<#if p.conservants?size < 6 >
											<#assign listEnd = p.conservants?size - 1>
										<#else/>
											<#assign listEnd = 5>
										</#if>
										<#list p.conservants as e>
											<#if listEnd < e_index>
												<#break/>
											</#if>
											<#assign eList = eList + e.number />
											<#if (e_index < listEnd)>
												<#assign eList = eList + ", ">
											</#if>
										</#list>
	                             ${shortenString(eList, 20)}&nbsp;<a href="${cp}spring/product?id=${p.id}">(${p.conservants?size})</a>
							<#else/>
								<@spring.message code="productItem.noE" />
							</#if>
                            </p>

<#--
					<@sec.authorize ifAllGranted="ROLE_ADMIN">
					<a href="#" class="adminLink name" onclick="Spring.remoting.submitForm('', 'list', {
										_eventId:'edit', 
										fragments: 'body', 
										id: '${p.id}' 
									}); return false;"><@spring.message code="productItem.edit"/></a>
					<a href="product/delete?id=${p.id}" class="adminLink" onclick="return confirm('Ar tikrai tikrai?');"><@spring.message code="productItem.delete"/></a>
					<#if !p.approved>
						<a href="product/approve?id=${p.id}" class="adminLink">V</a>
					<#else/>
						<a href="product/disapprove?id=${p.id}" class="adminLink">X</a>
					</#if>
					<#if !p.approvedContent>
						<a href="product/approveContent?id=${p.id}" class="adminLink">VV</a>
					<#else/>
						<a href="product/disapproveContent?id=${p.id}" class="adminLink">XX</a>
					</#if>
					</@sec.authorize>
-->

<#--
				<#if currentUser?? && currentUser.hasInBasket(p)>
					<a href="basket/remove?id=${p.id}" class="shoppingBasket" title="<@spring.message code="basket.remove"/>"><img alt="<@spring.message code="basket.remove"/>" src="../img/sv_icon_listout.gif"/></a>
				<#else/>
					<a href="basket/add?id=${p.id}" class="shoppingBasket" title="<@spring.message code="basket.add"/>"><img alt="<@spring.message code="basket.add"/>" src="../img/sv_icon_listin.gif"/></a>						
				</#if>
				<#if p.gmo>
					<img src="../img/gmo.png" class="gmo"/> 
				</#if>
			</div>
-->		
</@compress>
