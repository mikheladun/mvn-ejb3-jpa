<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="expires" content="-1" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<title><tiles:insertAttribute name="title" /></title>
<link href="<c:url value='/resources/css/yui-reset.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/yui-base.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/yui-form.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/yui-button.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/base.css'/>" rel="stylesheet" />
<link href="<c:url value='/resources/css/dialog.css'/>" rel="stylesheet" />
<tiles:insertAttribute name="style" />
<script type="text/javascript" src="<c:url value='/resources/js/lib/jquery-1.8.2.min.js'/>"></script>
<tiles:insertAttribute name="script" />
</head>
<body>
  <tiles:insertAttribute name="body" />
</body>
</html>