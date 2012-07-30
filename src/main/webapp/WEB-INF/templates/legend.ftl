<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="hazards" class="clear clearfix">
                    	<div class="c top"></div>
                        <ol class="clearfix">
                        	<#list [4, 3, 2, 1, 0] as no>
	                        	<#assign descTxt><@spring.message code="zymejimoPaaiskinimai.hazard${no}.desc"/></#assign>
	                        	<#assign shortTxt><@spring.message code="zymejimoPaaiskinimai.hazard${no}"/></#assign>
	                        	<#if descTxt?length <= 168 >
	                        		<#assign descExtraClass = "center-1" />
	                        	<#elseif descTxt?length <= 336/>
	                        		<#assign descExtraClass = "center-2" />
	                        	<#else/>
	                        		<#assign descExtraClass = "" />
	                        	</#if>
	                        	<#if shortTxt?length < 22>
	                        		<#assign shortExtraClass = "center-1">
	                        	<#else/>
									<#assign shortExtraClass = "center-2">	                        	
	                        	</#if>
	                            <li class="<#if no == 4>last </#if>clearfix">
	                                <a href="${cp}spring/productList/filterByHazard?hazard=${no}"><img src="${cp}images/hazard-${no}.png" width="45" height="45" alt="${shortTxt}" title="${descTxt}"/></a>
	                                <p class="short ${shortExtraClass}">
	                                     <a href="${cp}spring/productList/filterByHazard?hazard=${no}">${shortTxt}</a>
	                                </p>
	                                <p class="${descExtraClass}"><strong>${shortTxt}.</strong> ${descTxt}</p>
	                            </li>
                            </#list>
                        </ol>
                        <p class="toggle fr">
                            <a href="javascript:void(0);" class="toggle-handle js" id="hazards-toggle-handle">Hide</a>
                        </p>
                        <div class="c bottom"></div>
                        
                    </div>
			        <script type="text/javascript">
                    //<![CDATA[
                    (function() {
                        var hazards = lib.dom.byId("hazards"),
                            ol = lib.dom.byTag("ol", hazards)[0],
                            toggle = lib.dom.byId("hazards-toggle-handle");
                        if (ol) lib.dom.addClass(ol, "compact");
                        if (toggle) toggle.innerHTML = "Förklaring";
                        lib.ready(function() {
                            var hazards = lib.dom.byId("hazards");
                            
                            lib.widget.hazards.run(hazards, {show: "Förklaring", hide: "Göm"});
                        });
                    })();
                    //]]>
                    </script>
