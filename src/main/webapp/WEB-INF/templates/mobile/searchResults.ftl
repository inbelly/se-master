<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>

<div id="body">
		<@form.form modelAttribute="list">
			<#if searchResults.isConservant()>
				<div id="sortBar"><@spring.message code="searchResults.isAdditive" arguments="${searchResults.query}"/>:</div>
				<@tiles.insertAttribute name="eTable"/>
				<#assign productDescription>
					<@spring.message code='searchResults.productsContaining' />:
				</#assign>
				<#--assign feedLinks='<span id="rss"><a href="products.rss?e=${q}" alt="RSS" title="Paieškos rezultatai RSS formatu"><img src="../img/rss.png" alt="RSS" title="Paieškos rezultatai RSS formatu" /></a> <a href="products.atom?e=${q}" alt="ATOM" title="Paieškos rezultatai ATOM formatu"><img src="../img/rss.png" alt="ATOM" title="Paieškos rezultatai ATOM formatu" /></a> <a href="products.JSON?e=${q}" alt="JSON" title="Paieškos rezultatai JSON formatu"><img src="../img/rss.png" alt="JSON" title="Paieškos rezultatai JSON formatu" /></a></span>' /-->
				<#assign feedLinks="" />
			<#else/>	
				<#if searchResults.isProduct()>
					<#assign productDescription><@spring.message code="searchResults.productsByQuery" arguments="${q?xhtml}" />:	</#assign>
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
			
			<@compress>
				<div id="sortBar">
					<h2>${productDescription?xhtml}</h2>
				</div>
				<div id="catalog">
					<#if searchResults.products??>
						<#list searchResults.products as p>
							<#include "productItem.ftl"/>
						</#list>
						<#if (searchResults.products?size > 0)>		
							<#include "_paginator.ftl" />
						</#if>
					</#if>
				</div>
				<p><a href="index"><@spring.message code='mobile.newSearch' /></a></p>
			</@compress>
			
		</@form.form>
</div>