<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#if ! conservants?? && (product?? && product.conservants??)>
	<#assign conservants = product.conservants />
<#elseif ! conservants?? && searchResults??>
	<#assign conservants = searchResults.conservants />
</#if>	

<#if (! conservants??) && searchResults?? && searchResults.conservants??>
	<#assign conservants = searchResults.conservants />
</#if>

<#-- webflow interop hack!!! -->
<#if currentUser?? && currentUser.principal?? >
	<#assign currentUser = currentUser.principal />
</#if>
		<div id="eTable">
				<#if conservants??>
					<#assign haveNotApproved = false />
						<div id="additives" class="mb">
					<#if reverse?? && reverse>
						<#assign preparedList = conservants?reverse />
					<#else/>
						<#assign preparedList = conservants />
					</#if>
					<#list preparedList as e >
						<#assign haveNotApproved = haveNotApproved || ! e.approved />
                        
                            <div class="additive clearfix <#if !createProductInProgress?? && !e.approved>notapproved</#if><#if !e_has_next> last</#if>">
                            	<div class="additive-info-wrapper clearfix">
	                                <div class="additive-info-quick">
										<div class="additive-admin">
		                                    <#if product??>
													<#if editing?? && editing && product.canBeEditedBy(currentUser)>
														<p class="action-delete">					
															<a class="red" href="${cp}spring/product/remove?eid=${e.id}&id=${product.id}" onclick="return confirm('<@spring.message code="confirm.really" />');"><@spring.message code="eTable.deleteFromProduct"/></a>
														</p>
													</#if>
											<#else>
													<#if currentUser?? && currentUser.admin>
													<p class="action-delete">					
														<a class="red" href="${cp}spring/e?_eventId=edit&amp;eid=${e.id}"><@spring.message code="eTable.edit"/></a>
													</p>
													<#if !e.approved>
														<p class="action-delete">					
															<a class="red" href="${cp}spring/e/approve?eid=${e.id}" onclick="return confirm('<@spring.message code="confirm.really" />');"><@spring.message code="eTable.confirm"/></a>
														</p>
													</#if>
													<#if 0 == e.productCount >
														<p class="action-delete">					
															<a class="red" href="${cp}spring/e/delete?eid=${e.id}" onclick="return confirm('<@spring.message code="confirm.really" />');"><@spring.message code="eTable.delete"/></a>
														</p>
													</#if>
													</#if>
											</#if>
										</div>
	                                	<div>
	                                    <img src="${cp}images/hazard-${e.category}.png" width="45" height="45" alt="<@spring.message code="${hazardDescriptions[e.category]}" />" title="<@spring.message code="${hazardDescriptions[e.category]}" />" class="hazard" />
	                                    <h3><span>${e.number}</span></h3>
	<#--                                    
										<#if e.vegan>
											<p><img title="<@spring.message code="eTable.notVegan"/>" alt="<@spring.message code="eTable.notVegan"/>" src="${cp}img/notVegan.png" width="28" height="20"/></p>
										</#if>    
	-->									                                
	                                    <p>${e.name?xhtml} <#if (e.aliases?? && e.aliases?length > 0)>(${e.aliases?xhtml})</#if></p>
	                                    <p class="nmb"><strong><a href="${cp}spring/productList/filterByE?eid=${e.id}&amp;name=${e.number?url('utf-8')}"><@spring.message code="eTable.detectedInProducts" arguments="${e.approvedProductCount}"/></a></strong>
	                                    </div>
	                                </div>
	                                
	                                <div class="additive-info-details">
										<#if (e.diseases?? && e.diseases?length>0)><p class="diseases"><strong><@spring.message code="eTable.diseases"/>: </strong>${e.diseases?xhtml}</p></#if>
	                                    <#if e.origin??><p>${e.origin?xhtml}</p></#if>
										<#if (e.linksDiseases?? && e.linksDiseases?length>0)><p><strong><@spring.message code="eTable.diseasesLinks"/>:</strong> 
											<#list e.linksDiseases?split(' ') as l>
												<a href="${l}">${l}</a>
											</#list>
											</p>
										</#if>
										<#if (e.linksBanned?? && e.linksBanned?length>0)><p><strong><@spring.message code="eTable.bannedLinks"/>:</strong> 
											<#list e.linksBanned?split(' ') as l>
												<a href="${l}">${l}</a>
											</#list>
											</p>
										</#if>
										<#if e.bannedInUsa || e.bannedInCanada || e.bannedInAustralia>
											<p><strong><@spring.message code="eTable.bannedIn"/>: </strong>
											<#if e.bannedInUsa>
												<#if e.isColorant()>
													<a href="http://www.fda.gov/ForIndustry/ColorAdditives/ColorAdditiveInventories/ucm106626.htm"><@spring.message code="eTable.bannedIn.usa"/></a>
												<#else/>
													<a href="http://www.fda.gov/Food/FoodIngredientsPackaging/FoodAdditives/FoodAdditiveListings/ucm091048.htm"><@spring.message code="eTable.bannedIn.usa"/></a>
												</#if>
											</#if>
											<#if e.bannedInCanada>
												<a href="http://www.hc-sc.gc.ca/fn-an/securit/addit/diction/dict_food-alim_add-eng.php"><@spring.message code="eTable.bannedIn.canada"/></a>
											</#if>
											<#if e.bannedInAustralia>
												<a href="http://www.foodstandards.gov.au/newsroom/publications/choosingtherightstuff/foodadditivesnumeric1680.cfm"><@spring.message code="eTable.bannedIn.australia"/></a>
											</#if>
											</p>
										</#if> 
						
										<#if (e.nameEnglish?? && e.nameEnglish?length>0)>
											<p class="secondary"><a href="http://www.ncbi.nlm.nih.gov/sites/entrez?cmd=Search&amp;db=PubMed&amp;term=${e.nameEnglish?url('utf-8')}"><@spring.message code="eTable.moreInfo"/></a></p>
										</#if>
	                                </div>
								</div>
                            </div>
                    </#list>
                    	</div>
				</#if>
			</div>
