<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
			<div id="categories">
				<ul>
				<#list categoryList as category>
					<#if category?? && category.name??>
					<li>
						<a href="${cp}spring/productList/filterByCategory?category=${category.id?url('utf-8')}" 
							title="<#if currentUser??>${category.products?size}<#else/>${category.approvedProductsInCategory}</#if>">${category.name?html} (${category.productCount})</a>
					</li>
					</#if>
				</#list>
				</ul>
				
				<#include "_helpus.ftl" />
			</div>