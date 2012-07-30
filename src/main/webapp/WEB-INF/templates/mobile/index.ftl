	<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
		<img src="../img/mobile/logo.png" style="vertical-align: top; border: none; padding: 0px;"/> <span style="color: #af6e61; text-transform: uppercase;"><@spring.message code="site.name"/></span>
		<form action="search" method="get">
			<label><@spring.message code="mobile.search"/>:</label>
			<input type="text" name="q"/>
			<input type="submit" value="Sök" name="_eventId_search"/>
		</form>

		<p><@spring.message code="mobile.searchText"/></p>
