 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<form:form commandName="model" class="yui3-form yui3-form-aligned" method="post">
	<div id="lead-disposition-form" class="right-rail cf">
		<div class="close-lead cf yui3-form yui3-form-aligned">
			<h5 class="title">Lead Disposition</h5>
			<div class="solid-top"></div>
			<div class="right-rail-content">

			  <!-- Disposition -->
			  <div class="yui3-control-group">
			    <label for="disposition">Disposition:<span class="r">*</span></label><br>
			    <select id="disposition" name="dispositionModel.statusCode.abbreviation" class="select disposition required" tabindex="300">
			      <option>Select...</option>
			      <option value="108:CL" <c:if test="${model.ltLead.statusCode.description == 'Review'}"> selected="selected"</c:if>>Close Lead</option>
				  <option value="106:PE" <c:if test="${model.ltLead.statusCode.description == 'Pending'}"> selected="selected"</c:if>>Place Lead in Pending</option>
				  <option value="103:HO" <c:if test="${model.ltLead.statusCode.description == 'Hold'}"> selected="selected"</c:if>>Place Lead on Hold</option>
				  <option value="105:RT" <c:if test="${model.ltLead.statusCode.description == 'Returned'}"> selected="selected"</c:if>>Return Lead</option>
			    </select>
			  </div>

			  <!-- Closure Reason -->
			  <div class="yui3-control-group <c:if test="${model.ltLead.statusCode.description != 'Review'}">hidden</c:if>">
			    <label for="closureReason">Closure Reason:<span class="r">*</span></label><br>
			    <select id="closureReason" name="dispositionModel.reasonCode.abbreviation" class="select required closure-reason" tabindex="300">
			      <option>Select...</option>
			      <c:forEach var="option" items="${requestScope.supportDataCache['DisposCloseReasonCode']}">
			        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.disposCloseReasonCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
			      </c:forEach>
			    </select>
			  </div>

			  <!-- Pending Reason -->
			  <div class="yui3-control-group <c:if test="${model.ltLead.statusCode.description != 'Pending'}">hidden</c:if>">
			    <label for="pendingReason">Pending Reason:<span class="r">*</span></label><br>
			    <select id="pendingReason" name="dispositionModel.reasonCode.abbreviation" class="select required pending-reason" tabindex="300">
			      <option>Select...</option>
			      <c:forEach var="option" items="${requestScope.supportDataCache['DisposCloseReasonCode']}">
			        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.disposCloseReasonCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
			      </c:forEach>
			    </select>
			  </div>

			  <!-- Return Reason -->
			  <div class="yui3-control-group <c:if test="${model.ltLead.statusCode.description != 'Returned'}">hidden</c:if>">
			    <label for="returnReason">Return Reason:<span class="r">*</span></label><br>
			    <select id="returnReason" name="dispositionModel.reasonCode.abbreviation" class="select required return-reason" tabindex="300">
			      <option>Select...</option>
			      <c:forEach var="option" items="${requestScope.supportDataCache['DisposCloseReasonCode']}">
			        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.disposCloseReasonCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
			      </c:forEach>
			    </select>
			  </div>

			  <!-- Hold Reason -->
			  <div class="yui3-control-group <c:if test="${model.ltLead.statusCode.description != 'Hold'}">hidden</c:if>">
			    <label for="holdReason">Hold Reason:<span class="r">*</span></label><br>
			    <select id="holdReason" name="dispositionModel.reasonCode.abbreviation" class="select required hold-reason" tabindex="300">
			      <option>Select...</option>
			      <c:forEach var="option" items="${requestScope.supportDataCache['DisposCloseReasonCode']}">
			        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.disposCloseReasonCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
			      </c:forEach>
			    </select>
			  </div>

			  <!-- Closure System -->
			  <div class="yui3-control-group <c:if test="${model.ltLead.statusCode.description != 'Review'}">hidden</c:if>">
			    <label for="closureSystem">Closure System:<span class="r">*</span></label><br>
			    <select id="closureSystem" name="dispositionModel.systemCode.abbreviation" class="select required closure-system" tabindex="300">
			      <option>Select...</option>
			      <c:forEach var="option" items="${requestScope.supportDataCache['DisposCloseSystemCode']}">
			        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.disposCloseSystemCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
			      </c:forEach>
			    </select>
			  </div>

			  <!-- Call-up Date -->
			  <div class="yui3-control-group <c:if test="${model.ltLead.statusCode.description != 'Pending'}">hidden</c:if>">
			    <label for="callUpDate">Call-up Date:<span class="r">*</span></label><br>
			    <input id="callUpDate" type="text" name="dispositionModel.callupDateModel.value" class="input date required" tabindex="300" size="25" maxlength="11">
			  </div>

			  <sec:authorize access="hasRole('AN')">
				  <div class="yui3-control-group 
				  	<c:if test="${model.ltLead.statusCode.description != 'Review' &&
				  			      model.ltLead.statusCode.description != 'Pending'}">hidden</c:if>">
					<label for="dispositionSupervisor">Supervisor:<span class="r">*</span></label>
					<select id="dispositionSupervisor" name="dispositionModel.supervisor.id" class="supervisor required" tabindex="300">
					  <option value="">None</option>
				      <c:forEach var="option" items="${model.dispositionModel.supervisors}">
				        <option value="${option.id}">${option.lastname}, ${option.firstname}</option>
				      </c:forEach>
					</select>
				  </div>
			  </sec:authorize>
			  <sec:authorize access="hasAnyRole('SU','SA')">
				  <div class="yui3-control-group <c:if test="${model.ltLead.statusCode.description != 'Returned'}">hidden</c:if>">
					<label for="dispositionAnalyst" class="analyst">Analyst:<span class="r">*</span></label>
					<select id="dispositionAnalyst" name="dispositionModel.analyst.id" class="analyst required" tabindex="300">
					  <option value="">None</option>
				      <c:forEach var="option" items="${model.dispositionModel.analysts}">
				        <option value="${option.id}" <c:if test="${model.ltLead.ltUserByLtAssignToUserId.id eq option.id}">selected="selected"</c:if>>${option.lastname}, ${option.firstname}</option>
				      </c:forEach>
					</select>
				  </div>
			  </sec:authorize>

			    <!-- Details -->
			    <div class="yui3-control-group comments 
				<c:if test="${model.ltLead.statusCode.description != 'Returned' && model.ltLead.statusCode.description != 'Hold'}">hidden</c:if>">
			      <label for="dispositionDetails">Details:<span class="r">*</span></label>
			      <textarea id="dispositionDetails" name="dispositionModel.details" rows="5" class="textarea details required" tabindex="300" maxlength="4000">${model.dispositionModel.details}</textarea>
			    </div>
			</div>

			<div class="buttons">
				<a id="dispositionCancelLink" class="yui3-link cancel" href="#" title="Cancel" tabindex="300">Cancel</a>
			<sec:authorize access="hasRole('AN')">
				<input id="dispositionSubmitForReviewButton" type="button" name="submitForReview" value="Submit for Review" class="yui3-button <c:if test="${model.ltLead.statusCode.description != 'Review' && model.ltLead.statusCode.description != 'Pending'}">hidden</c:if>" tabindex="300" />
			</sec:authorize>
			<sec:authorize access="hasAnyRole('SU','SA')">
				<input id="dispositionCloseLeadButton" type="button" name="closeLead" value="Close Lead" class="yui3-button <c:if test="${model.ltLead.statusCode.description != 'Review'}">hidden</c:if>" tabindex="300" />
				<input id="dispositionPlaceInPendingButton" type="button" name="placeInPending" value="Place in Pending" class="yui3-button <c:if test="${model.ltLead.statusCode.description != 'Pending'}">hidden</c:if>" tabindex="300" />
			</sec:authorize>
			<input id="dispositionPlaceOnHoldButton" type="button" name="placeOnHold" value="Place on Hold" class="yui3-button <c:if test="${model.ltLead.statusCode.description != 'Hold'}">hidden</c:if>" tabindex="300" />
			<input id="dispositionReturnLeadButton" type="button" name="returnLead" value="Return Lead" class="yui3-button <c:if test="${model.ltLead.statusCode.description != 'Returned'}">hidden</c:if>" tabindex="300" />
		</div>
	</div>
	<sec:authorize access="hasAnyRole('SU','SA')">
		<input type="hidden" name="role" value="SU_SA" />
	</sec:authorize>
	<input type="hidden" name="ltLead.id" value="${model.ltLead.id}" />
</div>
</form:form>