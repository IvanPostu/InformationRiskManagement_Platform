<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Main template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<%@attribute name="enableSidebarToggleOnClickHandler" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>

    <link href="webjars/bootstrap/4.6.0/css/bootstrap.min.css" rel="stylesheet" />
    <link href="static/mainSidebar.css" rel="stylesheet" />
    <link href="static/index.css" rel="stylesheet" />
    <style>
    
    </style>
  </head>
  <body>
    
    <nav class="navbar navbar-expand navbar-dark bg-primary">
        <a href="#" id="menu-toggle" class="navbar-brand active">
            <span class="navbar-toggler-icon" style="border-color: white;"></span>
        </a>
        <div class="collapse navbar-collapse" id="navbarsExample02">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active"> <a class="nav-link" href="#">Home</a> </li>
                <li class="nav-item"> <a class="nav-link" href="#">Link</a> </li>
            </ul>
            <ul class="navbar-nav ml-auto">
              <li class="nav-item active"> <a class="nav-link" href="#">Logout</a> </li>
            </ul>
        </div>
    </nav>
    <div id="container-sidebar-wrapper" class="toggled">
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    General management:
                </a>
            </li>
            <li>
                <a href="#">Organisations 
                  <div>Hello</div>
                </a>
            </li>
            <li>
                <a href="#">Users</a>
            </li>
            <li>
                <a href="#">Security assessments</a>
            </li>
        </ul>
    </div>

    <div id="pageheader">
      <jsp:invoke fragment="header"/>
    </div>
    <div id="body" class="container-fluid">
      <jsp:doBody/>
    </div>
    <div id="pagefooter" >
      <jsp:invoke fragment="footer"/>
    </div>

    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/4.6.0/js/bootstrap.bundle.min.js"></script>
    <c:if test="${enableSidebarToggleOnClickHandler}" >
      <script>
        $("#menu-toggle").click(function (e) {
            e.preventDefault();
            $("#container-sidebar-wrapper").toggleClass("toggled");
        });
      </script>
    </c:if>
  </body>
</html>