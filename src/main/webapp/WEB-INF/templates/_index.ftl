<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

					<div id="products" class="products-list columns-2">
						<#if (products?? && products?size > 0)>
							<#list products as p>
								<div class="product <#if 0 == (p_index + 1) % 2>last</#if>">
									<#if !p.approved>
										<img src="${cp}img/aproval_1.png" alt="not approved" class="not-approved" title="<@spring.message code="productList.productNotApproved" />"/>
									</#if>								
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
			<p><strong>Vår ide</strong> på inBelly är att ge dig all tillgänglig information om den mat du äter, så att du kan fatta välinformerade beslut om vad du vill äta. Vi samlar information om livsmedelstillsatser (färger, konserveringsmedel, smakförstärkare och sk e-nummer) och deras möjliga effekter på människan. Med vår app kan du se vad olika livsmedelsprodukter innehåller, och om de ämnen som ingår är förbjudna i andra länder.</p> 

			<p>Våra <strong>datakällor</strong> myndigheter för livsmedelssäkerhet från andra länder (Kanada, USA, Australien och Nya Zealand) och databaser som PubMed. PubMed är en databas för akademiska publikationer inom området hälso och sjukvård.</p>

			<p>Förutom att ge dig alla verktyg du behöver för att fatta beslut om vad du och de dina äter, vill vi också uppmuntra livsmedelstillverkare att vara socialt ansvarsfulla och minska användningen av tveksamma livsmedelstillsatser inom livsmedelsindustrin.</p> 

			<p><strong>Gå med i</strong> vår kampanj och lägg till en produkt för att se vad du äter. När du gör det så hjälper du också andra att se vad de äter.</p>
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
                    </div>
                    
