function isdefined(variable) {
    return (typeof(window[variable]) == "undefined")?  false: true;
}

function zeroPad(num, count) {
	var pad = count - (num + '').length;
	var ret = num;
	while(pad-- > 0) {
	    ret = '0' + ret;
	}
	return ret;
};

function mod10CheckDigit(digits) {
	var toCheck = digits.replace(/[^0-9]+/g, '');
	var total = 0;
	for (i=toCheck.length-3; i>=0; i=i-2) {
		total += toCheck.substr(i,1) * 1;
	}
	for (i=toCheck.length-2; i>=0; i=i-2) {
		total += toCheck.substr(i,1) * 3;
	}
	
	var nextTen = ((Math.ceil(total / 10)) * 10);
	return nextTen - total;
}

String.prototype.trim = function() {
	  return this.replace(/^\s+|\s+$/g, "");
};

function isValidBarcode(barcode) {
	if (isNaN(barcode)) {
		return false;
	} else {
		var last = barcode.substring(barcode.length-1);
		return last == mod10CheckDigit(barcode);
	}
}

function processBarcode(barcode, codeType, digitsBeforeCksum, barcodeId, errFieldId) {
	if (isNaN(barcode)) {
		return false;
	} else {
		var first = barcode.substring(0, digitsBeforeCksum);
		var last = barcode.substring(digitsBeforeCksum);
		var checkDigit = mod10CheckDigit(barcode);
		if(last != checkDigit){
			jQuery(errFieldId).removeClass('hidden');
			return false;
		} else {
			jQuery(errFieldId).addClass('hidden');
			//showBarcode(barcodeId, barcode, codeType);
			return true;
		}
	}
}

function checkBarcode(inputField, errFieldId) {
	var barcode = inputField.value.trim();
	if ('' != barcode && isNaN(barcode)) {
		jQuery(errFieldId).removeClass('hidden');
		return false;
	}
	if ('' == barcode && ! jQuery(errFieldId).hasClass('hidden')) {
		jQuery(errFieldId).addClass('hidden');
		return false;
	}
	return true;
}

function getBarcodeType(barcode) {
	switch (barcode.length) {
	case 8:	// EAN-8
		return 'ean8';
		break;
	case 12: // UPC-A
		return 'upc';
		break;
	case 13: // EAN-13
		return 'ean13';
		break;
	default:
		return '';
	}
}

function validateBarcode(inputField, barcodeId, errFieldId) {
	var barcode = inputField.val().trim();
	var codeType = getBarcodeType(barcode);
	if ('' != barcode && ! isNaN(barcode) && '' != codeType) {
		return processBarcode(barcode, codeType, barcode.length-1, barcodeId, errFieldId);
	} else {
		jQuery(errFieldId).removeClass('hidden');
		return false;
	}
}

function showBarcode(elementId, code, codeType) {
    var elem = lib.dom.byId(elementId.substr(1, elementId.length - 1)),
        klass = elem.className.split(" "),
        params = {};
    
    if (klass && klass.length) {
        for (var i in klass) {
            var k = klass[i].split("-");
            if (k.length == 2) {
                params[k[0]] = k[1];
            }
        }
    }
    
	jQuery(elementId).barcode(code, codeType, lib.extend({barWidth: 1.5, barHeight: 29, showHRI: false}, params));
	//jQuery(elementId).append('<p>'+code+"</p>");
}

function getSuggestList(uri, key, cont) {
	var script_name = uri;
	var params = { 'q':key }
	jQuery.get(script_name,params, 
         function(obj){ 
           cont(obj); 
         },
         'json');		
}

function getCompanies(key, cont) {
	getSuggestList('companies', key, cont);
}

function nameChanged(input) {
	var name=input.value;
	jQuery("#foundProductsBlock").hide();
	if(jQuery.trim(name)=="") {
		return;
	}
	jQuery(input).after("<img src=\"../img/ajax-loader.gif\" class=\"ajaxLoader\"/>");
	jQuery(input).attr('readonly', 'true');
	jQuery(input).css('color', '#666666');
	jQuery.getJSON("productsHelper.json", {'q':name}, function(data) {
		jQuery(input).attr('readonly', '');
		jQuery(".ajaxLoader").remove();
		jQuery("#foundProducts").html("");
		if(data.length > 0){
			jQuery.each(data, function(i,item){
				jQuery("#foundProducts").append($("<a href=\"product?id=" + item.id + "\">" + item.name + "</a><br/>"));
			});
			jQuery("#foundProductsBlock").show();
		}
	});
}

function prepareXhrArgs(productId, unique) {
	var xhrArgsStr = "{'id': '" + productId + "'," + 
					  "'unique': '" + unique + "'}";
	console.log(xhrArgsStr);
	ret = eval('(' + xhrArgsStr + ')');
	console.log("NEXT IS XHR ARGS");
	console.log(ret);
	return ret;
}

function processFieldUpdateError(settings, original, xhr) {
	original.reset();
	jQuery(original).append('<div class="ajaxError">'+xhr.responseText+'</div>');
    jQuery(".ajaxError").fadeOut(3400, function() { jQuery(".ajaxError").remove(); });
}

function updateProductField(productId, fieldId, fieldName, value, oldValue, url, unique) {
	var valueToSend = value;
	if(value.match(/^'+$/)) {
		valueToSend+="''";
	}
	if (value.match(/^"+$/)) {
		valueToSend+="\"\"";
	}
	
	var ret = value;
	var xhrArgsStr = "{'id': '" + productId + "', 'field': '" + fieldName + "', 'value': '" + valueToSend + "', '" + fieldName + "' : '" + valueToSend + "', 'unique': '" + unique +"'}";
	return jQuery.ajax({
		data: eval('(' + xhrArgsStr + ')'),
		url: 'product/' + url,
	    success: function(data, textStatus, xhr) {
	        jQuery(fieldId).html(data);
	        
	        return data;
	    },
	    error: function(xhr, status, error) {
	    	console.log(fieldId);
	    	console.log(xhr);
	    	jQuery(fieldId).html(oldValue);
	    	jQuery(fieldId).insertAfter('<div class="ajaxError">'+xhr.responseText+'</div>');
	        jQuery(".ajaxError").fadeOut(3400, function() { jQuery(".ajaxError").remove(); });
	        
	        return oldValue;
	    },
	    cache: false
	});
	
//	return oldValue;
}

function bindEditFieldClickHandler(editLink, fieldToEdit) {
	jQuery(editLink).click(
			function(objEvent) {
				jQuery(fieldToEdit).trigger('click', objEvent);
			}
		);
}

function parseIngredients(element) {
	Spring.remoting.submitForm(element, 'product-data-form', 
		{
			_eventId:'add',
			fragments: 'eTable'
		}
	);
}

function addIngredient(element, blur) {
	var text = element.value.trim();
	if (blur || ',' == text.charAt(text.length -1)) {
		parseIngredients(element);
	}
}

$("#conservantsText").bind('change', function(e) {
	  this.text = this.text;
});