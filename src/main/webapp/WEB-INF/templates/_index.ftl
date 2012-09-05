<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

					<div id="products" class="products-list columns-2">
						<#if (products?? && products?size > 0)>
							<#list products as p>
								<div class="product <#if 0 == (p_index + 1) % 2>last</#if>">
									<#include "productItem.ftl"/>
								</div>
								<#if (5 == p_index)>
									<#break/>
								</#if>
							</#list>
							<p><a href="${cp}spring/productList?clear=yes">See all</a></p>
						<#else/>
							<p>Inga produkter</p>
						</#if>
					</div>
					
                    <div id="blog">
                        <#assign smallHelp = true />
	   	        <#include "_helpus.ftl" />   
                    	<div class="post clearfix">
			<p><strong>Idén</strong> om Inbelly är att samla all info om livsmedelstillsats (färger, konserveringsmedel, smakförstärkare och andra numren E) samt möjliga konsekvenser av deras konsumtion och visa de produkter som innehåller dessa.</p> 

			<p>De <strong>datakällor</strong> inkluderar både myndigheter för livsmedelssäkerhet från andra länder och databaser som PubMed.</p>

			<p><strong>Målet</strong> är att uppmuntra livsmedelstillverkare att vara socialt ansvarsfulla och minska användningen av farliga livsmedelstillsatser inom livsmedelsindustrin.</p> 

			<p>Den ultimata <strong>utmaningen</strong> är att främja ett förbud mot tillsatser som har visat farligt och förbjudet i andra länder i förteckningen över tillsatser som är officiellt är tillåtna inom EU.</p>

			<p><strong>Gå med i</strong> vår kampanj och <strong>lägga</strong> till en produkt för att se vad du äter. Genom att göra detta kommer du att hjälpa andra se vad de äter också.</p>
 			<p><a href="http://blog.inbelly.co.uk/?p=18#respond">Berätta vad du tycker!</a></p>                   	

			</div>
<#--                    
                    	<#if feed??>
							<#list feed.entries as entry>
		                        <div class="post clearfix">
			                		<h3><a href="${entry.link}">${entry.title}</a></h3>
			                		<em class="date">${entry.publishedDate?string('yyyy-MM-dd')}</em>
									<p>${entry.description.value}&nbsp;<a href="${entry.link}"><@spring.message code="index.blog.more"/></a></p>
		                        </div>
                        		<#if (entry_index > 2)>
                        			<#break/>
                        		</#if>
                        	</#list>
	                        <script type="text/javascript">
	                        //<![CDATA[
	                            lib.ready(function() {
	                                jQuery('.alignleft.size-full').addClass('image');
	                            })
	                        //]]>
	                        </script>
                        </#if>  
-->                                              
                        <#assign smallHelp = true />
						<#include "_facebook.ftl" />               
                    </div> <!-- test -->
                    
