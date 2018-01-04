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

    <title>Raccourcisseur de liens</title>


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
                  <a class="navbar-brand" href="/">Raccourcisseur de lien</a>
              </div>
          </div>
      </nav>

      <!-- Page Content -->
      <div class="container" style="margin-top: 65px;">
          <div class="row">
              <div class="col-xs-12 col-sm-4 col-md-3">

                  <form:form method="POST" modelAttribute="User" action="/connect">
                      <form:errors path="*" cssClass="errorblock" element="div"/>
                      <label for="emailC">Nom: </label>
                      <td><form:input type="text" id="emailC" class="form-control" path="email" /></td>
                      <td><form:errors path="email" cssClass="error" /></td>
                      <label for="passwordC">Mot de passe : </label>
                      <td><form:input type="text" id="passwordC" class="form-control" path="password" /></td>
                      <td><form:errors path="password" cssClass="error" /></td>
                      <button class="btn btn-primary btn-block" type="submit">Login</button>
                  </form:form>

                  <form:form method="POST" modelAttribute="User" action="/addUser">
                      <form:errors path="*" cssClass="errorblock" element="div"/>
                      <label for="nom">Nom: </label>
                      <td><form:input type="text" id="nom" class="form-control" path="nom" /></td>
                      <td><form:errors path="nom" cssClass="error" /></td>
                      <label for="prenom">Prénom: </label>
                      <td><form:input type="text" id="prenom" class="form-control" path="prenom" /></td>
                      <td><form:errors path="prenom" cssClass="error" /></td>
                      <label for="email">E-mail : </label>
                      <td><form:input type="text" id="email" class="form-control" path="email" /></td>
                      <td><form:errors path="email" cssClass="error" /></td>
                      <label for="password">Mot de passe : </label>
                      <td><form:input type="text" id="password" class="form-control" path="password" /></td>
                      <td><form:errors path="password" cssClass="error" /></td>
                      <button class="btn btn-primary btn-block" type="submit">Register</button>
                  </form:form>

              </div>
              <div class="col-xs-0 col-sm-0 col-md-1">
              </div>
              <div class="col-xs-12 col-sm-8 col-md-8">
                  <p class="lead"></p>
                  <div class="list-group">
                        <form:form method="POST" modelAttribute="Url" action="/addUrl">
                            <form:errors path="*" cssClass="errorblock" element="div" />
                            <label for="url">Url: </label>
                            <td><form:input type="text" id="url" class="form-control" path="url" /></td>
                            <td><form:errors path="url" cssClass="error" /></td>
                            <button class="btn btn-primary btn-block" type="submit">Raccourcir l'Url</button>
                        </form:form>
                  </div>
                  <div class="col-xs-12 col-sm-12 col-md-12">
                      <div class="col-xs-12 col-sm-4 col-md-4">
                          <button class="btn btn-info btn-block" type="button">Nb lien</button>
                      </div>
                      <div class="col-xs-12 col-sm-4 col-md-4">
                          <button class="btn btn-info btn-block" type="button">Nb user</button>
                      </div>
                      <div class="col-xs-12 col-sm-4 col-md-4">
                          <button class="btn btn-info btn-block" type="button">Argent collecté</button>
                      </div>
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