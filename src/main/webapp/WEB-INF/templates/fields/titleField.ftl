<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<div class="field">
    <label for="name">Namn:</label>
    <div class="text">
        <h3 id="name" class="editable">${product.name?xhtml}</h3>
        <a id="titleEdit" href="javascript:void(0);" class="action edit">edit</a>
    </div>
</div>
<#if product.canBeEditedBy(currentUser)>
<script type="text/javascript">
//<![CDATA[
    lib.ready(function() {
		jQuery("#name").editable("${cp}spring/product/updateValue",
		 	{
		 		tooltip	: 'Klicka för att redigera',
		 		cancel	: 'Avbryt',
		 		submit	: 'OK',
		 		placeholder : 'Klicka här',
		 		onblur : 'ignore',
		 		id : 'field',
		 		ajaxoptions :	{ cache : false, type : 'GET' },
		 		indicator :		'<span class="loadingIndicator"><img src="${cp}images/ajax-loader.gif"/><b>Saving...</b></span>',
		 		submitdata :	{id : ${product.id}, 
					  			 unique : true },		 		
				onerror :	function(settings, original, xhr) {
									return processFieldUpdateError(settings, original, xhr);
							},
				data :	function(value,settings) {
							var ret = value;
							if(value.match(/^'+$/)) {
								ret+="''";
							}
							if (value.match(/^"+$/)) {
								ret+="\"\"";
							}
							
							console.log(ret); 
							
							return ret;
						}
		 	}
		);  
		bindEditFieldClickHandler("#titleEdit", "#name"); 		                              
    })
//]]>
</script> 
</#if>
