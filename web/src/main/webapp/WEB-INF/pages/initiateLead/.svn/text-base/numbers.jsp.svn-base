 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="identifying-numbers-form" class="yui3-form yui3-form-aligned">
<h5 class="title">Identifying Number(s)</h5>
 <span class="expand-collapse-link hidden"><a id="expandAllAssociatesLink" href="#">Expand All</a></span>
<div class="identifying-numbers-data-table">
<table id="IdNumTable" class="data-table hidden">
    <thead>
        <tr class="table-top-line">
            <th class="thead">Number Type</th>
            <th class="thead">Number</th>
            <th class="thead">Country</th>
            <th class="thead">State/Province</th>
            <th class="thead">Issue Date</th>           
            <th class="thead">Expiration Date</th>
            <th class="thead">Source</th>
            <th class="no-table-style thead last"></th>
        </tr>
    </thead>
    <tbody>   
  </tbody>
</table>
</div>

  <div class="column-group two-column">
    <div class="identifying-numbers column-one">
      <div class="clear"></div>
      <span class="expand-collapse-link hidden"><br/><a id="collapseIdentifyingNumberLink" href="#">Collapse</a></span>
      <div class="clear"></div>

      <!--  Number Type -->
      <div class="yui3-control-group">
        <label for="identifyingNumberType">Number Type:<span class="asterisk" >*</span></label>
        <select id="identifyingNumberType" tabindex="20" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.numberTypeCode.abbreviation">
              <option value="">Select...</option>
            <c:forEach var="option" items="${model.supportDataModel.numberTypeCodes}">
              <option value="${option.id}:${option.abbreviation}">${option.description}</option>
            </c:forEach>
         </select>
      </div>

      <div class="identifying-numbers-group hidden">

       <!--  Other type -->
        <div class="yui3-control-group">
        	<label for="identifyingNumberOtherType">Other type:<span class="asterisk" >*</span></label>
        	<input id="identifyingNumberOtherType" type="text" value="" class="input required other" tabindex="21" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.otherType"  />
        </div>

        <!--  Number -->
        <div class="yui3-control-group">
          <label for="identifyingNumberNumber">Number:<span class="asterisk" >*</span></label>
          <input id="identifyingNumberNumber" type="text" value="" class="input required type number" tabindex="21" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.number"/>
        </div>

		<!-- Status -->
       <div class="yui3-control-group">
        	<label for="identifyingNumberStatus">Status: <span class="asterisk">*</span></label>
        	<select id="identifyingNumberStatus" tabindex="21" class="required status tecs-case tecs-subject-record" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.statusCode.abbreviation">
        	<option value="">Select...</option>
              <c:forEach var="option" items="${model.supportDataModel.tecsStatusCodes}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
        	</select>
        </div>

       <!--  Creation date -->
        <div class="yui3-control-group">
        	<label for="identifyingNumberCreationDate">Creation Date:</label>
        	<input id="identifyingNumberCreationDate" type="text" class="input date tecs-subject-record" tabindex="21" size="11" name="ltLeadsModel[0].identifyingNumberModel[0].creationDate.value" />
        </div>

        <!--  Update date -->
        <div class="yui3-control-group">
        	<label for="identifyingNumberUpdateDate">Update Date:</label>
        	<input id="identifyingNumberUpdateDate" type="text" class="input date tecs-subject-record" tabindex="21" size="11" name="ltLeadsModel[0].identifyingNumberModel[0].updateDate.value" />
        </div>

		<!-- State/Province -->
        <div class="yui3-control-group">
          <label for="identifyingNumberStateProvince">State/Province:</label>
          <select id="identifyingNumberStateProvince" tabindex="21" class="stateprovince drivers-license state-id-card" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.stateProvinceCode.abbreviation">
              <option>Select...</option>
              <c:forEach var="state" items="${model.supportDataModel.stateProvince}">
              <option value="${state.type}:${state.id}:${state.abbreviation}">${state.description}</option>
              </c:forEach>
           </select>
        </div>

       <!--  Naturalization date -->
        <div class="yui3-control-group">
        	<label for="identifyingNumberNaturalizationDate">Naturalization Date:</label>
        	<input id="identifyingNumberNaturalizationDate" type="text" class="input date naturalization" tabindex="21" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].naturalizationDate.value" />
        </div>

       <!--  Incident date -->
        <div class="yui3-control-group">
        	<label for="identifyingNumberIncidentDate">Incident Date:</label>
        	<input id="identifyingNumberIncidentDate" type="text" class="input date tecs-ilog" tabindex="21" size="24" name="ltLeadsModel[0].identifyingNumberModel[0].incidentDate.value" />
        </div>

       <!--   Visa class -->
       <div class="yui3-control-group">
          <label for="identifyingNumberVisaClass">Class:</label>
          <select id="identifyingNumberVisaClass" tabindex="21" class="visa-class visa visa-control" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.visaClassCode.abbreviation">
   		 <option value="">Select...</option>
   		     <c:forEach var="option" items="${model.supportDataModel.visaClassCodes}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
           </select>
        </div>        

     	<!--  Country -->
        <div class="yui3-control-group">
          <label for="identifyingNumberCountry">Country:<span class="asterisk" id="reqch">*</span></label>
          <select id="identifyingNumberCountry" tabindex="21" class="country alien-registration fin ssn other naturalization passport drivers-license state-id-card" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.countryCode.abbreviation">
              <option value="">Select...</option>
              <c:forEach var="option" items="${model.supportDataModel.countryCodes}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
           </select>
        </div> 

        <!--  Issue date -->
        <div class="yui3-control-group">
        	<label for="identifyingNuberIssueDate">Issue Date:</label>
        	<input id="identifyingNumberIssueDate" type="text" class="input date passport visa drivers-license state-id-card faa-license" tabindex="21" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueDate.value" />
        </div> 
 
 		<!-- Expiration date -->
         <div class="yui3-control-group">
        	<label for="identifyingNumberExpirationDate">Expiration Date:</label>
        	<input id="identifyingNumberExpirationDate" type="text" class="input date passport visa drivers-license state-id-card faa-license" tabindex="21" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].expirationDate.value" />
        </div> 

        <!-- Event date -->
        <div class="yui3-control-group">
        	<label for="identifyingNumberEventDate">Event Date:</label>
        	<input id="identifyingNumberEventDate" type="text" class="input date visa-control" tabindex="21" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].eventDate.value" />
        </div>
        
        <!-- Event type -->
        <div class="yui3-control-group">
       	<label for="identifyingNumberEventType">Event Type:</label>
          <select id="identifyingNumberEventType" tabindex="21" class="vcEventType visa-control" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.eventType.abbreviation">
              <option value="">Select...</option>
              <c:forEach var="event" items="${model.supportDataModel.eventTypes}">
              	<option value="${event.id}:${event.abbreviation}">${event.description}</option>
              </c:forEach>
           </select>
        </div>
        
        <!-- Application location -->
        <div class="yui3-control-group">
       	<label for="identifyingNumberApplicationLocation">Application Location:</label>
          <select id="identifyingNumberApplicationLocation" tabindex="21" class="vcAppLoc visa-control" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.applicationLocationCode.abbreviation">
              <option value="">Select...</option>
              <c:forEach var="apploc" items="${model.supportDataModel.applocs}">
              	<option value="${apploc.id}:${apploc.abbreviation}">${apploc.description}</option>
              </c:forEach>
           </select>
        </div>

		<!-- Issue location -->
        <div class="yui3-control-group">
           <label for="identifyingNumberIssueLocation">Issue Location:</label>
        	<input id="identifyingNumberIssueLocation" type="text" class="input faa-license" tabindex="21" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueLocation" />       
        </div>
        
        <!-- License type -->
        <div class="yui3-control-group">
        <label for="identifyingNumberLicenseType">License Type:</label>
         <select id="identifyingNumberLicenseType" tabindex="21" class="licCode faa-license" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.licenseTypeCode.abbreviation">
         	<option value="">Select...</option>
            <c:forEach var="code" items="${model.supportDataModel.licenseCodes}">
            <option value="${code.id}:${code.abbreviation}">${code.description}</option>
            </c:forEach>
         </select>
        </div>
        
                   
       </div>
      </div>
      
      <div class="identifying-numbers column-two">
       <div class="identifying-numbers-group hidden">
        <!-- Source field -->
        <div class="yui3-control-group multiselect">
          <label for="identifyingNumberSource">Source(s):<span class="asterisk" >*</span></label>
          <input type="image" name="add" class="image" id="identifying-numbers-source-add" src="<c:url value='/resources/images/p.png'/>" alt="Click to add a new Source" title="Click to add a new Source"/>
          <select id="identifyingNumberSource" class="required source" tabindex="22" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.sourceCodes[0].sourceCode.abbreviation">
            <option>Select...</option>
              <c:forEach var="option" items="${model.supportDataModel.sourceCodes}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
          </select>
          <input type="image" class="image remove hidden" name="del" id="" src="<c:url value='/resources/images/m.png'/>" tabindex="14" alt="Click to remove Source selection" title="Click to remove Source selection"/>
        </div>
        <!--  Comments -->
        <div class="yui3-control-group comments">
          <label for="identifyingNumberComment">Comments:</label>
          <textarea id="identifyingNumberComment" cols="10" rows="5" tabindex="23" maxlength="2000" name="ltLeadsModel[0].identifyingNumberModel[0].ltIdentifierNumber.identifyingNumberComment"></textarea>
        </div>
       </div>
		<div class="clear"></div>
		<div class="buttons hidden">
		  <input id="deleteIdentifyingNumberButton" type="button" class="yui3-button" name="delete" value="Delete" title="Delete identifying number" />
		</div>
      </div>

      <div class="clear"></div>
      <div class="buttons hidden">
        <span><a id="cancelIdentifyingNumberLink" class="yui3-link cancel" href="<c:url value='#identifying-numbers-form'/>" title="Cancel identifying number">Cancel</a></span>
        <input id="addIdentifyingNumberButton" type="button" class="yui3-button" name="add" value="Add" title="Add identifying number" />
        <input id="updateIdentifyingNumberButton" type="button" class="yui3-button" name="update" value="Update" title="Update identifying number" />
        <input id="deleteIdentifyingNumberButton" type="button" class="yui3-button" name="delete" value="Delete" title="Delete identifying number" />
      </div>
  </div>
  <div class="clear">&nbsp;</div>
 </div>