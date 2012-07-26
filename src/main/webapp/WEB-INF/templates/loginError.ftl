<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<div id="login-editing" class="editing clearfix">
    <p class="ac">
		<h3><@spring.message code="loginError.description"/></h3>
		<p><a href="${cp}spring/reset"><@spring.message code="loginError.lostPassword"/></a></p>
		<p><a href="${cp}spring/register"><@spring.message code="login.registerMessage"/></a></p>
	</p> 
</div>
