<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="photos-editing" class="editing">
                        <h2 class="ac mb"><#if thanks?? && thanks>Denna produkt \u00e4r \u00e4nnu inte i v\u00e5r databas. <@spring.message code="thankyoumessage" /><br /></#if>Ladda upp bilder p\u00e5 produkten och dess ingredienser, tack!</h2>
                        
                        <@form.form modelAttribute="product" cssClass="simple clearfix" id="photos-upload" enctype="multipart/form-data">
                        
                            <div class="field clearfix">
                                <@form.label path="labelPhoto" cssErrorClass="errorMsg"><@spring.message code="createproduct.form.picture"/></@form.label>
                                <div class="input">
									<input type="file" name="labelPhoto" accept="image/jpeg" size="50" id="f-product" />
                                    <p>Snälla, <strong>*.jpg, *.png, *.gif</strong> filer.</p>
                                    <@form.errors path="labelPhoto" element="p" cssClass="errorMsg"/>
                                </div>
                            </div>
                            <div class="field clearfix">
                                <@form.label path="ingredientsPhoto" cssErrorClass="errorMsg"><@spring.message code="createproduct.form.conservantsPicture"/></@form.label>
                                <div class="input">
									<input type="file" name="ingredientsPhoto" accept="image/jpeg" size="50" id="f-ingredients" />
                                    <p>Sn\u00e4lla, <strong>*.jpg, *.png, *.gif</strong> filer. Se till att det \u00e4r en stor och l\u00e4ttl\u00e4st foto.</p>
                                    <@form.errors path="ingredientsPhoto" element="p" cssClass="errorMsg"/>
                                </div>
                            </div>
                            <div class="field nmb clearfix">
                                <span class="label">&nbsp;</span>
                                <div class="input">
                                	<input type="submit" name="_eventId_cancel" value="<@spring.message code="createproduct.form.back" />" />
                                    <input type="submit" name="_eventId_submit" value="<@spring.message code="product.next"/>" />
                                </div>
                            </div>
						</@form.form>                        
                    </div>
