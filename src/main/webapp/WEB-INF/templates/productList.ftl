<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#if searchResults?? && searchResults.products?? && ! products??>
	<#assign products = searchResults.products />
</#if>

<#if searchResults?? >
	<#if searchResults.conservant>
		<div id="sortBar">
			<@spring.message code="searchResults.isAdditive" arguments="${searchResults.query}"/>:
		</div>
		<@tiles.insertAttribute name="eTable"/>
		<#assign productDescription>
			<@spring.message code='searchResults.productsContaining' />:
		</#assign>
		<#assign feedLinks="" />
	<#elseif searchResults.isProduct()>
		<#assign productDescription>
			<@spring.message code="searchResults.productsByQuery" arguments="${q?xhtml}" />:
		</#assign>
		<#assign feedLinks>
			<span id="rss"><a href="products.rss?q=${q}" alt="RSS" title="<@spring.message code="searchResults.rss"/>"><img src="../img/rss.png" alt="RSS" title="<@spring.message code="searchResults.rss"/>" /></a> <a href="products.atom?q=${q}" alt="ATOM" title="<@spring.message code="searchResults.atom"/>"><img src="../img/rss.png" alt="ATOM" title="<@spring.message code="searchResults.atom"/>" /></a> <a href="products.JSON?q=${q}" alt="JSON" title="<@spring.message code="searchResults.json"/>"><img src="../img/rss.png" alt="JSON" title="<@spring.message code="searchResults.json"/>" /></a></span>
		</#assign>
	<#else>
		<#assign productDescription>
			<@spring.message code='searchResults.notFound' />
		</#assign>
		<#assign feedLinks="" />
	</#if>
</#if>

<div id="products" class="products-list columns-3">
	<div id="sort" class="mbh clearfix">
		<p class="fl">
			<#if criteria.hasFilter()>
				<strong><@spring.message code="productList.filter.filter"/>:</strong>
				<#list criteria.filters as filter>
					<#if filter.descriptionArgument != "ApprovedProductFilter">
						<a href="${cp}spring/productList/remove?filterIndex=${filter_index}" title="<@spring.message code="productList.filter.remove" />" class="ico remove"><span><@spring.message code="${filter.description}" arguments="${filter.descriptionArgument!''}"/></span></a>
					</#if>
				</#list>
			</#if>
		</p>
		<#if criteria.hasFilter()>
			<p class="fr">
				<a href="${cp}spring/productList?clear=yes"><@spring.message code="productList.filter.reset"/></a>
			</p>
		</#if>
	</div>
	<#if (products?? && products?size > 0)>
		<#list products as p>
			<div class="product <#if 0 == (p_index + 1) % 3>last</#if>">
				<#if !p.approved>
					<img src="${cp}img/aproval_1.png" alt="not approved" class="not-approved"/>
				</#if>
				<#include "productItem.ftl"/>
			</div>
		</#list>
		<@tiles.insertAttribute name="paginator" /> 
	<#else/>
		<p>Inga produkter</p>
	</#if>
</div>

<@tiles.insertAttribute name="categoryList" />