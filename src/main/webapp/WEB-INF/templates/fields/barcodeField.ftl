<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<div class="field">
	<label for="barcode">Barcode:</label>
    <div class="text">
		<p id="barcode" class="editable">${product.barcode}</p>
        <a id="barcodeEdit" class="action edit">edit</a>
    </div>
</div>

<#if product.canBeEditedBy(currentUser)>
<script type="text/javascript">
//<![CDATA[
    lib.ready(function() {
		jQuery("#barcode").editable("${cp}spring/product/updateBarcode",
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
		 		onsubmit :	function(settings, original) {
								var value = jQuery("input", this).val();
								if (! isValidBarcode(value)) {
									console.log("INVALID BARCODE: " + value);
									original.reset();
									jQuery("#barcodeField").append('<div class="ajaxError">Invalid barcode</div>');
						        	jQuery(".ajaxError").fadeOut(3400, function() { jQuery(".ajaxError").remove(); });
						        	console.log("RETURNING FALSE");
									return false;
								} else {
									console.log("VALID BARCODE: " + value);
									return value;
								}
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
						}							
		 	}
		);
		bindEditFieldClickHandler("#barcodeEdit", "#barcode"); 
    })
//]]>
</script> 
</#if>