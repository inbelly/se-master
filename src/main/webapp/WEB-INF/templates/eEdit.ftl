<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="conservant" class="editing inline">
                        <h3><@spring.message code="eTable.conservantCodeTitle" /> ${e.number}</h3>
                      	<div id="conservant-info-edit" class="mb clearfix">
                      	  <form class="clearfix" action="${cp}spring/e/edit" method="POST">
                            <div class="left" class="clearfix">
                                <input name="eid" type="hidden" value="${e.id}"/> 
                                <table class="edit-form-vertical">
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="approved"><@spring.message code="eform.approved" /></label></td>
                                        <td>
                                            <input id="approved" name="approved" type="checkbox" value="approved" <#if e.approved>checked="checked"</#if>></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="category"><@spring.message code="eform.eCategory" /></label></td>
                                        <td>
                                            <select id="category" name="category">
                                                <#list e.hazardDescriptions?keys as c>
                                                    <option value="${c}" <#if c==e.category>selected="selected"</#if>><@spring.message code="hazard.${c}.desc"  /></option>
                                                </#list>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
								        <td class="edit-form-vertical-name"><label for="name"><@spring.message code="eform.eName" /></label></td>
								        <td>
								            <input id="name" name="name" type="text" value="${e.name?default("")}" size="64"></input>
								        </td>
								    </tr>
								    <tr>
                                        <td class="edit-form-vertical-name"><label for="nameEnglish"><@spring.message code="eform.eEnglishName" /></label></td>
                                        <td>
                                            <input id="nameEnglish" name="nameEnglish" type="text" value="${e.nameEnglish?default("")}" size="64"></input>
                                        </td>
                                    </tr>
								    
								    <tr>
                                        <td class="edit-form-vertical-name"><label><@spring.message code="eform.eAliases" /></label></td>
                                        <td>
                                            <textarea id="aliases" name="aliases" rows="4" cols="80">${e.aliases?default("")}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="function"><@spring.message code="eform.eFunction" /></label></td>
                                        <td>
                                            <input id="function" name="function" type="text" value="${e.function?default("")}" size="64"></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="properties"><@spring.message code="eform.eProperties" /></label></td>
                                        <td>
                                            <textarea id="properties" name="properties" rows="4" cols="80">${e.properties?default("")}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="origin"><@spring.message code="eform.eOrigin" /></label></td>
                                        <td>
                                            <textarea id="origin" name="origin" rows="4" cols="80">${e.origin?default("")}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="sideEffect"><@spring.message code="eform.eSideEffects" /></label></td>
                                        <td>
                                            <textarea id="sideEffect" name="sideEffect" rows="4" cols="80">${e.sideEffect?default("")}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="diseases"><@spring.message code="eform.eDiseases" /></label></td>
                                        <td>
                                            <textarea id="diseases" name="diseases" rows="4" cols="80">${e.diseases?default("")}</textarea>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="linksDiseases"><@spring.message code="eform.eDiseasesLink" /></label></td>
                                        <td>
                                            <input id="linksDiseases" name="linksDiseases" type="text" value="${e.linksDiseases?default("")}" size="64"></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="linksBanned"><@spring.message code="eform.eBannedLink" /></label></td>
                                        <td>
                                            <input id="linksBanned" name="linksBanned" type="text" value="${e.linksBanned?default("")}" size="64"></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="vegan"><@spring.message code="eform.eVegan" /></label></td>
                                        <td>
                                            <input id="vegan" name="vegan" type="checkbox" value="vegan" <#if e.vegan>checked="checked"</#if>></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="edit-form-vertical-name"><label for="gmo"><@spring.message code="eform.eGmo" /></label></td>
                                        <td>
                                            <input id="gmo" name="gmo" type="checkbox" value="gmo" <#if e.gmo>checked="checked"</#if>></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <label for="bannedInUsa"><@spring.message code="eform.eBanned" />: <@spring.message code="eform.bannedInUsa" />:</label>
                                            <input id="bannedInUsa" name="bannedInUsa" type="checkbox" value="bannedInUsa" <#if e.bannedInUsa>checked="checked"</#if>></input>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <label for="bannedInCanada"><@spring.message code="eform.bannedInCanada" /></label>
                                            <input id="bannedInCanada" name="bannedInCanada" type="checkbox" value="bannedInCanada" <#if e.bannedInCanada>checked="checked"</#if>></input>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <label for="bannedInAustralia"><@spring.message code="eform.bannedInAustralia" /></label>
                                            <input id="bannedInAustralia" name="bannedInAustralia" type="checkbox" value="bannedInAustralia" <#if e.bannedInAustralia>checked="checked"</#if>></input>
                                        </td>
                                    </tr>
                                </table>
								<div class="field">
                                    <button id="eEditSubmit" type="submit" name="eEditSubmit"><@spring.message code="messages.save"/></button>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="${cp}spring/eList"><@spring.message code="messages.close"/></a>
                                </div>  
                            </div>
                        </div>      
                     </div>                         
                       