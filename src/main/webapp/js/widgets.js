lib.widget({
    name: "menu",
    events: {
        focus: [],
        blur: []
    },
    
    construct: function() {
        lib.dom.addClass(this.element, "initialized");
        
        var a = lib.dom.byTag("a", this.element);
        for (var i = 0; i < a.length; i++) {
            var focus = function(event) {
                    var head = lib.dom.parent(event.target, "head");
                    if (!lib.dom.hasClass(head, "hover")) {
                        lib.dom.addClass(head, "hover");
                    }
                    
                    if (!lib.dom.hasClass(event.target, "hover")) {
                        lib.dom.addClass(event.target, "hover");
                    }
                },
                blur = function(event) {
                    var head = lib.dom.parent(event.target, "head");
                    if (lib.dom.hasClass(head, "hover")) {
                        lib.dom.removeClass(head, "hover");
                    }
                    
                    if (lib.dom.hasClass(event.target, "hover")) {
                        lib.dom.removeClass(event.target, "hover");
                    }
                };
            lib.event.add(a[i], "focus", focus);
            lib.event.add(a[i], "blur", blur);
            
            this.events.focus.push({elem: a[i], callback: focus});
            this.events.blur.push({elem: a[i], callback: blur});
        }
    },
    
    destroy: function() {
        for (var i = 0; i < this.events.focus.length; i++) {
            lib.event.remove(this.events.focus[i].elem, "focus", this.events.focus[i].callback);
            lib.event.remove(this.events.blur[i].elem, "blur", this.events.blur[i].callback);
        }
    }
});

lib.ready(function() {
    var elems = [];
    elems.push(lib.dom.byId("main-menu"));
    elems.push(lib.dom.byId("footer-menu"));
    lib.widget.menu.run(elems);
    //lib.widget.rating.run(lib.dom.byClass("rating-widget"));
    lib.widget.search.run(lib.dom.byId("search-form"), { placeholder: "KONTROLLERA DIN MAT!" });
})

lib.widget({
    name: "hazards",
    
    hide: "Hide",
    show: "Show",
    
    event: null,
    
    construct: function() {
        var ol = lib.dom.byTag("ol", this.element)[0],
            handle = lib.dom.byClass("toggle-handle", this.element)[0];
        
        lib.dom.addClass(ol, "compact");
        handle.innerHTML = this.show;
        
        this.event = lib.bind(function(event) {
            if (lib.dom.hasClass(ol, "compact")) {
                lib.dom.removeClass(ol, "compact");
                handle.innerHTML = this.hide;
            } else {
                lib.dom.addClass(ol, "compact");
                handle.innerHTML = this.show;
            }
            
            return false;
        }, this);
        lib.event.add(handle, "click", this.event);
    },
    
    destroy: function() {
        this.event.remove(handle, "click", this.event);
    }
});

lib.widget({
    name: "addFriend",
    
    event: null,
    
    construct: function() {
        var form = lib.dom.byTag("form", this.element)[0],
            handle = lib.dom.byClass("toggle-handle", this.element)[0];
        
        lib.dom.addClass(form, "hidden");
        
        this.event = lib.bind(function(event) {
            if (lib.dom.hasClass(form, "hidden")) {
                lib.dom.removeClass(form, "hidden");
            } else {
                lib.dom.addClass(form, "hidden");
            }
        }, this);
        lib.event.add(handle, "click", this.event);
    },
    
    destroy: function() {
        this.event.remove(handle, "click", this.event);
    }
});

lib.widget({
    name: "termsAndConditions",
    
    event: null,
    
    construct: function() {
        var ul = lib.dom.byTag("ul", this.element)[0],
            handle = lib.dom.byClass("toggle-handle", this.element)[0];
        
        lib.dom.addClass(ul, "hidden");
        
        this.event = lib.bind(function(event) {
            if (lib.dom.hasClass(ul, "hidden")) {
                lib.dom.removeClass(ul, "hidden");
            } else {
                lib.dom.addClass(ul, "hidden");
            }
        }, this);
        lib.event.add(handle, "click", this.event);
    },
    
    destroy: function() {
        this.event.remove(handle, "click", this.event);
    }
});

