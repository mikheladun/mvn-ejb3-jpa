 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="subject-information-form" class="yui3-form yui3-form-aligned">
  <h5 class="title">Subject Information</h5>

  <!-- Fields for subject information -->

  <!-- LSID field -->
  <div class="yui3-control-group">
    <label for="lsid">LSID:</label>
    <input id="lsid" type="text" name="ltLeadsModel[0].ltLeadSubject.lsid" class="input" tabindex="8" size="25" maxlength="12"/>
  </div>

  <!-- Surname field -->
  <div class="yui3-control-group">
    <label for="subjectSurname">Surname:<span class="asterisk" >*</span></label>
    <input id="subjectSurname"  type="text" name="ltLeadsModel[0].ltLead.ltSubject.lastname" class="input required" tabindex="9" size="25" maxlength="100"/>
  </div>
  
  <!-- Given Name -->
  <div class="yui3-control-group">
    <label for="subjectGivenName">Given Name:<span class="asterisk" >*</span></label>
    <input id="subjectGivenName" type="text" name="ltLeadsModel[0].ltLead.ltSubject.firstname" class="input required" tabindex="10" size="25" maxlength="100"/>
  </div>

  <!-- Gender -->
  <div class="yui3-control-group">
    <label for="subjectGender" >Gender:</label>
    <select id="subjectGender" name="ltLeadsModel[0].ltLead.ltSubject.genderCode.abbreviation" class="select" tabindex="11">
      <option>Select...</option>
      <c:forEach var="option" items="${model.supportDataModel.genderCodes}">
        <option value="${option.id}:${option.abbreviation}">${option.description}</option>
      </c:forEach>
    </select>
  </div>

  <!-- Date of Birth -->
  <div class="yui3-control-group">
    <label for="subjectDOB">DOB:</label>
    <input id="subjectDOB" type="text" name="ltLeadsModel[0].birthDateModel.value" class="input date dob" tabindex="12" size="25" maxlength="11"/>
  </div>

  <!-- Country of Birth -->
  <div class="yui3-control-group">
    <label for="subjectCOB" >COB:</label>
    <select id="subjectCOB" name="ltLeadsModel[0].ltLead.ltSubject.countryCode.abbreviation" class="select" tabindex="13">
      <option>Select...</option>
      <c:forEach var="option" items="${model.supportDataModel.countryCodes}">
        <option value="${option.id}:${option.abbreviation}">${option.description}</option>
      </c:forEach>
     </select>
  </div>

  <!--  Country of Citizenship -->
  <div id="countryOfCitizenshipsDropDown1" class="yui3-control-group multiselect">
    <label for="subjectCOCs">COC(s):</label>
    <input type="image" class="image" name="add" id="addCountryOfCitizenshipButton1" class="countryOfCitizenshipButton" src="<c:url value='/resources/images/p.png'/>" tabindex="14" alt="Click to add Country of Citizenship" title="Click to add Country of Citizenship"/>
    <select id="subjectCOCs" name="ltLeadsModel[0].ltSubjectCitizenshipCountries[0].countryCode.abbreviation" tabindex="14" class="select coc">
        <option>Select...</option>
        <c:forEach var="option" items="${model.supportDataModel.countryCodes}">
          <option value="${option.id}:${option.abbreviation}">${option.description}</option>
        </c:forEach>
    </select>
    <input type="image" class="image remove hidden" name="del" id="" src="<c:url value='/resources/images/m.png'/>" tabindex="14" alt="Click to remove Country of Citizenship" title="Click to remove Country of Citizenship"/>
  </div>

  <!-- Entry Date -->
  <div class="yui3-control-group">
    <label for="subjectEntryDate">Entry Date:</label>
    <input id="subjectEntryDate" type="text" name="ltLeadsModel[0].entryDateModel.value" class="input date entrydate" tabindex="15" size="25" maxlength="11"/>
  </div>

  <!--  Class of Administration -->
  <div class="yui3-control-group">
    <label for="subjectCOA">COA:</label>
    <select id="subjectCOA" name="ltLeadsModel[0].ltLead.ltSubject.classAdmissionCode.abbreviation"  class="select" tabindex="16">
      <option>Select...</option>
      <c:forEach var="option" items="${model.supportDataModel.classAdmissionCodes}">
        <option value="${option.id}:${option.abbreviation}">${option.description}</option>
      </c:forEach>
     </select>
  </div>

  <!-- Special Projects -->
    <div id="specialProjects" class="yui3-control-group multiselect">
      <label for="leadSpecialProjects">Special Project(s):</label>
      <input class="image" name="add" id="addSpecialProjectButton1" type="image" src="<c:url value='/resources/images/p.png'/>" tabindex="17" alt="Click to add new Special Project" title="Click to add new Special Project"/>
      <select id="leadSpecialProjects" name="ltLeadsModel[0].ltLeadSpecialProjects[0].specialProjectCode.abbreviation" tabindex="17" class="select">
        <option>Select...</option>
        <c:forEach var="option" items="${model.supportDataModel.specialProjectCodes}">
          <option value="${option.id}:${option.abbreviation}">${option.description}</option>
        </c:forEach>
      </select>
      <input class="image remove hidden" name="del" id="" type="image" src="<c:url value='/resources/images/m.png'/>" tabindex="17" alt="Click to remove Special Project" title="Click to remove Special Project"/>
    </div>
</div>