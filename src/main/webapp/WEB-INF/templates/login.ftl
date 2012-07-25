<#assign tiles=JspTaglibs["http://tiles.apache.org/tags-tiles"]/>
<#assign spring=JspTaglibs["http://www.springframework.org/tags"]>

                    <div id="login-editing" class="editing clearfix">
                        <h3 class="ac"><@tiles.insertAttribute name="message" /></h3>
						<p id="passwordReset" class="ac mb" style="display:none;">
							<@spring.message code="resetSuccess.message"/>
						</p> 
						<#if login_error??>
							<p id="error" class="errorBox">
								<@spring.message code="login.badLogin"/><br/>
								<a href="${cp}spring/reset"><@spring.message code="loginError.lostPassword"/></a>
							</p>
						</#if>
						
						
      <div id="fb-root"></div>
      <script>
      	function performLogin(response) {
			FB.api('/me', function(response) {
	            document.getElementById("fbname").value=response.name;
	            document.getElementById("fbemail").value=response.email;
	            
	            document.loginForm.submit();
		    });
		}
		    
     	function loginToFacebook() {
			FB.getLoginStatus(function(response) {
			  if (response.authResponse) {
			    performLogin();
			  } else {
			    FB.login(function(response){},  {scope: 'email'});
			  }
			});
     	}
     	
        window.fbAsyncInit = function() {
			FB.Event.subscribe('auth.login', performLogin);
			
			FB.init({
				appId      : '148759295225845',
				channelURL : '//beta.inbelly.com/channel.html',
				status     : true, // check login status
				cookie     : true, // enable cookies to allow the server to access the session
				oauth      : true, // enable OAuth 2.0
				xfbml      : true  // parse XFBML
			});
        };
        (function(d){
           var js, id = 'facebook-jssdk'; if (d.getElementById(id)) {return;}
           js = d.createElement('script'); js.id = id; js.async = true;
           js.src = "//connect.facebook.net/en_US/all.js";
           d.getElementsByTagName('head')[0].appendChild(js);
         }(document));
      </script>
      						
						
                        <form id="login" name="loginForm" action="loginProcess" class="login clearfix">
					      <div class="mt mb clearfix" data-scope="email" >
					        <a href="login" onclick="loginToFacebook(); return false;"><img src="${cp}images/facebook_login_button.png" /></a>
					      </div>
                            <div class="field clearfix">
                                <label for="f-product"><@spring.message code="login.email"/></label>
                                <div class="input">
									<input type="text" name="j_username" onfocus="bUsernameHasFocus = true;" onblur="bUsernameHasFocus = false;" id="username" value="" maxlength="256" size="30"/>
                                </div>
                            </div>
                            <div class="field clearfix">
                                <label for="f-password"><@spring.message code="login.password"/></label>
                                <div class="input">
                                    <input type="password" name="j_password" onfocus="bPasswordHasFocus = true;" onblur="bPasswordHasFocus = false;" id="password" value="" maxlength="256" size="30"/>
                                    <a class="extraLink" href="${cp}spring/reset"><@spring.message code="loginError.lostPassword"/></a>
                                </div>
                            </div>
                            <div class="field clearfix">
                                <input id="remember_me" type="checkbox" name="_spring_security_remember_me"/>
								<label for="remember_me"><@spring.message code="login.rememberMe"/></label>
                            </div>
                            
                            <div class="field nmb clearfix">
                                <div class="input">
									<button type="submit" name="btnSubmit"><@spring.message code="login.login"/></button>
									<a href="${cp}spring/register"><@spring.message code="login.registerMessage"/></a>
                                </div>
                            </div>
                            <input type="hidden" name="fbemail" id="fbemail" value="" />
                            <input type="hidden" name="fbname" id="fbname" value="" />
                        </form>
                        
                    </div>
