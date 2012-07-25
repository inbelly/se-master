<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>
<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>

                    <div id="footer-menu" class="main-menu fl">
						<#include "_menu.ftl" />
                    </div>
                    
                    <div id="footer-text" class="clear">
                        <p class="mb">
                            <small>
                                <strong><@spring.message code="footer.contactUs" /></strong>
                            </small>
                        </p>
                        
                        <p><small><@spring.message code="footer.legal" /></small></p>
                        
                        <p>
                        	<small><@spring.message code="footer.copyright" />
							<a href="http://validator.w3.org/check?uri=referer">
	                        	<!-- img src="http://www.w3.org/Icons/valid-xhtml10"
	        						alt="Valid XHTML 1.0 Transitional" height="15" width="44" / -->
	        				</a>
	        				</small>
	        			</p>
                        
                        <p class="fr">
                            <a href="mailto:info@inbelly.co.uk">Contact us</a>
                        </p>

			<p><small>${build.tag}</small></p>
                    </div>
