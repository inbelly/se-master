<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="barcode-editing" class="editing">
                        <h3 class="ac"><@spring.message code="thankyoumessage" /> Låt oss först kontrollera att vi verkligen inte den här produkten ännu!</h3>
                        <p class="ac mb">
                            Ange den streckkoden här 
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
                            <p id="newBarcodeErr" class="green hidden"><small>Det verkar som du har angivit en felaktig streckkod. Vänligen kontrollera det!</small></p>
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
