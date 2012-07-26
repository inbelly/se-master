<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<#if product??>

  <meta property="fb:app_id"      content="148759295225845" /> 
  <meta property="og:type"        content="inbelly:product" /> 
  <meta property="og:url"         content="http://<@spring.message code="site.domain" />${cp}spring/product?id=${product.id}" />
  <meta property="og:title"       content="${product.name?xhtml} by ${product.company?xhtml}" /> 
  <meta property="og:description" content="presented by InBelly - Campaign for safer food" /> 
  <meta property="og:image"       content="<#if (product.label?? && product.label.photo?exists && product.label.photo?length > 0)>http://<@spring.message code="site.domain" />${cp}files/${product.label.photo}<#else/>${cp}images/product.png</#if>" />

</#if>