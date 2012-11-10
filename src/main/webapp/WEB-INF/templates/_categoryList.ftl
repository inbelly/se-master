<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<div id="categories">
    <ul>
        <li>
            <#if criteria.hasFilter()>
                <#list criteria.filters as filter>
                    <#if filter.descriptionArgument = "UnapprovedContentFilter">
                        <span class="UnapprovedContentFilter selected">Unapproved products</span>
                    <#else>
                        <a class="UnapprovedContentFilter" href="${cp}spring/productList/filterByUnapprovedProducts">Unapproved products</a>
                    </#if>
                </#list>
            <#else>
                <a class="UnapprovedContentFilter" href="${cp}spring/productList/filterByUnapprovedProducts">Unapproved products</a>
            </#if>
        </li>
        <#list categoryList as category>
            <#if category?? && category.name??>
                <li>
                    <#if criteria.hasFilter()>
                        <#list criteria.filters as filter>
                            <#if filter.descriptionArgument = category.name>
                                <span class="selected">${category.name?html} (${category.productCount})</span>
                            <#else>
                                <a href="${cp}spring/productList/filterByCategory?category=${category.id?url('utf-8')}" title="<#if currentUser??>${category.products?size}<#else/>${category.approvedProductsInCategory}</#if>">${category.name?html} (${category.productCount})</a>
                            </#if>
                        </#list>
                    <#else>
                        <a href="${cp}spring/productList/filterByCategory?category=${category.id?url('utf-8')}" title="<#if currentUser??>${category.products?size}<#else/>${category.approvedProductsInCategory}</#if>">${category.name?html} (${category.productCount})</a>
                    </#if>
                </li>
            </#if>
        </#list>
    </ul> 
    <#include "_helpus.ftl" />
</div>