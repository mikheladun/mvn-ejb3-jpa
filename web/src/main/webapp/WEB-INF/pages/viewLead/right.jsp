<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<div class="right">
	<div class="icons">
		<img class="p dots" src="<c:url value="/resources/images/Dots.png" />">
		<img class="line" src="<c:url value="/resources/images/Line.png" />">
		<img class="p check" src="<c:url value="/resources/images/Check.png" />">
	</div>
	<div class="right-rail lead-disposition cf">
		<jsp:include page="disposition.jsp" />
	</div>
	<div class="right-rail indices cf hidden">
		<jsp:include page="indices-rail.jsp" />
	</div>
</div>