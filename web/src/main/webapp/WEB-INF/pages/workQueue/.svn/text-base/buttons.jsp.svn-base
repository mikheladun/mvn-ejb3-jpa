<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<div class="buttons">
	<input id="requestLeadToWorkButton" type="button" class="yui3-button" name="workLead" value="Request New Lead to Work" title="Request New Lead to Work" />
	<sec:authorize access="hasAnyRole('SU','SA')">
		<input id="requestLeadToReviewButton" type="button" class="yui3-button" name="reviewLead" value="Request New Lead to Review" title="Request New Lead to Review" />
		<input id="initiateNewLeadButton" type="button" class="yui3-button" name="initiateLead" value="Initiate New Lead" title="Initiate New Lead" />
	</sec:authorize>
	<c:if test="${empty model.workQueueItems}">No leads in your queue</c:if>
</div>