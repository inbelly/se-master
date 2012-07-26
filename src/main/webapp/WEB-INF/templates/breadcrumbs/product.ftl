<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>

                <div id="breadcrumbs" class="clear">
                    <span>
                    	<h1><#if product??>${product.name?xhtml}</h1> by <a href="${cp}spring/productList/filterByCompany?company=<#if product.company??>${product.company?url('utf-8')}</#if>"><#if product.company??>${product.company?html}</#if></a></#if>
                    </span>
                </div>