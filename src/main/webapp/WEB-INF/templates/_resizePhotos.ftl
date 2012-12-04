<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"]/>
<#assign form=JspTaglibs["http://www.springframework.org/tags/form"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="crop-editing" class="editing clearfix">
                        <h2 class="ac mb"><@spring.message code="crop.cropPhoto" /></h2>
                        
                        <@form.form modelAttribute="product" id="photos-resize" enctype="multipart/form-data">
						<#if product.label?? && product.label.originalPhoto?? && (product.label.originalPhoto?length > 0)>
                            <div id="crop-image" class="crop-widget mb clearfix">
                                <div class="mbh clearfix">
                                    <div class="large">
                                        <img src="${cp}files/${product.label.resizedPhoto}?${timestamp}" width="456" alt="<@spring.message code="crop.originalPhoto"/>" id="origPhoto" class="image" />
                                        <div class="mth clearfix">
                                            <div class="fl">
                                                <input type="submit" value="-90&deg;" name="_eventId_turnLabelLeft"/>
                                                <input type="submit" value="+90&deg;" name="_eventId_turnLabelRight"/>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="small">
                                    	<img src="${cp}files/${product.label.resizedPhoto}?${timestamp}" alt="<@spring.message code="crop.croppedPhoto"/>" id="croppedPhoto" class="thumbnail" />
                                    </div>
                                </div>

                                <@form.hidden path="label.cropping.x1" />
                                <@form.hidden path="label.cropping.y1" />
                                <@form.hidden path="label.cropping.x2" />
                                <@form.hidden path="label.cropping.y2" />
                                <@form.hidden path="label.cropping.width" />
                                <@form.hidden path="label.cropping.height" />
                            </div>
                            <script type="text/javascript">
                            // <[![CDATA
                                lib.ready(function() {
                                    lib.widget.crop.run(lib.dom.byId("crop-image"), {
                                        aspectRatio: 1,
                                        thumbnailDimensions: {
                                            width: 217,
                                            height: 217
                                        },
                                        naturalDimensions: {
                                            width: ${product.label.width},
                                            height: ${product.label.height}
                                        },
                                        output: {
                                            x: "label.cropping.x1",
                                            y: "label.cropping.y1",
                                            x2: "label.cropping.x2",
                                            y2: "label.cropping.y2",
                                            w: "label.cropping.width",
                                            h: "label.cropping.height"
                                        },
                                        setSelect: [${product.label.cropping.x1}*456/${product.label.width},${product.label.cropping.y1}*456/${product.label.width},${product.label.cropping.x2}*456/${product.label.width},${product.label.cropping.y2}*456/${product.label.width}]
                                    });
                                })
                            // ]]>
                            </script>
                            </#if>
                            <#if product.ingredients?? && product.ingredients.originalPhoto?? && (product.ingredients.originalPhoto?length > 0)>
                            <div id="crop-ingredients" class="crop-widget mb clearfix">
                                <div class="mbh clearfix">
                                    <div class="large">
                                        <img src="${cp}files/${product.ingredients.resizedPhoto}?${timestamp}" alt="<@spring.message code="createproduct.form.conservantsPicture"/>" id="conservantsPhoto" class="image" />
                                        <div class="mth clearfix">
                                            <div class="fl">
                                                <input type="submit" value="-90&deg;" name="_eventId_turnIngredientsLeft"/>
                                                <input type="submit" value="+90&deg;" name="_eventId_turnIngredientsRight"/>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div id="crop-editing-ingredients-photo" class="overflow-drag-widget small overflow">
                                        <div class="overflow-container">
                                            <img src="${cp}files/${product.ingredients.resizedPhoto}?${timestamp}" alt="<@spring.message code="createproduct.form.conservantsPicture"/>" class="thumbnail" />
                                        </div>
                                    </div>
                                    <script type="text/javascript">
                                    // <![CDATA[
                                    lib.ready(function() {
                                        lib.widget.overflowDrag.run(lib.dom.byId("crop-editing-ingredients-photo"));
                                    });
                                    // ]]>
                                    </script>
                                </div>
                                
                                <@form.hidden path="ingredients.cropping.x1" />
                                <@form.hidden path="ingredients.cropping.y1" />
                                <@form.hidden path="ingredients.cropping.x2" />
                                <@form.hidden path="ingredients.cropping.y2" />
                                <@form.hidden path="ingredients.cropping.width" />
                                <@form.hidden path="ingredients.cropping.height" />
                                <@form.hidden path="ingredients.height" />
                                <@form.hidden path="ingredients.width" />
                            </div>
                            <script type="text/javascript">
                            // <[![CDATA
                            	var imgH = ${product.ingredients.height};
                            	var imgW = ${product.ingredients.width};
                            	var boxWidth = 456;
                            	var boxHeight = 456;

                            	var minW = boxWidth * boxWidth / imgW;
                            	var minH = boxHeight * boxHeight / imgH;
                            	if (minW > minH) {
                            		minW = minH;
                            	} else {
                            		minH = minW;
                            	}
                            	
                                lib.ready(function() {
                                    lib.widget.crop.run(lib.dom.byId("crop-ingredients"), {
                                    	boxWidth: boxWidth,
                                    	boxHeight: boxHeight,
                                        thumbnailDimensions: {
                                            width: boxWidth,
                                            height: boxWidth
                                        },
                                        naturalDimensions: {
                                            width: imgW,
                                            height: imgH
                                        },
                                        output: {
                                            x: "ingredients.cropping.x1",
                                            y: "ingredients.cropping.y1",
                                            x2: "ingredients.cropping.x2",
                                            y2: "ingredients.cropping.y2",
                                            w: "ingredients.cropping.width",
                                            h: "ingredients.cropping.height"
                                        },
                                        crop: true,
                                        setSelect: [${product.ingredients.cropping.x1},${product.ingredients.cropping.y1},${product.ingredients.cropping.x2},${product.ingredients.cropping.y2}],
                                        minSize: [minW,minH]
                                    });
                                })
                            // ]]>
                            </script>
                            </#if>
                            <div class="ac">
                            	<input type="submit" name="_eventId_cancel" value="<@spring.message code="createproduct.form.back" />" />
                            	<input type="submit" name="_eventId_submit" value="<@spring.message code="product.next"/>" />
                            </div>
                        </@form.form>
                    </div>