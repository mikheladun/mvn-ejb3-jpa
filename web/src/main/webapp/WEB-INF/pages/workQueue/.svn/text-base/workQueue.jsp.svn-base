<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form commandName="model" class="yui3-form yui3-form-aligned" method="post">
	<hr class="clear" />
	<div class="workqueue-data-table">
		<table class="data-table">
			<thead>
				<tr class="table-top-line">
					<td width="2%" align="center"><strong>Priority</strong></td>
					<td><strong>LeadTrac Number</strong></td>
					<td><strong>Subject Name</strong></td>
					<td width="2%"><strong>Gender</strong></td>
					<td><strong>DOB</strong></td>
					<td><strong>COB</strong></td>
					<td><strong>COC</strong></td>
					<td><strong>COA</strong></td>
					<td><strong>Lead Type</strong></td>
					<td><strong>Associated Lead(s)</strong></td>
					<td><strong>Status</strong></td>
					<sec:authorize access="hasAnyRole('SU','SA')">
					<td><strong>Recommendation</strong></td>
					</sec:authorize>
				</tr>
			</thead>
			<tbody>
				<c:if test="${empty model.workQueueItems}">
					<tr class="table-odd">
						<td colspan="11">&nbsp;</td>
						<sec:authorize access="hasAnyRole('SU','SA')">
							<td>&nbsp;</td>
						</sec:authorize>
					</tr>
				</c:if>
				<c:forEach var="item" items="${model.workQueueItems}" varStatus="counter">
				<tr class="<c:if test="${(counter.index % 2) eq 0}">table-even</c:if><c:if test="${(counter.index % 2) ne 0}">table-odd</c:if>">
					<td width="2%" align="center">1</td>
					<td><a href="<c:url value="/viewLead/${item.ltLead.id}" />"><strong><c:out value="${item.ltLead.ltid}" /></strong></a></td>
					<td><a href="#"><strong><c:out value="${item.ltSubject.lastname}"/>, <c:out value="${item.ltSubject.firstname}"/></strong></a></td>
					<td width="2%"><c:out value="${item.ltSubject.genderCode.description}"/></td>
					<td><f:formatDate pattern="MM/dd/yyyy" value="${item.ltSubject.birthDate}" /></td>
					<td><c:out value="${item.ltSubject.countryCode.description}"/></td>
					<td><c:forEach var="coc" items="${item.ltSubject.ltSubjectCitizenshipCountries}" varStatus="cocCounter">
						<c:if test="${cocCounter.index == 0}"><c:out value="${coc.countryCode.description}"/></c:if>
						<c:if test="${cocCounter.index == 1 }">...</c:if>
					</c:forEach></td>
					<td><c:out value="${item.ltSubject.classAdmissionCode.description}"/></td>
					<td><c:out value="${item.ltLead.leadTypeCode.description}"/></td>
					<td><c:forEach var="assoc" items="${item.ltLead.ltAssociatedLeadsForLtLeadId}" varStatus="assocCounter">
						<c:if test="${assocCounter.index == 0}"><c:out value="${assoc.ltLeadByLtAssociatedLeadId.ltid}"/></c:if>
						<c:if test="${assocCounter.index == 1}">...</c:if>
					</c:forEach></td>
					<td><c:out value="${item.ltLead.statusCode.description}"/></td>
					<sec:authorize access="hasAnyRole('SU','SA')">
					<td><c:out value="${item.ltLead.disposCloseReasonCode.description}"/></td>
					</sec:authorize>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

</form:form>