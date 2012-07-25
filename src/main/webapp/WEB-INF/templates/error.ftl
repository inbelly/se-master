<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><@spring.message code="site.name"/><@tiles.insertAttribute name="title"/>: <@tiles.insertAttribute name="errMsg" /></title>
	<meta content="text/html; charset=utf-8" http-equiv="content-type" />
	<link rel="shortcut icon" href="${cp}images/favicon.ico" type="image/ico" />        
    <link rel="stylesheet" href="${cp}styles/reset.css" type="text/css" media="all" />
    <link rel="stylesheet" href="${cp}styles/main.css" type="text/css" media="all" />
    <link rel="stylesheet" href="${cp}styles/content.css" type="text/css" media="all" />
	<script type="text/javascript" src="${cp}js/jquery-1.4.2.min.js"></script>
	<script src="${cp}js/functions.js" type="text/javascript" charset="utf-8"></script>
    <script src="${cp}js/lib.js" type="text/javascript" charset="utf-8"></script>
    <script src="${cp}js/widgets.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
        <!--[if lt IE 7]>
            <div id="darkside">
                Please install an updated version of
                <a href="http://www.microsoft.com/windows/internet-explorer/worldwide-sites.aspx">Internet Explorer</a>
                or download another browser, like <a href="http://www.getfirefox.com/">Firefox</a>,
                <a href="http://www.apple.com/safari/">Safari</a>,
                <a href="http://www.google.com/chrome">Chrome</a>,
                <a href="http://www.opera.com/browser/">Opera</a>.
            </div>
        <![endif]-->
        <div id="wrapper-outer">
            <div id="wrapper" class="clearfix">
                <div id="top" class="clearfix">
					<@tiles.insertAttribute name="header"/>
					<@tiles.insertAttribute name="barcode" />
					<@tiles.insertAttribute name="legend" />
				</div>
				<hr class="clear mbh" />
				<div id="content" class="clearfix">
					<h1><@tiles.insertAttribute name="errMsg" /></h1>
				</div>
				<hr />
				<div id="footer" class="clearfix">
					<@tiles.insertAttribute name="footer"/>
				</div>
			</div>
		</div>
		
		<script type="text/javascript">
		var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
		document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
		</script>
		<script type="text/javascript">
		try {
		var pageTracker = _gat._getTracker("UA-1622602-12");
		pageTracker._setDomainName(".inbelly.co.uk");
		pageTracker._trackPageview();
		} catch(err) {}</script>
</body>
</html>