<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#macro getPhotoLink photo><#if (photo?? && photo.photo?exists && photo.photo?length > 0)>${cp}files/${photo.photo}<#else/>${cp}images/product.png</#if></#macro>

                    <div id="product" class="inline">
                        <div id="product-info" class="clearfix">
                            <div id="product-info-quick">
                                <img src="<#if (product.label?? && product.label.photo?exists && product.label.photo?length > 0)>${cp}files/${product.label.photo}<#else/>${cp}images/product.png</#if>" width="217" height="217" alt="${product.name?xhtml} photo" title="${product.name?xhtml}" class="picture" />
                            </div>
                            <div id="product-info-details">
                                <div id="product-info-details-photo" class="picture-big overflow-drag-widget">
                                    <img src="<@getPhotoLink product.ingredients />" width="1024" height="768" alt="${product.name?xhtml} ingredienser" title="${product.name?xhtml} ingredients" onClick="window.open('<@getPhotoLink product.ingredients />', 'WindowC', 'width=${product.ingredients.width}, height=${product.ingredients.height}, scrollbars=yes');"/>
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
                        
                      	<#if (currentUser?? && currentUser.admin) || !product.confirmed && (!currentUser?? ||  (currentUser?? && (product.canBeConfirmedBy(currentUser) || product.canBeEditedBy(currentUser))))>
	                        <div id="waiting-approval" class="mb clearfix">
	                        	<div class="message">
									<p>
										<#if !currentUser?? || product.canBeConfirmedBy(currentUser)>Dessa data väntar på ditt godkännande.</#if> Kontrollera uppgifterna nedan.
										<#if !currentUser?? || product.canBeEditedBy(currentUser)>Du kan <a href="product/edit?id=${product.id}">redigera</a> <#if !currentUser?? || product.canBeReportedBy(currentUser)>eller
										<a href="product/report?id=${product.id}">rapportera</a> </#if>det om det inte är korrekt.</#if>
									</p>
		                            
		                            <ul class="termsandconditions mt hidden"><@spring.message code="createproduct.form.terms" /></ul>
		                        </div>
		                        <#if !currentUser?? || product.canBeConfirmedBy(currentUser)>
    		                        <div class="confirm" onsubmit="return confirm('<@spring.message code="confirm.really" />');">
    		                            <form method="get" action="${cp}spring/product/confirm">
    		                                <input type="hidden" name="id" value="${product.id}"/>
    		                                <button type="submit">Bekräfta</button>
    		                            </form>
    		                        </div>
		                        </#if>
	                        </div>
							<script type="text/javascript">
	                        //<![CDATA[
	                            lib.ready(function() {
	                                lib.widget.termsAndConditions.run(lib.dom.byId("waiting-approval"), {});
	                            })
	                        //]]>
	                        </script>	                        
                      	</#if>
                      	
                      	<#if product.confirmed && (!currentUser?? ||  product.canBeReportedBy(currentUser))>
                      		<div class="message">
                      			<p>Du kan <a href="product/report?id=${product.id}">rapportera</a> det om det inte är korrekt.</p>
                      		</div>
                      		<br/>
                      	</#if>
                      	                   
                        <div id="product-info-edit" class="mb clearfix">
                        	<div class="left" class="clearfix">
                        		<div class="field">
								    <label for="company">Producent:</label>
								    <div class="text">
								        <h4 id="company"><a href="${cp}spring/productList/filterByCompany?company=${product.company?url('utf-8')}"><#if product.company??>${product.company?html}</#if></a></h4>
								    </div>
								</div>
                        		<div class="field">
								    <label for="name">Namn:</label>
								    <div class="text">
								        <h3 id="name">${product.name?xhtml}</h3>
								    </div>
								</div>
								<div class="field">
								    <label for="category">Kategori:</label>
								    <div class="text">
								        <h4 id="category"><a href="${cp}spring/productList/filterByCategory?category=${product.category.id?url('utf-8')}"><#if product.category??>${product.category.name?html}</#if></a></h4>
								    </div>
								</div>		
								<div class="field">
									<label for="barcode">Streckkod:</label>
								    <div class="text">
								        <h4 id="barcode"><#if product.barcode??>${product.barcode}</#if></a></h4>
								    </div>
									<p id="product-barcode-img"></p>
									<#if product.barcode??>
			                        <script type="text/javascript">
				                        //<![CDATA[
				                            lib.ready(function() {
				                                showBarcode("#product-barcode-img", '${product.barcode}', 'ean13');
				                            })
				                        //]]>
				                        </script>  								
			                        </#if>														
								</div>
							</div>
						
                            <div class="right clearfix">
                                <div class="ico comment">
                                	<a href="#comment"><@spring.message code="product.comments.title" arguments="${product.comments?size}" /></a>
                                </div>
                                <#include "_productHistory.ftl" />
								<div id="fb-root"></div>
								<script>(function(d, s, id) {
								  var js, fjs = d.getElementsByTagName(s)[0];
								  if (d.getElementById(id)) {return;}
								  js = d.createElement(s); js.id = id;
								  js.src = "//connect.facebook.net/en_US/all.js#xfbml=1&appId=148759295225845";
								  fjs.parentNode.insertBefore(js, fjs);
								}(document, 'script', 'facebook-jssdk'));</script>	                                
                                <div class="fb-like" data-href="http://<@spring.message code="site.domain" />${cp}spring/product?id=${product.id}" data-send="false" data-layout="button_count" data-width="450" data-show-faces="false"></div>
                            </div>
                        </div>
                        
                        <hr />    
                        
						<#if (product.conservants?? && product.conservants?size > 0)>
	                    	<h2 class="mb"><@spring.message code="product.detectedAdditives"/></h2>   	
							<@tiles.insertAttribute name="eTable" />
						<#else/>
							<h2 class="mb"><@spring.message code="product.noE"/></h2>
						</#if>
						
						<div class="mb fr clearfix">
                            <div class="addthis_toolbox addthis_default_style">
                                <a href="http://addthis.com/bookmark.php?v=250&amp;username=xa-4bfe640360a8a9a8" class="js addthis_button_compact">Share</a>
                                <span class="addthis_separator">|</span>
                                <a class="nd addthis_button_facebook"></a>
                                <a class="nd addthis_button_myspace"></a>
                                <a class="nd addthis_button_google"></a>
                                <a class="nd addthis_button_twitter"></a>
                            </div>
                            <script type="text/javascript" src="http://s7.addthis.com/js/250/addthis_widget.js#username=xa-4bfe640360a8a9a8"></script>
						</div>
                        
						<@tiles.insertAttribute name="commentForm" />
						<#include "_comments.ftl" />                        
                    </div>                                        
                    <@tiles.insertAttribute name="categoryList" />