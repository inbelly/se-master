<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#macro getPhotoLink photo><#if (photo?? && photo.photo?exists && photo.photo?length > 0)>${cp}files/${photo.photo}<#else/>${cp}images/product.png</#if></#macro>

                    <div id="product" class="editing inline">
                        	<div id="product-info" class="clearfix <#if ! product.canBeConfirmedBy(currentUser)>mb</#if>" >
                            <div id="product-info-quick">
                                <img src="<#if (product.label?? && product.label.photo?exists && product.label.photo?length > 0)>${cp}files/${product.label.photo}<#else/>${cp}images/product.png</#if>" width="217" height="217" alt="${product.name?xhtml} photo" title="${product.name?xhtml}" class="picture" />
                            </div>
                            <div id="product-info-details">
                                <div id="product-info-details-photo" class="picture-big overflow-drag-widget">
                                    <img src="<@getPhotoLink product.ingredients />" width="1024" height="768" alt="${product.name?xhtml} ingredients" title="${product.name?xhtml} ingredients" onClick="window.open('<@getPhotoLink product.ingredients />', 'WindowC', 'width=${product.ingredients.width}, height=${product.ingredients.height}, scrollbars=yes');"/>
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
                        
                        <@sec.authorize ifAnyGranted="ROLE_ADMIN, ROLE_USER">
                      	<#if product.canBeConfirmedBy(currentUser)>
	                        <div id="waiting-approval" class="mb clearfix">
	                        	<div class="message">
		                            <p>Genom att skriva in data som du har gjort gällande att du har läst och accepterat våra <a href="javascript:void(0);" class="toggle-handle js">villkor</a>.</p>
		                        </div>
                                <ul id="termsandconditions" class="hidden"><@spring.message code="createproduct.form.terms" /></ul>	                            
	                        </div>
							<script type="text/javascript">
	                        //<![CDATA[
	                            lib.ready(function() {
	                                lib.widget.termsAndConditions.run(lib.dom.byId("waiting-approval"), {});
	                            })
	                        //]]>
	                        </script>	                        
                      	</#if>
                      	</@sec.authorize>
                      	
                      	<div id="product-info-edit" class="mb clearfix">
                            <div class="left" class="clearfix">
                                <@tiles.insertAttribute name="companyField" />
								<@tiles.insertAttribute name="titleField" />
								<@tiles.insertAttribute name="categoryField" />
								<div class="field">
								    <label for="barcode">Barcode:</label>
								    <div class="text">
								        <h4 id="barcode"><#if product.barcode??>${product.barcode?html}<#else>nonexistent</#if></h4>
								    </div>
								</div>	
                            </div>
                                
                            <div class="right" class="clearfix">
                                <div id="product-info-edit-actions" class="clearfix">
								<@sec.authorize ifAnyGranted="ROLE_ADMIN,ROLE_USER">
            						<#if product.canBeReportedBy(currentUser)>
	                                    <div class="ico report">
	                                    	<a href="${cp}spring/product/report?id=${product.id}" onclick="return confirm('<@spring.message code="confirm.really" />');"><@spring.message code="productItem.report"/></a>
	                                    </div>
									</#if>					
									<#if product.canBeConfirmedBy(currentUser)>
	                                    <div class="ico confirm">
	                                    	<a href="${cp}spring/product/confirm?id=${product.id}" onclick="return confirm('<@spring.message code="confirm.really" />');"><@spring.message code="productItem.confirm"/></a><br/>
	                                    </div>
	                                </#if>
								</@sec.authorize>		
<#--                                        
	                                <div class="ico edit-photo">
	                                    <a href="">Edit photo<br />(crop or turn)</a>
	                                </div>
-->								
                                </div>
                                
                                <div class="field checkbox clearfix" id="product-info-edit-inform">
                                    <div class="text clearfix">
                                    </div>
                                </div>
                                <@tiles.insertAttribute name="ingredientsField" />

		                        <div id="product-info-details">
		                        	<#include "_productHistory.ftl" />
	                                <form method="get" action="../product" class="clearfix done">
	                                    <p class="fr">
		                                    <input type="hidden" name="id" value="${product.id}" />
		                                    <button type="submit">Done editing</button>
					        				<@sec.authorize ifAllGranted="ROLE_ADMIN">
		                                    	<a href="${cp}spring/product/delete?id=${product.id}" class="red" onclick="return confirm('är du säker?');">bort produkten</a>
											</@sec.authorize>    		                                    
		                                </p>
	                                </form>		                        	
		                        </div>
                        	</div>
                        </div>      
                                              
                        <hr />

						<#if (product.conservants?? && product.conservants?size > 0) || (product.conservantFree?? && product.conservantFree)>
								<h2 class="mb"><@spring.message code="product.detectedAdditives"/></h2>
								<div id="eTable><@tiles.insertAttribute name="eTable" /></div>
						<#else/>
							<h2 class="mb"><@spring.message code="product.noE"/></h2>
						</#if>                    
                        
						<@tiles.insertAttribute name="commentForm" />
						<#include "_comments.ftl" />                        
                    </div>                                        
                    <@tiles.insertAttribute name="categoryList" />