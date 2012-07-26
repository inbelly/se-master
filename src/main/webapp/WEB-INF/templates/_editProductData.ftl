<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<script type="text/javascript" src="../js/jquery.autocomplete.min.js"></script>
<script type="text/javascript" src="../js/jquery.bgiframe.min.js"></script>

                    <div id="product" class="editing">
                        <div id="product-info" class="mb clearfix">
                            <div id="product-info-quick">
                                <img src="<#if (product.label?? && product.label.photo?exists && product.label.photo?length > 0)>${cp}files/${product.label.photo}<#else/>${cp}images/product.png</#if>" width="217" height="217" alt="Product label" class="picture" />
                            </div>
                            
                            <div id="product-info-details">
                                <div id="product-info-details-photo" class="picture-big overflow-drag-widget">
                                    <img src="<#if (product.ingredients?? && product.ingredients.photo?exists && product.ingredients.photo?length > 0)>${cp}files/${product.ingredients.photo}<#else/>${cp}images/product.png</#if>" alt="Product ingredients" />
                                </div>
                                <script type="text/javascript">
                                // <![CDATA[
                                lib.ready(function() {
                                    lib.widget.overflowDrag.run(lib.dom.byId("product-info-details-photo"));
                                });
                                // ]]>
                                </script>
                            </div>
                        </div>
                        
                        <div id="product-info-edit" class="mb clearfix">
                            <@form.form modelAttribute="product" id="product-data-form" cssClass="simple mb clearfix">
                                <div class="field clearfix">
                                	<@form.label path="name" cssClass="label" cssErrorClass="errorMsg"><@spring.message code="createproduct.form.name"/></@form.label>
                                    <div class="input">
                                    	<@form.input path="name" cssErrorClass="error" onchange="nameChanged(this)"/>
	                                    <@form.errors path="name" element="p" cssClass="errorMsg"/>
                                    </div>
                                </div>
								<div class="field clearfix" style="display:none" id="foundProductsBlock">
									<h2><@spring.message code="createproduct.form.name.found.header"/></h2>
									<p id="foundProducts"></p>
									<p><@spring.message code="createproduct.form.name.found.desc"/></p>
								</div>                                
                                <div class="field clearfix">
                                    <@form.label path="company" cssClass="label" cssErrorClass="errorMsg"><@spring.message code="createproduct.form.producer"/></@form.label>
                                    <div class="input">
                                        <@form.input path="company" id="company" cssErrorClass="error" autocomplete="off"/>
	                                    <@form.errors path="company" element="p" cssClass="errorMsg"/>
                                    </div>
                                </div>
                                <div class="field clearfix">
                                	<@form.label path="category" cssClass="label" cssErrorClass="errorMsg"><@spring.message code="createproduct.form.category"/></@form.label>
                                	<div class="input">
										<@form.select path="category" cssErrorClass="error" id="f-category" cssClass="mbh">
											<@form.option value="" label=""/>
											<#list categoryList as category>
												<@form.option value="${category.id}" label="${category.name}"/>
											</#list>
										</@form.select>
										<@form.errors path="category" element="p" cssClass="errorMsg"/>
									</div>
                                </div>
                                <div class="field nmb clearfix">
                                    <span class="label">&nbsp;</span>
                                    <div class="input clearfix">
                                    	<input type="submit" name="_eventId_cancel" value="<@spring.message code="createproduct.form.back" />" />
                                        <input type="submit" name="_eventId_submit" value="<@spring.message code="product.next"/>" />
                                    </div>
                                </div>
							<script type="text/javascript">
							//<![CDATA[
								lib.ready(function() {
									jQuery("#company").autocomplete('companies', {
										minChars: 4, 
										delay: 100, 
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
											return item.value;
										}
									})
								});
							//]]>							
							</script>                                
                            </@form.form>
							<script type="text/javascript">
							//<![CDATA[
								lib.ready(function(){
									jQuery("#company").autocomplete('companies', {
										minChars: 4, 
										delay: 100, 
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
											return item.value;
										}
									});
								});
							//]]>							
							</script>                              
                        </div>
                    </div>
