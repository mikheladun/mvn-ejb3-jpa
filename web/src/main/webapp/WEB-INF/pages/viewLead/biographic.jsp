 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<form:form commandName="model" class="yui3-form yui3-form-aligned" method="post">
<div id="biographic-information-form" class="update-form cf yui3-form yui3-form-aligned">
<h4 class="title">Biographic Information</h4>
  <div class="cf column-group two-column">
    <div class="biographic-information column-one">

	  <!-- Surname field -->
	  <div class="yui3-control-group">
	    <label for="subjectSurname">Surname:</label>
	    <input id="subjectSurname"  type="text" value="<c:out value="${model.ltLead.ltSubject.lastname}" />" class="input readonly" readonly="readonly" tabindex="30" size="25" maxlength="100"/>
	  </div>

	  <!-- Given Name -->
	  <div class="yui3-control-group">
	    <label for="subjectGivenName">Given Name:</label>
	    <input id="subjectGivenName" type="text" value="<c:out value="${model.ltLead.ltSubject.firstname}" />" class="input readonly" readonly="readonly" tabindex="30" size="25" maxlength="100"/>
	  </div>
	
	  <!-- Date of Birth -->
	  <div class="yui3-control-group">
	    <label for="subjectDOB">DOB:</label>
	    <input id="subjectDOB" type="text" name="biographicModel.dob.value" value='<f:formatDate pattern="MM/dd/Eyyyy" value="${model.ltLead.ltSubject.birthDate}" />'  class="input date dob" tabindex="30" size="25" maxlength="11"/>
	  </div>

      </div>
      
      <div class="biographic-information column-two">

  <!-- Gender -->
  <div class="yui3-control-group">
    <label for="subjectGender" >Gender:</label>
    <select id="subjectGender" name="biographicModel.genderCode.abbreviation" class="select" tabindex="30">
      <option>Select...</option>
      <c:forEach var="option" items="${requestScope.supportDataCache['GenderCode']}">
        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.ltSubject.genderCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
      </c:forEach>
    </select>
  </div>

	  <!-- Country of Birth -->
	  <div class="yui3-control-group">
	    <label for="subjectCOB" >COB:</label>
	    <select id="subjectCOB" name="biographicModel.countryCode.abbreviation" class="select" tabindex="30">
	      <option>Select...</option>
	      <c:forEach var="option" items="${requestScope.supportDataCache['CountryCode']}">
	        <option value="${option.id}:${option.abbreviation}" <c:if test="${model.ltLead.ltSubject.countryCode.id eq option.id}">selected="selected"</c:if>>${option.description}</option>
	      </c:forEach>
	     </select>
	  </div>

		<!-- Country of Citizenship -->
		<div id="countryOfCitizenshipsDropDown1" class="yui3-control-group multiselect">
		<c:set var="cocArray" value="${model.ltLead.ltSubject.ltSubjectCitizenshipCountries.toArray()}"/>
		<label for="subjectCOCs">COC(s):</label>
		<input type="image" class="image" name="add" id="addCountryOfCitizenshipButton1" class="countryOfCitizenshipButton" src="<c:url value='/resources/images/p.png'/>" tabindex="30" alt="Click to add Country of Citizenship" title="Click to add Country of Citizenship"/>
		<select id="subjectCOCs" name="biographicModel.cocs[0].countryCode.abbreviation" tabindex="30" class="select coc">
			<option>Select...</option>
			<c:forEach var="option" items="${requestScope.supportDataCache['CountryCode']}">
				<option value="${option.id}:${option.abbreviation}"
				<c:if test="${not empty cocArray && cocArray[0].countryCode.id == option.id}"> selected="selected"</c:if>
				<c:if test="${not empty cocArray[1] && cocArray[1].countryCode.id == option.id}"> disabled</c:if>>${option.description}</option>
			</c:forEach>
		</select>
		<input type="image" class="image remove hidden" name="del" id="" src="<c:url value='/resources/images/m.png'/>" tabindex="30" alt="Click to remove Country of Citizenship" title="Click to remove Country of Citizenship"/>
		<c:if test="${not empty cocArray && not empty cocArray[1]}">
			<c:set var="coc" value="${cocArray[1]}" />
			<span class="multiselect" style="display: none;">
			<select id="biographic-coc-1" class="hidden" tabindex="30" name="biographicModel.cocs[1].countryCode.abbreviation">
				<option value="${coc.countryCode.id}:${coc.countryCode.abbreviation}">${coc.countryCode.description}</option>
			</select><label for="" tabindex="30"><c:out value="${coc.countryCode.description}" /></label>
				<input type="image" name="del" class="image remove" id="" src="/leadtrac-web/resources/images/m.png" alt="Click to remove COC" title="Click to remove COC" tabindex="30">
			</span>
		</c:if>
		</div>
       <div class="clear"></div>
      </div>

  </div>
  <div class="clear">&nbsp;</div>
 	<input type="hidden" name="ltLead.id" value="${model.ltLead.id}" />
 	<input type="hidden" name="ltLead.ltSubject.id" value="${model.ltLead.ltSubject.id}" />
 </div>
 </form:form>