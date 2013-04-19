<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<div class="field">
    <label for="category">Category:</label>
    <div class="input">
        <h4 id="category" class="editable"><#if product.category??>${product.category.name?html}<#else/>not specified</#if></h4>
        <a id="categoryEdit" href="javascript:void(0);" class="action edit"><@spring.message code="eTable.edit"/></a>
    </div>
</div>

<#if product.canBeEditedBy(currentUser)>
<script type="text/javascript">
//<![CDATA[
    lib.ready(function() {
		jQuery("#category").editable("${cp}spring/product/updateCategory",
			{
		 		tooltip	: 'Klicka för att redigera',
		 		cancel	: 'Avbryt',
		 		submit	: 'OK',
		 		placeholder : 'Klicka här',
		 		onblur : 'ignore',
		 		type	: 'select',
		 		id : 'field',		
		 		ajaxoptions :	{ cache : false, type : 'GET' },
		 		indicator :		'<span class="loadingIndicator"><img src="${cp}images/ajax-loader.gif"/><b>Saving...</b></span>',
		 		submitdata :	function(value, settings) {
		 							return {id : ${product.id}, unique : false, category : jQuery("select", this).val() };
		 						},
				onerror :	function(settings, original, xhr) {
									return processFieldUpdateError(settings, original, xhr);
							},
		 		data 	: { <#list categoryList as category>
								<#if category?? && category.name??>"${category.id}" : "${category.name}",</#if>
							</#list> selected: ${product.category.id} },							
			}
		);
		bindEditFieldClickHandler("#categoryEdit", "#category"); 
    })
//]]>
</script> 
</#if>