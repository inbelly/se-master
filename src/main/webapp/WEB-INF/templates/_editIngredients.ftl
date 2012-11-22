<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="product" class="editing">
                        <div id="product-info" class="mb clearfix">
                            <div id="product-info-quick">
                                <img src="<#if (product.label?? && product.label.photo?exists && product.label.photo?length > 0)>${cp}files/${product.label.photo}<#else/>${cp}images/product.png</#if>" width="217" height="217" alt="<#if product.name??>${product.name}<#else/>Product</#if> label" class="picture" />
                            </div>
                            
                            <div id="product-info-details">
                                <div id="product-info-details-photo" class="picture-big overflow-drag-widget">
                                    <img src="<#if (product.ingredients?? && product.ingredients.photo?exists && product.ingredients.photo?length > 0)>${cp}files/${product.ingredients.photo}<#else/>${cp}images/product.png</#if>" alt="<#if product.name??>${product.name}<#else/>Product</#if> ingredients" />
                                </div>
                                <script type="text/javascript">
                                // <![CDATA[
                                lib.ready(function() {
                                    lib.widget.overflowDrag.run(lib.dom.byId("product-info-details-photo"));
                                });
                                // ]]>
                                </script>
                            </div>
                            <#if product.name??>${product.name}</#if>
                        </div>
                        
                        <div id="product-info-edit" class="mb clearfix">
                            <@form.form modelAttribute="product" id="product-data-form" cssClass="simple mb clearfix">
                            	<div class="field clearfix">
									<@form.label path="conservantsText" cssErrorClass="errorMsg"><@spring.message code="createproduct.form.additives"/></@form.label>
									<div class="input clearfix">
										<@form.textarea path="conservantsText" rows="5" cols="75" onkeyup="addIngredient(this, false);" onchange="addIngredient(this, false);">${product.conservantsText!""?xhtml}<#if (!product.conservantsText?? || product.conservantsText?trim?length == 0) && product.conservants??><#list product.conservants as e>${e.number}</#list></#if></@form.textarea>
									</div>
									<div class="input clearfix">
										<@form.checkbox path="conservantFree" onchange="if(this.checked){$('#conservantsText').attr('disabled',true);}else{$('#conservantsText').attr('disabled',false);}"/>
										<@form.errors path="conservantFree" element="p" cssClass="errorMsg"/>
										<@spring.message code="createproduct.form.additives.no" />
										<@form.errors path="conservantsText" element="p" cssClass="errorMsg"/>
	                                    
										<div class="example">
	                                        <p>
	                                        	<small><@spring.message code="createproduct.form.additives.desc1"  /><#--a href="">Help</a--></small>
	                                        </p>
											<p class="help hidden">
												<@spring.message code="createproduct.form.additives.desc2"/>
											</p>
											<p class="help hidden">
												<@spring.message code="createproduct.form.additives.desc3" />
											</p>
	                                    </div>
										<#if product.conservants?? && (product.conservants?size >0)>
											<p><@spring.message code="createproduct.form.additives.recognized"/>: <#list product.conservants as e>${e.number} </#list></p>
										</#if>
									</div>
								</div>
<#--								
								<div class="field clearfix">
									<@sec.authorize ifNotGranted="ROLE_ADMIN">
									<span class="label"><@spring.message code="createproduct.form.accept.desc"/></span>
									</@sec.authorize>
									<div class="input">
										<@sec.authorize ifNotGranted="ROLE_ADMIN">
											<input type="checkbox" onchange="if(this.checked){$('#submitButton').attr('disabled', false); }else{$('#submitButton').attr('disabled', true);}" /> <@spring.message code="createproduct.form.accept"/>
										</@sec.authorize>
										<@sec.authorize ifAllGranted="ROLE_ADMIN">
											<p><input type="checkbox" disabled="true" checked /> <@spring.message code="createproduct.form.accept"/></p>
										</@sec.authorize>                                
									</div>
                                </div>
-->                                
                                <div class="field nmb clearfix">
                                    <span class="label">&nbsp;</span>
                                    <div class="input">
                                    	<input type="submit" name="_eventId_cancel" value="<@spring.message code="createproduct.form.back" />" />
                                        <input type="submit" name="_eventId_submit" value="<@spring.message code="createproduct.form.save" />" />
                                    </div>
                                </div>
                            </@form.form>
							<script type="text/javascript">
							//<![CDATA[
								lib.ready(function(){
									$("#conservantsText").autocomplete('conservants', {
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
									$("#conservantsText").result(function(event, data, formatted) {
										$(this)[0].selectionStart = $(this)[0].selectionEnd = $(this).val().length;
										event.preventDefault();
										event.stopPropagation();
										Spring.remoting.submitForm(jQuery("#conservantsText"), 'product-data-form', 
											{
												_eventId:'add',
												fragments: 'eTable'
											}
										);
									});
								});
								
							//]]>							
							</script>                                 
                        </div>
                        
                        <hr />
                        
                        <@tiles.insertAttribute name="eTable" />
                        
<#-- šitas aktualus -->                        
<#--                        
                            <div class="additive unknown clearfix">
                                <div class="additive-info-quick">
                                    <img src="${cp}images/hazard-unknown.png" width="47" height="46" alt="Hazard Unknown" class="hazard" />
                                    <input type="text" value="E123" size="6" class="code" />
                                    <input type="text" value="Mononatrio gliutamatas" size="15" class="name" />
                                    <button type="submit" name="_eventId_save">Išsaugoti</button>
                                </div>
                                
                                <div class="additive-info-details suggestions">
                                    <p class="mbh"><strong>Suggestions:</strong></p>
                                    <div class="mb clearfix">
                                        <ol>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-1">
                                                <label for="f-suggestion-1">E123 labai nuodinga medžiaga</label>
                                            </li>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-2">
                                                <label for="f-suggestion-2">E123 labai nuodinga medžiaga</label>
                                            </li>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-3">
                                                <label for="f-suggestion-3">E123 labai nuodinga medžiaga</label>
                                            </li>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-4">
                                                <label for="f-suggestion-4">E123 labai nuodinga medžiaga</label>
                                            </li>
                                        </ol>
                                        <ol>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-5">
                                                <label for="f-suggestion-5">E123 labai nuodinga medžiaga</label>
                                            </li>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-6">
                                                <label for="f-suggestion-6">E123 labai nuodinga medžiaga</label>
                                            </li>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-7">
                                                <label for="f-suggestion-7">E123 labai nuodinga medžiaga</label>
                                            </li>
                                            <li>
                                                <input type="checkbox" id="f-suggestion-8">
                                                <label for="f-suggestion-8">E123 labai nuodinga medžiaga</label>
                                            </li>
                                        </ol>
                                    </div>
                                    <p class="ar"><a href="">Google it</a></p>
                                </div>
                            </div>
-->                            
