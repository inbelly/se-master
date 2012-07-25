<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]>

<div id="login-editing" class="editing clearfix">
	<p><@tiles.insertAttribute name="message" /></p>
	<div id="login" class="register clearfix">
		<p><@tiles.insertAttribute name="control" /></p>
	</div>
</div>
