<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Sveikas Vaikas<@tiles.insertAttribute name="title"/></title>
		<meta content="text/html; charset=utf-8" http-equiv="content-type" />
	</head>
	<body>
		<@tiles.insertAttribute name="body"/>
		<p style="color: #aaaaaa;"><@spring.message code="footer.copyright" /></p>
	</body>
</html>