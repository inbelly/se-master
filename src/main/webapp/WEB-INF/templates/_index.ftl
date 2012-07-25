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
							<p>No products</p>
						</#if>
					</div>
					
                    <div id="blog">
                        <#assign smallHelp = true />
	   	        <#include "_helpus.ftl" />   
                    	<div class="post clearfix">
			<p>The <strong>idea</strong> of Inbelly is to gather all the info about food additive (colors, preservatives, flavor enhancers and other E numbers) as well as possible consequences of their consumption and show the products containing them.</p> 

			<p>The data <strong>sources</strong> include both food safety agencies from other countries and databases such as PUBmed.</p>

			<p>The <strong>goal</strong> is to encourage food manufacturers to be socially responsible and reduce the usage of hazardous food additives in the food industry.</p> 

			<p>The ultimate <strong>challenge</strong> is to promote a ban on additives that have been proven hazardous and banned in other countries in the list of additives that are officially allowed in the EU.</p>

			<p><strong>Join</strong> our campaign and <strong>add</strong> a product to see what you are eating. By doing this you will help others see what they are eating as well.</p>
 			<p><a href="http://blog.inbelly.co.uk/?p=18#respond">Tell us what you think!</a></p>                   	

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
                    
