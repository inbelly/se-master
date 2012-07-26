<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

				<#if conservants??>
					<div id="e-list" class="e-list columns-3">
                        <div id="sort" class="clearfix">
                            <p class="fl mbh">
                                <strong><@spring.message code="eList.sort.sortBy"/>:</strong>
                                <a href="${cp}spring/e/sort?orderBy=number" <#if criteria.orderBy=='number'>class="current" </#if>><@spring.message code="eList.sort.number"/></a>
                                <a href="${cp}spring/e/sort?orderBy=category" <#if criteria.orderBy=='category'>class="current" </#if>><@spring.message code="eList.sort.hazard"/></a>
                            </p>
<#--                                
                            <p class="fr mbh ico">
                                <a href="<@spring.message code="eList.downloadPdfUrl"/>" class="pdf"><span><@spring.message code="eList.downloadPdfLink"/></span></a>
                            </p>
-->                                
                        </div>
						<@tiles.insertAttribute name="eTable" />
					</div>
				<@tiles.insertAttribute name="categoryList" />
				</#if>

<#--	
		<div id="sortBar">
			<span class="label"><@spring.message code="eList.sort.sortBy"/>:</span>
			<a href="${cp}spring/e/sort?orderBy=number" <#if criteria.orderBy=='number'>class="current" </#if>><@spring.message code="eList.sort.number"/></a>
			<a href="${cp}spring/e/sort?orderBy=category" <#if criteria.orderBy=='category'>class="current" </#if>><@spring.message code="eList.sort.hazard"/></a>
			<a href="${cp}spring/e/sort?orderBy=productCount" <#if criteria.orderBy=='productCount'>class="current" </#if>><@spring.message code="eList.sort.productCount"/></a>
			<@sec.authorize ifAllGranted="ROLE_ADMIN">
				<a href="createE"><@spring.message code="eList.createE"/></a>
			</@sec.authorize>
		</div>
		<div id="pdf">
			<img src="${cp}img/pdf-icon.gif" alt="PDF" /> <a href="<@spring.message code="eList.downloadPdfUrl"/>"><@spring.message code="eList.downloadPdfLink"/></a>
		</div>
-->				