lib.widget({
    name: "overflowDrag",
    img: null,
    drag: false,
    start: {},
    events: {},
    
    construct: function() {
        this.img = lib.dom.byTag("img", this.element)[0];
        this.img.style.cursor = "move";
        
        this.events = {
            mousedown: lib.bind(this.mousedown, this),
            mouseup: lib.bind(this.mouseup, this),
            mousemove: lib.bind(this.mousemove, this)
        };
        
        lib.event.add(document, "mousedown", this.events.mousedown);
        lib.event.add(document, "mouseup", this.events.mouseup);
        lib.event.add(document, "mousemove", this.events.mousemove);
    },
    
    destroy: function() {
        lib.event.remove(document, "mousedown", this.events.mousedown);
        lib.event.remove(document, "mouseup", this.events.mouseup);
        lib.event.remove(document, "mousemove", this.events.mousemove);
    },
    
    mousedown: function(event) {
        if (event.target == this.img) {
            this.drag = true;
            this.start.clientX = event.clientX + this.element.scrollLeft;
            this.start.clientY = event.clientY + this.element.scrollTop;
            event.preventDefault();
        }
    },
    
    mouseup: function(event) {
        this.drag = false;
    },
    
    mousemove: function(event) {
        if (this.drag) {
            this.element.scrollLeft = this.start.clientX - event.clientX;
            this.element.scrollTop = this.start.clientY - event.clientY;
            event.preventDefault();
        }
    }
});

lib.ready(function() {
    //lib.widget.overflowDrag.run(lib.dom.byClass("overflow-drag-widget"));
})

lib.widget({
    name: "rating",
    inputs: [],
    events: {},
    checked: 0,
    vote: null,
    
    construct: function() {
        var inputs = lib.dom.byTag("input", this.element),
            button = lib.dom.byTag("button", this.element)[0];
        
        if (button) lib.dom.addClass(button, "hidden");
        
        for (var i = 0; i < inputs.length; i++) {
            if (inputs[i].type == "radio" && inputs[i].disabled == false) {
                inputs[i].name += "[" + i + "]";
                this.inputs.push(inputs[i]);
                
                if (inputs[i].checked) {
                    this.checked = this.inputs.length;
                    for (var j = 0; j < this.inputs.length - 1; j++) {
                        this.inputs[j].checked = true;
                    }
                }
            } else if (inputs[i].type == "hidden") {
                this.vote = inputs[i];
            }
        }
        
        this.events = {
            mouseover: lib.bind(this.mouseover, this),
            mouseout: lib.bind(this.mouseout, this),
            click: lib.bind(this.click, this)
        };
        
        lib.event.add(this.element, "mouseover", this.events.mouseover);
        lib.event.add(this.element, "mouseout", this.events.mouseout);
        lib.event.add(this.element, "click", this.events.click);
    },
    
    destroy: function() {
        lib.event.remove(this.element, "mouseover", this.events.mouseover);
        lib.event.remove(this.element, "mouseout", this.events.mouseout);
        lib.event.remove(this.element, "click", this.events.click);
    },
    
    mouseover: function(event) {
        var n = 0;
        for (var i = 0; i < this.inputs.length; i++) {
            if (this.inputs[i] === event.target) {
                n = ++i;
                break;
            }
        }
        
        if (n > 0) {
            for (var i = 0; i < this.inputs.length; i++) {
                if (i < n) {
                    this.inputs[i].checked = true;
                } else {
                    this.inputs[i].checked = false;
                }
            }
        }
    },
    
    mouseout: function(event) {
        if (event.target && lib.dom.isChild(event.target, this.element)
            && event.relatedTarget && !lib.dom.isChild(event.relatedTarget, this.element)) {
            for (var i = 0; i < this.inputs.length; i++) {
                if (i < this.checked) {
                    this.inputs[i].checked = true;
                } else {
                    this.inputs[i].checked = false;
                }
            }
        }
    },
    
    click: function(event) {
        for (var i = 0; i < this.inputs.length; i++) {
            if (this.inputs[i] === event.target) {
                this.vote.value = ++i;
                this.element.submit();
                break;
            }
        }
    }
});

