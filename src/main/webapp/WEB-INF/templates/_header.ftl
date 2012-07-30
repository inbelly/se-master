<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#-- webflow interop hack!!! -->
<#if currentUser?? && currentUser.principal?? >
	<#assign currentUser = currentUser.principal />
</#if>

                    <div id="userbar" class="clearfix">
                	<#if currentUser??>
                    	<p class="fr"><strong>${currentUser.nick}</strong> <#--| <a href="settings">Inställningarna</a>--> | <a href="${cp}spring/logout">Logga ut</a></p>
                	<#else/>
                		<p class="fr"><a href="${cp}spring/login">Logga in</a></p>
                    </#if>
                    </div>
                    <div id="header" class="clearfix">
                        <div id="logo">
                            <a href="${cp}spring/index?clear=yes"><img src="${cp}images/logo.png" width="282" height="74" alt="Inbelly Logo" /></a>
                        </div>
                        <div id="main-menu" class="main-menu fr">
                            <#include "_menu.ftl" />
                        </div>
                    </div>
