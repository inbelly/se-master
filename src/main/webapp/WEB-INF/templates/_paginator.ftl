<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>

<#macro pageLink page label>
	<a href="${cp}spring/<@tiles.insertAttribute name="paginatorURI" />?page=${page}<#if criteria.q?? && 0 < criteria.q?length>&q=${criteria.q}</#if>">${label}</a>
</#macro>

	                        <div class="pagination clear clearfix">
	                        	<div class="label"><@spring.message code="paginator.totalPages" arguments="${criteria.totalPages}"/>:</div>
	                            <div class="pages">
									<#assign start = criteria.page - 7/>
									<#if (start < 0)>
										<#assign start = 0 />
									</#if>
									<#if (criteria.page > 0)>
										<#assign page = criteria.page -1 />
										<#assign label><@spring.message code="paginator.back" /></#assign>
										<@pageLink page label />
										<span>&nbsp;</span>
									</#if>
									<#list 0..14 as i>
										<#assign page = start + i/>
										<#if page==criteria.page>
											<strong>${page+1}</strong>
										<#else/>
											<@pageLink page page+1/>
										</#if>
										<#if ((page + 1) == criteria.totalPages)>
											<#break/>
										</#if>
									</#list>
									<#if (criteria.page < criteria.totalPages-1)>
										<#assign page = criteria.page + 1 />
										<#assign label><@spring.message code="paginator.forward" /></#assign>
										<span>&nbsp;</span>
										<@pageLink page label />
									</#if>
								</div>
	                            <div class="limit">
									<span>Per sida:</span>
									<#list [6, 24, 60] as pageSize>
										<#if pageSize==criteria.itemsPerPage>
											<strong>${pageSize}</strong>
										<#else/>
											<a href="${cp}spring/<@tiles.insertAttribute name="paginatorURI" />?pageSize=${pageSize}<#if criteria.q?? && 0 < criteria.q?length>&q=${criteria.q}</#if>">${pageSize}</a>
										</#if>
									</#list>	                            
	                            </div>
	                        </div>	
