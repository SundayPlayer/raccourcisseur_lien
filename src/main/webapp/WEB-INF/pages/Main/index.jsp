<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Hibernate Spring Exemple</title>


    <spring:url value="/resources/css/style.css" var="mainCss" />
    <spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
    <spring:url value="/resources/css/style.css" var="logo-navCss" />

    <link href="${mainCss}" rel="stylesheet" />
    <link href="${bootstrapCss}" rel="stylesheet" />
    <link href="${logo-navCss}" rel="stylesheet" />

  </head>

  <body>

      <!-- Navigation -->
      <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
          <div class="container">
              <div class="navbar-header">
                  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                      <span class="sr-only">Toggle navigation</span>
                  </button>
                  <a class="navbar-brand" href="#">Hibernate Spring</a>
              </div>

          </div>
      </nav>

      <!-- Page Content -->
      <div class="container" style="margin-top: 65px;">

          <div class="row">

              <div class="col-md-3">
                  <p class="lead">Tchat</p>
                  <div class="list-group">

                        <form:form method="POST" modelAttribute="Message" action="/addMessage">
                            <form:errors path="*" cssClass="errorblock" element="div" />
                            <label for="message">Message: </label>
                            <td><form:input type="text" id="message" class="form-control" path="message" /></td>
                            <td><form:errors path="message" cssClass="error" /></td>
                            <button class="btn btn-primary btn-block" type="submit">Ajouter le message</button>
                        </form:form>

                      <a href="/deleteUser" class="list-group-item active">Supprimer l'utilisateur: ${User.id}</a>
                  </div>
              </div>

              <div class="col-md-9">
                  <div class="well">
                      <div class="text-right">
                            <form:form method="POST" action="/deleteMessage">
                              <form:errors path="*" cssClass="errorblock" element="div" />
                              <button class="btn btn-danger" type="submit">Supprimer Messages de: ${User.nom} ${User.prenom} (${User.id})</button>
                           </form:form>
                      </div>
                      <hr>

                       <c:forEach items="${User.messages}" var="message">
                            <div class="row">
                                 <div class="col-md-12">
                                     <p>${message.message}</p>
                                     <span class="pull-right">${message.user.nom} (${message.user.id})</span>
                                     <p>${fn:substring(message.encode, 0, 70)}...</p>
                                 </div>
                             </div>
                             <hr>
                       </c:forEach>

                  </div>
              </div>
          </div>
      </div>

      <!-- jQuery & Bootstrap -->
      <spring:url value="/resources/js/jquery.js" var="jqueryJs" />
      <spring:url value="/resources/js/bootstrap.min.js" var="bootstrapJs" />

      <script src="${jqueryJs}" type="text/javascript"></script>
      <script src="${bootstrapJs}" type="text/javascript"></script>

  </body>
</html>