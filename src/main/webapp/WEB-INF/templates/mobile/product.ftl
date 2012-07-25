#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>

<div id="body">
	<@compress>
	<div id="catalog">
		<#if (createProductInProgress?exists && product.id?string == createProductInProgress)><div id="greenAciu"><h2><img src="../img/check_green.png" />Ačiū už dalyvavimą ir pagalbą Sveiko Vaiko projektui. Peržiūrėję produktą, patalpinsime į katalogą.</h2></div></#if>
		<@form.form modelAttribute="list">
		<div id="productInfo">
			<h1><#if product.gmo?? && product.gmo><img src="../img/gmo.png" title="Pagamintas iš genetiškai modifikuotų organizmų arba turi tokių sudėtyje" alt="Pagamintas iš genetiškai modifikuotų organizmų arba turi tokių sudėtyje"/> </#if>${product.name?xhtml}</h1>
			<hr />
			<div class="section">
				<h2><@spring.message code="product.producer"/>:</h2>
				<p>${product.company?html}</p>
			</div>
			<div class="section">
				<h2><@spring.message code="product.category"/>:</h2>
				<p><#if product.category??>${product.category.name?html}</#if></p>
			</div>
			<#if !product.approvedContent><div class="atmaz"><@spring.message code="product.unapprovedIngredients"/></div></#if>
		</div>
		</@form.form>
	</div>
	</@compress>
	<#if (product.conservants?? && product.conservants?size > 0)>
		<h2 id="tableHeader"><@spring.message code="product.detectedAdditives"/>:</h2>
		<@tiles.insertAttribute name="eTable"/>
	</#if>
	<p><a href="index"><@spring.message code="mobile.newSearch"/></a></p>
	