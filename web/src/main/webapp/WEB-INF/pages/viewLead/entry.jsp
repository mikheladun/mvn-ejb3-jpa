 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<form:form commandName="model" class="yui3-form yui3-form-aligned" method="post">
<div id="entry-information-form" class="update-form yui3-form yui3-form-aligned">
<h4 class="title">Entry Information</h4>
  <div class="column-group two-column">
    <div class="entry-information column-one">

	  <!--  Class of Administration -->
	  <div class="yui3-control-group">
	    <label for="subjectCOA">COA:</label>
	    <select id="subjectCOA" name="entryModel.classAdmissionCode.abbreviation"  class="select" tabindex="40">
	      <option>Select...</option>
	      <c:forEach var="option" items="${requestScope.supportDataCache['ClassAdmissionCode']}">
	        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.ltSubject.classAdmissionCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
	      </c:forEach>
	     </select>
	  </div>

	  <!--  Class of Administration -->
	  <div class="yui3-control-group">
	    <label for="subjectPOE">POE:</label>
	    <select id="subjectPOE" name="entryModel.portEntryCode.abbreviation"  class="select" tabindex="40">
	      <option>Select...</option>
	      <c:forEach var="option" items="${requestScope.supportDataCache['PortEntryCode']}">
	        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.ltSubject.portEntryCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
	      </c:forEach>
	     </select>
	  </div>

	  <!-- Entry Date -->
	  <div class="yui3-control-group">
	    <label for="subjectEntryDate">Entry Date:</label>
	    <input id="subjectEntryDate" type="text" name="entryModel.entryDate.value" value='<f:formatDate pattern="MM/dd/yyyy" value="${model.ltLead.ltSubject.entryDate}" />' class="input date entryDate" tabindex="40" size="25" maxlength="11"/>
	  </div>

	  <!-- Admit Until Date -->
	  <div class="yui3-control-group">
	    <label for="admitUntilDate">Admit Until Date:</label>
	    <input id="admitUntilDate" type="text" name="entryModel.admitUntilDate.value" value='<f:formatDate pattern="MM/dd/yyyy" value="${model.ltLead.ltSubject.admitUntilDate}" />' class="input date admitUntilDate" tabindex="40" size="25" maxlength="11"/>
	  </div>

	  <!-- Duration of Status -->
	  <div class="yui3-control-group">
	    <input id="durationOfStatus" type="checkbox" name="entryModel.durationStatus.value" class="checkbox" <c:if test="${model.entryModel.durationStatus.value eq 'Y'}">checked="checked"</c:if> tabindex="40" />
	    <label for="durationOfStatus">Duration of Status</label>
	  </div>

    </div>
      
    <div class="entry-information column-two">

	  <!-- Adjusted Admit Until Date -->
	  <div class="yui3-control-group">
	    <label for="adjustedAdmitDate">Adjusted Admit Until Date:</label>
	    <input id="adjustedAdmitDate" type="text" name="entryModel.adjustedAdmitUntilDate.value" value='<f:formatDate pattern="MM/dd/yyyy" value="${model.ltLead.ltSubject.adjustedAdmitUntilDate}" />' class="input date adjustedAdmitUntilDate" tabindex="40" size="25" maxlength="11"/>
	  </div>

	  <!-- Departure Date -->
	  <div class="yui3-control-group">
	    <label for="departureDate">Departure Date:</label>
	    <input id="departureDate" type="text" name="entryModel.departureDate.value" value='<f:formatDate pattern="MM/dd/yyyy" value="${model.ltLead.ltSubject.departureDate}" />' class="input date departureDate" tabindex="40" size="25" maxlength="11"/>
	  </div>

	  <!--  Days Overstayed -->
	  <div class="yui3-control-group">
	    <label for="dispoverstay">Days Overstayed:</label>
	    <label id="dispoverstay"></label>
	  </div>

       <div class="clear"></div>  
      </div>

  </div>
  <div class="clear">&nbsp;</div>
 	<input type="hidden" name="ltLead.id" value="${model.ltLead.id}" />
 	<input type="hidden" name="ltLead.ltSubject.id" value="${model.ltLead.ltSubject.id}" />
</div>
</form:form>