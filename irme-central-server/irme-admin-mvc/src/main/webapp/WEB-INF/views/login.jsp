<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

    <t:_mainLayout>
      <jsp:attribute name="enableSidebarToggleOnClickHandler">true</jsp:attribute>

      <jsp:attribute name="header">
        <h1>Welcome</h1>
      </jsp:attribute>
      <jsp:attribute name="footer">
        <p id="copyright">Copyright 1927, Future Bits When There Be Bits Inc.</p>
      </jsp:attribute>
      <jsp:body>
        <div class="container" style="height: 100vh;">
          <div id="login-row" class="row justify-content-center align-items-center h-100">
            <div id="login-column" class="col-md-6 border border-secondary rounded m-3 p-3 bg-light">
              <div id="login-box" class="col-md-12">
                <form id="login-form" class="form" action="" method="post">
                  <h3 class="text-center text-info">Login</h3>
                  <div class="form-group">
                    <label for="username" class="text-info">Username:</label><br>
                    <input type="text" name="username" id="username" class="form-control">
                  </div>
                  <div class="form-group">
                    <label for="password" class="text-info">Password:</label><br>
                    <input type="text" name="password" id="password" class="form-control">
                  </div>

                  <div class="alerts">
                    <div class="alert alert-danger" role="alert">
                      A simple danger alert—check it out!
                    </div>
                  </div>

                  <div class="form-group">
                    <label for="remember-me" class="text-info"><span>Remember me</span> <span><input id="remember-me"
                          name="remember-me" type="checkbox"></span></label><br>
                    <input type="submit" name="submit" class="btn btn-info btn-md" value="Login">
                  </div>
                  <div id="register-link" class="text-right">
                    <a href="#" class="text-info">Forgot password?</a>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </jsp:body>
    </t:_mainLayout>