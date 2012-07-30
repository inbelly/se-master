<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<div id="search" class="clearfix">
    <form id="search-form" method="get" action="${cp}spring/search">
        <input id="q" type="text" name="q" class="search-input" />
        <button type="submit">Sök</button>
    </form>
    <p id="search-note" class="note"><small>Det är lättare om du anger en streckkod nummer</small></p>
</div>

<script type="text/javascript">
// <![CDATA[
lib.widget.search.run(lib.dom.byId("search-form"), { placeholder: "KONTROLLERA DIN MAT!" });
// ]]>
</script>