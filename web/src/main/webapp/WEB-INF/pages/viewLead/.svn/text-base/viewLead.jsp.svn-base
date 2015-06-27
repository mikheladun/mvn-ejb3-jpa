<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<div class="update-bread-crumb">
		<ul class="breadcrumb">
			<li><a href="<c:url value='/workQueue'/>">Work Queue</a>&nbsp;&nbsp;&gt;</li>
			<li><a href="#"><c:out value="${model.ltLead.ltSubject.lastname}" />, <c:out value="${model.ltLead.ltSubject.firstname}" /></a>&nbsp;&nbsp;&gt;</li>
			<li class="active"><c:out value="${model.ltLead.ltid}" /></li>
		</ul>
	</div>
	<h3 class="lead-status-open">Lead Status: <c:out value="${model.ltLead.statusCode.description}" /></h3>

	<div class="view-lead">
		<div class="biographic-information">
			<jsp:include page="biographic.jsp" />
		</div>
		<div class="entry-information">
			<div class="clear">&nbsp;</div>
			<jsp:include page="entry.jsp" />
		</div>
		<div class="lead-comments">
			<div class="clear">&nbsp;</div>
			<jsp:include page="comments.jsp" />
		</div>
		<div class="identifying-numbers hidden">
			<div class="clear">&nbsp;</div>
			<jsp:include page="numbers.jsp" />
		</div>
		<div class="aliases-section hidden">
			<div class="clear">&nbsp;</div>
			<jsp:include page="aliases.jsp" />
		</div>
		<div class="travel-information hidden">
			<div class="clear">&nbsp;</div>
			<jsp:include page="travel.jsp" />
		</div>
		<div class="associates-section hidden">
			<div class="clear">&nbsp;</div>
			<jsp:include page="associates.jsp" />
		</div>
	</div>