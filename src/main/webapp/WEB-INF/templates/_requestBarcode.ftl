<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="barcode-editing" class="editing">
                        <h3 class="ac"><@spring.message code="thankyoumessage" /> First, let's check that we really don't have this product yet!</h3>
                        <p class="ac mb">
                            Please enter the product barcode right here 
                        </p>
                        <@form.form modelAttribute="product" method="post" cssClass="clearfix" id="enterBarcode" onsubmit="return validateBarcode(jQuery('.barcode'), '#newBarcodeImg', '#newBarcodeErr');">
                            <#--p id="newBarcodeImg" class="bgColor-edfeef">
                            </p-->
                            <p>
						    	<img src="${cp}images/barcode.png"  width="136" height="29" alt="Barcode picture" />
                            </p>	                            
                            <p class="clearfix">
                            	<input name="barcode" value="" maxlength="13" class="barcode" cssErrorClass="error" onkeyup="checkBarcode(this, '#newBarcodeErr');" />
                                <button id="newBarcodeSubmit" type="submit" name="_eventId_submit" onclick="return validateBarcode(jQuery('.barcode'), '#newBarcodeImg', '#newBarcodeErr');"><@spring.message code="product.next" /></button>
                            	<@form.errors path="barcode" element="p" cssClass="errorMsg" />
                            </p>
                            <p id="newBarcodeErr" class="green hidden"><small>It seems that you have entered an incorrect barcode. Please check it!</small></p>
                        </@form.form>
<#-->                        
                        <script type="text/javascript">
                        //<![CDATA[
                            lib.ready(function() {
                                showBarcode('#newBarcodeImg', '0000000000000', 'ean13');
                            })
                        //]]>
                        </script>  
-->                        
                    </div>
