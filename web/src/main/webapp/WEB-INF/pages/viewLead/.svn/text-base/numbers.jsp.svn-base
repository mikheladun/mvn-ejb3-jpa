 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="identifying-numbers-form" class="update-form yui3-form yui3-form-aligned">
<h4 class="title">Identifying Numbers</h4>
 <span class="expand-collapse-link hidden"><a id="expandAllIdentifyingNumberLink" href="#">Expand All</a></span>
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
      <div class="clear"></div>
      <span class="expand-collapse-link hidden"><br/><a id="collapseIdentifyingNumberLink" href="#">Collapse</a></span>
      <div class="clear"></div>
    <div class="identifying-numbers column-one">

      <!--  Number Type -->
      <div class="yui3-control-group">
        <label for="identifyingNumbersType">Number Type:<span class="asterisk" >*</span></label>
        <select id="identifyingNumbersType" class="type" tabindex="70" name="ltLeadsModel[0].identifyingNumberModel[0].numberTypeCode.abbreviation">
              <option value="">Select...</option>
            <c:forEach var="option" items="${requestScope.supportDataCache['NumberTypeCode']}">
              <option value="${option.id}:${option.abbreviation}">${option.description}</option>
            </c:forEach>
         </select>
      </div>
     
      <div class="identifying-numbers-group hidden">
      
       <!--  Other type mandatory field -->
         
        <div class="yui3-control-group">
        	<label for="other-type">Other type:<span class="asterisk" >*</span></label>
        	<input id="other-type" type="text" value="" class="input required other" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].otherType"  />
        </div>
        
        <!--  Number -->
        <div class="yui3-control-group">
          <label for="identifying-numbers-number">Number:<span class="asterisk" >*</span></label>
          <input id="identifying-numbers-number" type="text" value="" class="input required number" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].number"/>
        </div>

       <div class="yui3-control-group">
        	<label for="tecs-case-status">Status: <span class="asterisk">*</span></label>
        	<select id="tecs-case-status" tabindex="70" class="required status" name="ltLeadsModel[0].identifyingNumberModel[0].statusCode.abbreviation">
        	<option value="">Select...</option>
              <c:forEach var="option" items="${requestScope.supportDataCache['StatusCode']}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
        	</select>
        </div>
        
        <div class="yui3-control-group">
        	<label for="tecs-subj-rec-status">Status: <span class="asterisk">*</span></label>
        	<select id="tecs-subj-rec-status" tabindex="70" class="required status" name="ltLeadsModel[0].identifyingNumberModel[0].statusCode.abbreviation">
        	<option value="">Select...</option>
              <c:forEach var="option" items="${requestScope.supportDataCache['StatusCode']}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
        	</select>
        </div>
        
       <!--  TECS Subject Record date - mandatory field -->
        <div class="yui3-control-group">
        	<label for="tsrcCreationDate">Creation Date:</label>
        	<input id="tsrcCreationDate" name="tsrcCreationDate" type="text" class="input mdate" tabindex="70" size="11" name="ltLeadsModel[0].identifyingNumberModel[0].creationDate.value" />
        </div>
        
        <!--  TECS Subject Record date - mandatory field -->
        <div class="yui3-control-group">
        	<label for="tsrcUpdateDate">Update Date:</label>
        	<input id="tsrcUpdateDate" name="tsrcUpdateDate" type="text" class="input mdate" tabindex="70" size="11" name="ltLeadsModel[0].identifyingNumberModel[0].updateDate.value" />
        </div>

        <div class="yui3-control-group">
          <label for="stateprovince">State/Province:</label>
          <select id="stateprovince" tabindex="70" class="stateprovince" name="ltLeadsModel[0].identifyingNumberModel[0].stateProvinceCode.abbreviation">
              <option>Select...</option>
              <c:forEach var="state" items="${requestScope.supportDataCache['StateProvinceCode']}">
              <option value="${state.type}:${state.id}:${state.abbreviation}">${state.description}</option>
              </c:forEach>
           </select>
        </div>
        
       <!--  Naturalization date - mandatory field -->
        <div class="yui3-control-group">
        	<label for="naturalizationdate">Naturalization Date:</label>
        	<input id="naturalizationDate" name="naturalizationDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].naturalizationDate.value" />
        </div>
        
       <!--  TECS ILog incident date - mandatory field -->
        <div class="yui3-control-group">
        	<label for="ilogIncidentDate">Incident Date:</label>
        	<input id="ilogIncidentDate" type="text" class="input mdate" tabindex="70" size="24" name="ltLeadsModel[0].identifyingNumberModel[0].incidentDate.value" />
        </div>
        
        <!--  Passport dates - mandatory field -->
        <div class="yui3-control-group">
        	<label for="passportIssueDate">Issue Date:</label>
        	<input id="passportIssueDate" name="passportIssueDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueDate.value" />
        </div> 
 
         <div class="yui3-control-group">
        	<label for="passportExpDate">Expiration Date:</label>
        	<input id="passportExpDate" name="passportExpDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].expirationDate.value" />
        </div> 
       
       <!--   Visa Class -->
       <div class="yui3-control-group">
          <label for="visa-class">Class:</label>
          <select id="visa-class" tabindex="70" class="visa-class" name="ltLeadsModel[0].identifyingNumberModel[0].visaClassCode.abbreviation">
   		 <option value="">Select...</option>
   		     <c:forEach var="option" items="${requestScope.supportDataCache['VisaClassCode']}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
           </select>
        </div>
        
        <!--  Visa dates - mandatory field -->
        <div class="yui3-control-group">
        	<label for="visaIssueDate">Issue Date:</label>
        	<input id="visaIssueDate" name="visaIssueDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueDate.value" />
        </div> 
 
         <div class="yui3-control-group">
        	<label for="visaExpDate">Expiration Date:</label>
        	<input id="visaExpDate" name="visaExpDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].expirationDate.value" />
        </div>
        
     
     <!--  Drivers License Group of fields -->
        
        <div class="yui3-control-group">
          <label for="identifying-numbers-country">Country:<span class="asterisk" id="reqch">*</span></label>
          <select id="identifying-numbers-country" tabindex="70" class="country" name="ltLeadsModel[0].identifyingNumberModel[0].countryCode.abbreviation">
              <option value="">Select...</option>
              <c:forEach var="option" items="${requestScope.supportDataCache['CountryCode']}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
           </select>
        </div> 
          
        <div class="yui3-control-group">
        	<label for="dlIssueDate">Issue Date:</label>
        	<input id="dlIssueDate" name="dlIssueDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueDate.value" />
        </div>
        
        <div class="yui3-control-group">
        	<label for="dlExpDate">Expiration Date:</label>
        	<input id="dlExpDate" name="dlExpDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].expirationDate.value" />
        </div>
        
        <!-- State ID Fields -->  
 		
       <div class="yui3-control-group">
        	<label for="stateIdIssueDate">Issue Date:</label>
        	<input id="stateIdIssueDate" name="stateIdIssueDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueDate.value" />
        </div>
        
        <div class="yui3-control-group">
        	<label for="stateIdExpDate">Expiration Date:</label>
        	<input id="stateIdExpDate" name="dlExpDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].expirationDate.value" />
        </div>
        
        <!-- Visa Control -->
        
        <div class="yui3-control-group">
        	<label for="vcEventDate">Event Date:</label>
        	<input id="vcEventDate" name="vcEventDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].eventDate.value" />
        </div>
        
        <div class="yui3-control-group">
        	         <label for="vcEventType">Event Type:</label>
          <select id="vcEventType" tabindex="70" class="vcEventType" name="ltLeadsModel[0].identifyingNumberModel[0].eventType.abbreviation">
              <option value="">Select...</option>
              <c:forEach var="event" items="${requestScope.supportDataCache['EventTypeCode']}">
              	<option value="${event.id}:${event.abbreviation}">${event.description}</option>
              </c:forEach>
           </select>
        </div>
        
        <div class="yui3-control-group">
        	         <label for="vcAppLoc">Application Location:</label>
          <select id="vcAppLoc" tabindex="70" class="vcAppLoc" name="ltLeadsModel[0].identifyingNumberModel[0].applicationLocationCode.abbreviation">
              <option value="">Select...</option>
              <c:forEach var="apploc" items="${requestScope.supportDataCache['ApplicationLocationCode']}">
              	<option value="${apploc.id}:${apploc.abbreviation}">${apploc.description}</option>
              </c:forEach>
           </select>
        </div>
        
        <!--  FAA License additional Fields -->
        
        <div class="yui3-control-group">
             	<label for="licIssueDate">Issue Date:</label>
        	<input id="licIssueDate" name="licIssueDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueDate.value" />       
        </div>
        
        <div class="yui3-control-group">
             	<label for="licExpDate">Expiration Date:</label>
        	<input id="licExpDate" name="licExpDate" type="text" class="input mdate" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].expirationDate.value" />       
        </div>
        
         <div class="yui3-control-group">
             	<label for="licIssueLoc">Issue Location:</label>
        	<input id="licIssueLoc" name="licIssueLoc" type="text" class="input" tabindex="70" size="100" name="ltLeadsModel[0].identifyingNumberModel[0].issueLocation" />       
        </div>

        <div class="yui3-control-group">
        <label for="licCode">License Type:</label>
         <select id="licCode" tabindex="70" class="licCode" name="ltLeadsModel[0].identifyingNumberModel[0].licenseTypeCode.abbreviation">
         	<option value="">Select...</option>
            <c:forEach var="licCode" items="${requestScope.supportDataCache['LicenseTypeCode']}">
            <option value="${licCode.id}:${licCode.abbreviation}">${licCode.description}</option>
            </c:forEach>
         </select>
        </div>

       </div>
      </div>

      <div class="identifying-numbers column-two">
       <div class="identifying-numbers-group hidden">
        <!-- Source field -->
        <div class="yui3-control-group multiselect">
          <label for="identifying-numbers-source">Source(s):<span class="asterisk" >*</span></label>
          <input type="image" name="add" class="image" id="identifying-numbers-source-add" src="<c:url value='/resources/images/p.png'/>" alt="Click to add a new Source" title="Click to add a new Source" tabindex="70" />
          <select id="identifying-numbers-source" class="required source" tabindex="70" name="ltLeadsModel[0].identifyingNumberModel[0].sourceCodes[0].abbreviation">
            <option>Select...</option>
              <c:forEach var="option" items="${requestScope.supportDataCache['SourceCode']}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
              </c:forEach>
          </select>
          <input type="image" class="image remove hidden" name="del" id="" src="<c:url value='/resources/images/m.png'/>" tabindex="70" alt="Click to remove Source selection" title="Click to remove Source selection"/>
        </div>
        <!--  Comments -->
        <div class="yui3-control-group comments">
          <label for="identifying-numbers-comments">Comments:</label>
          <textarea id="identifying-numbers-comments" cols="10" rows="5" tabindex="70" maxlength="2000" name="ltLeadsModel[0].identifyingNumberModel[0].comment"></textarea>
        </div>
       </div>
		<div class="clear"></div>
		<div class="buttons hidden">
		  <input id="deleteIdentifyingNumberButton" type="button" class="yui3-button" name="delete" value="Delete" title="Delete associated lead" />
		</div>
      </div>

      <div class="clear"></div>
      <div class="buttons hidden">
        <span><a id="cancelIdentifyingNumberLink" class="yui3-link cancel" href="<c:url value='#identifying-numbers-form'/>" title="Cancel identifying numbers" tabindex="70">Cancel</a></span>
        <input id="addIdentifyingNumberButton" type="button" class="yui3-button" name="add" value="Add" title="Add identifying numbers" tabindex="70" />
        <input id="updateIdentifyingNumberButton" type="button" class="yui3-button" name="update" value="Update" title="Update identifying numbers" tabindex="70" />
        <input id="deleteIdentifyingNumberButton" type="button" class="yui3-button" name="delete" value="Delete" title="Delete identifying numbers" tabindex="70" />
      </div>
  </div>
  <div class="clear">&nbsp;</div>
 </div>