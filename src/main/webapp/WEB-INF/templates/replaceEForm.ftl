<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<@compress single_line=true>
<div id="body">
	<div>
	<@form.form modelAttribute="form">	
		<@spring.message code="replaceEForm.description" arguments="${e.number}, ${e.name?xhtml}"/>
		<select name="replacement">
			<#list list as ee>
				<#if ee.id != e.id>
					<option value="${ee.id}">${ee.number} (${ee.name?xhtml})</option>
				</#if>
			</#list>
		</select>
		<input type="submit" name="_eventId_replace" value="<@spring.message code="replaceEForm.replace" />"/>
	</@form.form>
	</div>
	<div>
		<a href="eList"><@spring.message code="createproduct.form.cancel"/></a>
	</div>
</div>
</@compress>