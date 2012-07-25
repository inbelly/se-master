<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<div class="field" >
    <label for="conservantsText">Additives:</label>
    <div class="text">
        <h4 id="conservantsText" class="editable">${product.conservantsText}</h4> 
    </div>
    <a id="conservantsTextEdit" href="javascript:void(0);" class="action edit">edit</a>
</div>
<#if product.canBeEditedBy(currentUser)>
<script type="text/javascript">
//<![CDATA[
    lib.ready(function() {
		$.editable.addInputType('autocomplete', {
		    element: $.editable.types.textarea.element,
		    plugin: function(settings, original) {
		        jQuery("textarea", this).autocomplete('${cp}spring/conservants', {
										minChars: 3, 
										delay: 10, 
										multiple: true,
										parse: function(data) {
											return $.map(eval(data), function(row) {
												return {
													data: row,
													value: row.value,
													result: row.value
												}
											});
										},
										formatItem: function(item){
											return item.value + " (" + item.info + ")";
										}			
									});
		    }
		});    
		jQuery("#conservantsText").editable("${cp}spring/product/updateConservants",
		 	{
		 		tooltip	: 'Click to edit',
		 		cancel	: 'Cancel',
		 		submit	: 'OK',
		 		placeholder : 'Click here',
		 		onblur : 'ignore',
		 		id : 'field',
		 		type : 'autocomplete',
		 		cssclass : 'inherit',
		 		style : 'inherit',
		 		width: 'none',
		 		rows: 3,
		 		ajaxoptions :	{ cache : false, type : 'GET' },
		 		indicator :		'<span class="loadingIndicator"><img src="${cp}images/ajax-loader.gif"/><b>Saving...</b></span>',
		 		submitdata :	function(value, settings) {
		 							return {id : ${product.id}, unique : false, conservantsText : jQuery("textarea", this).val() };
		 						},		 		
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
						},
				callback :	function(result, settings) {
								jQuery('#eTable').load("${cp}spring/product/eTable?id=${product.id}");
								jQuery("textarea", this).val(result);
								console.log('reloaded eTable');
								console.log(result);
							}
				
		 	}
		);  
		bindEditFieldClickHandler("#conservantsTextEdit", "#conservantsText"); 		                              
    })
//]]>
</script> 
</#if>
