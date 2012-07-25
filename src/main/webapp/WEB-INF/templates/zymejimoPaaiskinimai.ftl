<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<@compress>
<div id="body">
	<table border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="60">
				<img src="${cp}images/hazard-0.png"/>
			</td>
			<td><strong><@spring.message code="zymejimoPaaiskinimai.noE"/>:</strong> <@spring.message code="zymejimoPaaiskinimai.noE.desc"/>
			</td>
		</tr>
		<tr>
			<td>
				<img src="${cp}images/hazard-1.png"/>
			</td>
			<td><strong><@spring.message code="zymejimoPaaiskinimai.hazard0"/>:</strong> <@spring.message code="zymejimoPaaiskinimai.hazard0.desc"/> 
			</td>
		</tr>
		<tr>
			<td>
				<img src="${cp}images/hazard-2.png"/>
			</td>
			<td><strong><@spring.message code="zymejimoPaaiskinimai.hazard1"/>:</strong> <@spring.message code="zymejimoPaaiskinimai.hazard1.desc"/> 
			</td>
		</tr>
		<tr>
			<td>
				<img src="${cp}images/hazard-3.png"/>
			</td>
			<td><strong><@spring.message code="zymejimoPaaiskinimai.hazard2"/>:</strong> <@spring.message code="zymejimoPaaiskinimai.hazard2.desc"/>
			</td>
		</tr>
		<tr>
			<td>
				<img src="${cp}images/hazard-4.gif"/>
			</td>
			<td><strong><@spring.message code="zymejimoPaaiskinimai.hazard3"/>: </strong> <@spring.message code="zymejimoPaaiskinimai.hazard3.desc"/> 
			</td>
		</tr>
		<tr>
			<td>
				<img src="${cp}images/notVegan.png"/>
			</td>
			<td><strong><@spring.message code="zymejimoPaaiskinimai.notVegan"/>: </strong> <@spring.message code="zymejimoPaaiskinimai.notVegan.desc"/> 
			</td>
		</tr>
		<tr>
			<td>
				<img src="${cp}images/gmo.png"/>
			</td>
			<td><strong><@spring.message code="zymejimoPaaiskinimai.gmo"/>: </strong> <@spring.message code="zymejimoPaaiskinimai.gmo.desc"/> 
			</td>
		</tr>
	</table>
	<div id="catalog" class="paaiskinimai">
		<p><@spring.message code="zymejimoPaaiskinimai.legal1"/></p>
		<p><@spring.message code="zymejimoPaaiskinimai.legal2"/></p>
	</div>
</div>
</@compress>