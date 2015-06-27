<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--[if lte IE 7]> <html class="ie"> <![endif]-->  
<!--[if IE 8]>     <html class="ie"> <![endif]-->  
<!--[if !IE]><!--> <html>             <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="expires" content="-1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><tiles:insertAttribute name="title" /></title>
<link href="<c:url value='/resources/css/jquery-ui-1.9.2.custom.min.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/yui-reset.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/yui-base.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/yui-button.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/yui-form.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/base.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/main.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/dialog.css'/>" rel="stylesheet" />
<jsp:include page="style.jsp" />
<tiles:insertAttribute name="style" />
<script type="text/javascript" src="<c:url value='/resources/js/lib/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/lib/jquery-ui-1.9.2.custom.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/common/main.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/common/leadtrac.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/common/confirm.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/common/autosave.js'/>"></script>
<tiles:insertAttribute name="script" />
</head>
<body>
  <div id="container" class="container">
   <div class="top clearfix">
      <a class="logo" href="#"><strong>Lead</strong>Trac</a>
      <tiles:insertAttribute name="nav" />
    </div>
    <div class="wrapper default">
      <div id="content" class="content">
        <tiles:insertAttribute name="breadcrumb" />
        <div id="maincontent">
           <div class="contentdiv-border border-top-right"><h4 class="title"><tiles:insertAttribute name="pageTitle" /></h4><span><img src="<c:url value='/resources/images/bg-content-tr.jpg'/>" /></span></div>
           <div class="contentdiv-body">
            <tiles:insertAttribute name="body" />
           </div>
           <div class="contentdiv-border border-bottom-left"><img src="<c:url value='/resources/images/bg-content-bl.jpg'/>" /></div>
        </div>
      </div>
      <div id="footer" class="footer">
        <tiles:insertAttribute name="footer" />
      </div>&nbsp;
    </div>
  </div>
  <jsp:include page="../../../pages/common/dialog.jsp" />
</body>
</html>