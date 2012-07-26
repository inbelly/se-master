<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

<div id="search" class="clearfix">
    <form id="search-form" method="get" action="${cp}spring/search">
        <input id="q" type="text" name="q" class="search-input" />
        <button type="submit">Search</button>
    </form>
    <p id="search-note" class="note"><small>It is easier if you enter a barcode number</small></p>
</div>

<script type="text/javascript">
// <![CDATA[
lib.widget.search.run(lib.dom.byId("search-form"), { placeholder: "CHECK YOUR FOOD!" });
// ]]>
</script>