lib.widget({
    name: "crop",
    
    image: null,
    thumbnail: null,
    form: null,
    
    aspectRatio: false,
    thumbnailDimensions: { width: 217, height: 217 },
    naturalDimensions: { width: null, height: null },
    crop: false,
    output: false,
    
    timeout: null,
    height: null,
    
    boxWidth: 456,
    boxHeight: 456,
    
    minSize: [0, 0],
    
    setSelect: [null, null, null, null],
    
    construct: function() {
        this.image = lib.dom.byClass("image", this.element)[0];
        this.thumbnail = lib.dom.byClass("thumbnail", this.element)[0];
        this.form = lib.dom.parent(this.element, "", "form");
        
        function initCrop() {
            var qx = this.image.width / 4,
                qy = this.height / 4;
            
            if (null == this.setSelect[0]) {
            	this.setSelect = [qx, qy, qx * 3, qy * 3]; 
            }
            
            var jcropapi = 
            	jQuery(this.image).Jcrop({
	                aspectRatio: this.aspectRatio,
	                onSelect: lib.bind(this.preview, this),
	                onChange: lib.bind(this.preview, this),
	                setSelect: this.setSelect,
	                boxWidth: this.boxWidth,
	                boxHeight: this.boxHeight,
	                bgOpacity: .6,
	                minSize: this.minSize
	            });
        };
        
        if (this.image.height > 0) {
            this.height = this.image.height;
            initCrop.apply(this);
        } else {
            var interval = window.setInterval(lib.bind(function() {
                if (this.image.height > 0) {
                    window.clearInterval(interval);
                    this.height = this.image.height;
                    initCrop.apply(this);
                }
            }, this), 10);
        }
    },
    
    updateThumbnail: function(image, thumbnail, coords) {
        var rx = this.thumbnailDimensions.width / coords.w,
            ry = this.thumbnailDimensions.height / coords.h,
            ratio = this.naturalDimensions.width / this.image.width;
        
        if (!this.crop) {
            this.thumbnail.style.width = Math.round(rx * this.image.width) + "px";
            this.thumbnail.style.height = Math.round(ry * this.height) + "px";
            this.thumbnail.style.marginLeft = "-" + Math.round(rx * coords.x) + "px";
            this.thumbnail.style.marginTop = "-" + Math.round(ry * coords.y) + "px";
        } else {
            var overflowContainer = this.thumbnail.parentNode,
                overflowContainerParent = overflowContainer.parentNode,
                marginRight = Math.round(this.naturalDimensions.width - (ratio * coords.x2)),
                marginBottom = Math.round(this.naturalDimensions.height - (ratio * coords.y2));
            
            this.thumbnail.style.marginLeft = "-" + Math.round(ratio * coords.x) + "px";
            this.thumbnail.style.marginTop = "-" + Math.round(ratio * coords.y) + "px";
            
            overflowContainer.style.width = Math.round(ratio * coords.w) + "px";
            overflowContainer.style.height = Math.round(ratio * coords.h) + "px";
            
            var parentHeight = Math.round(ratio * coords.h) + 24,
                maxHeight = parseInt(lib.dom.getStyle(overflowContainerParent, "maxHeight"));

            overflowContainerParent.style.height = ((parentHeight < maxHeight) ? parentHeight : maxHeight) + "px";
        }
        
        if (typeof this.output == "object") {
            var x = this.form.elements[this.output.x].value = Math.round(ratio * coords.x),
                y = this.form.elements[this.output.y].value = Math.round(ratio * coords.y),
                x2 = this.form.elements[this.output.x2].value = Math.round(ratio * coords.x2),
                y2 = this.form.elements[this.output.y2].value = Math.round(ratio * coords.y2);
            this.form.elements[this.output.w].value = x2 - x;
            this.form.elements[this.output.h].value = y2 - y;
        }
    },
    
    preview: function(coords) {
        if (this.image.width == this.image.naturalWidth) {
            this.updateThumbnail(this.image, this.thumbnail, coords);
        } else {
            if (this.timeout) window.clearTimeout(this.timeout);
            this.timeout = window.setTimeout(lib.bind(function() {
                this.updateThumbnail(this.image, this.thumbnail, coords);
            }, this), 50);
        }
    }
});

lib.widget({
    name: "search",
    
    placeholder: "Search",
    input: null,
    keyPressed: false,
    
    events: {},
    
    construct: function() {
        this.input = lib.dom.byClass("search-input", this.element)[0];
        if (!this.input) return;
        
        this.events.focus = lib.bind(this.focus, this);
        this.events.blur = lib.bind(this.blur, this);
        this.events.keypress = lib.bind(this.keypress, this);
        this.events.submit = lib.bind(this.submit, this);
        
        lib.event.add(this.input, "focus", this.events.focus);
        lib.event.add(this.input, "blur", this.events.blur);
        lib.event.add(this.input, "keypress", this.events.keypress);
        lib.event.add(this.element, "submit", this.events.submit);
        
        if (!this.input.value || this.placeholder == this.input.value) {
            this.input.value = this.placeholder;
            lib.dom.addClass(this.input, "placeholder");
        }
    },
    
    destroy: function() {
        lib.event.remove(this.input, "focus", this.events.focus);
        lib.event.remove(this.input, "blur", this.events.blur);
        lib.event.remove(this.input, "keypress", this.events.keypress);
        lib.event.remove(this.element, "submit", this.events.submit);
    },
    
    focus: function(event) {
        log(this.input.value == this.placeholder, this.keyPressed);
        if (this.input.value == this.placeholder && !this.keyPressed) {
            lib.dom.removeClass(this.input, "placeholder");
            this.input.value = "";
        }
    },
    
    blur: function(event) {
        if (!this.input.value) {
            lib.dom.addClass(this.input, "placeholder");
            this.input.value = this.placeholder;
            this.keyPressed = false;
        }
    },
    
    keypress: function(event) {
        this.keyPressed = true;
    },
    
    submit: function(event) {
        if (!this.keyPressed) {
            this.input.value = "";
        }
    }
});























