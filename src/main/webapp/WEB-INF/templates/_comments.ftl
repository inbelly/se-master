<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                        
					<#if (product.comments?? && product.comments?size > 0)>
						<h3 class="mb"><@spring.message code="product.comments.title" arguments="${product.comments?size}"/></h3>
                        <div id="comments">
                        	
                        	<#if criteria.itemsPerPage < product.comments?size >
                            	<@tiles.insertAttribute name="paginator" />
                            </#if>
                            
                            <hr />
                            
                            <#assign start = criteria.page * criteria.itemsPerPage />
                            <#assign end = start + criteria.itemsPerPage />
                            <#list product.comments as comment >
                            	<#if start <= comment_index && comment_index < end >
		                            <div class="comment <#if end == comment_index>mbh</#if> clearfix">
		                            	<div class="comment-info-wrapper clearfix">
			                                <div class="comment-info-quick">
			                                    <h4><#if comment.name??>${comment.name?xhtml}<#else/>Anonymous</#if></h4>
			                                    <#--if comment.email??><p class="email"><a href="">${comment.email}</a></p></#if-->
			                                    <p class="date"><em>${comment.eventTime?string('yyyy-MM-dd HH:mm')}</em></p>
			                                    <p class="date">
		                    						<@sec.authorize ifAllGranted="ROLE_ADMIN">
														<a href="${cp}spring/comment/delete?id=${product.id}&cid=${comment.id}" class="grouping"><@spring.message code="productItem.delete"/></a>						
													</@sec.authorize>
												</p>
			                                </div>
			                                
			                                <div class="comment-info-details">
			                                    <p>${comment.body}</p>
			                                </div>
										</div>
		                            </div>
                            	</#if>
                            	<#if end <= comment_index >
                            		<#break/>
                            	</#if>
                            </#list>
                            
                        	<#if criteria.itemsPerPage < product.comments?size >
                            	<@tiles.insertAttribute name="paginator" />
                            </#if>
                        </div>
					<#else/>
						<h3 class="mb"><@spring.message code="product.comments.noComments"/></h3>                        
					</#if>