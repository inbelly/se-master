<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>                       
                        <ul class="clearfix">
                            <@sec.authorize ifAllGranted="ROLE_ADMIN">
                                <li class="head">
                                    <#if settingsMenu??>
                                        <span><@spring.message code="header.menu.settings"/></span>
                                    <#else/>
                                        <a href="${cp}spring/i18Messages"><@spring.message code="header.menu.settings"/></a>
                                    </#if>
                                </li>
                            </@sec.authorize>
                            <li class="head">
                            	<#if index?? && index>
                            		<span><@spring.message code="header.menu.main"/></span>
                            	<#else/>
                                	<a href="${cp}spring/index"><@spring.message code="header.menu.main"/></a>
                                </#if>
                            </li>
                            <li class="head">
                            	<#if productList?? && productList>
                            		<span><@spring.message code="header.menu.products"/></span>
                            	<#else/>
                                	<a href="${cp}spring/productList?clear=yes"><@spring.message code="header.menu.products"/></a>
                                </#if>
                            </li>
                            <li class="head">
                            	<#if additives?? && additives>
                            		<span><@spring.message code="header.menu.conservants"/></span>
                            	<#else/>
                                	<a href="${cp}spring/eList"><@spring.message code="header.menu.conservants"/></a>
                                </#if>
                            </li>
                            <li class="head last s">
                                <a href="${cp}spring/dataSources"><@spring.message code="header.menu.sources"/></a>
                            </li>
<#--
                            <li class="head last">
                                <a href="${cp}spring/friends"><@spring.message code="header.menu.friends"/></a>
                            </li>
-->                         
                        </ul>
