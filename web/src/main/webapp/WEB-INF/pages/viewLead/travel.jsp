 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<form:form commandName="model" class="yui3-form yui3-form-aligned" method="post">
<div id="travel-information-form" class="update-form yui3-form yui3-form-aligned">
	<h4 class="title">Travel Information</h4>
	
	<span class="expand-collapse-link hidden"><a href="#">Expand All</a></span>
	<div class="travel-information-data-table">
	<table id="travelInformationTable" class="data-table hidden">
	    <thead>
	        <tr class="table-top-line">
	            <th class="thead">Flight Number</th>
	            <th class="thead">Direction</th>
	            <th class="thead">Date</th>           
	            <th class="thead">Source(s)</th>
	            <th class="thead">Comments</th>
	            <th class="no-table-style thead last"></th>
	        </tr>
	    </thead>
	    <tbody>   
	  </tbody>
	</table>
	</div>

  <div class="column-group two-column">
    <div class="travel-information column-one">

	  <!-- Carrier -->
	  <div class="yui3-control-group">
	  <label for="travelInformationCarrier">Carrier:<span class="asterisk" >*</span></label>
	  <input id="travelInformationCarrier" type="text" name="travelModel.ltSubjectTravel.carrier" value="${model.travelModel.ltSubjectTravel.carrier}" type="text" class="input required carrier" tabindex="80" size="25" maxlength="2"/>
	  </div>

	  <!-- Flight Number -->
	  <div class="yui3-control-group">
	  <label for="travelInformationFlightNumber">Flight Number:<span class="asterisk" >*</span></label>
	  <input id="travelInformationFlightNumber" name="travelModel.ltSubjectTravel.flightNumber" value="${model.travelModel.ltSubjectTravel.flightNumber}" type="text" class="input required" tabindex="80" size="25"/>
	  </div>

	  <!-- Direction -->
	  <div class="yui3-control-group">
		<label for="travelInformationDirection" >Direction:<span class="asterisk" >*</span></label>
	    <select id="travelInformationDirection" name="travelModel.ltSubjectTravel.travelDirectionCode.abbreviation" class="select required" tabindex="80">
	      <option>Select...</option>
	      <c:forEach var="option" items="${requestScope.supportDataCache['TravelDirectionCode']}">
	        <option value="${option.id}:${option.abbreviation}"<c:if test="${option.id eq model.travelModel.ltSubjectTravel.travelDirectionCode.id}"><c:out value="selected='selected'" /></c:if>>${option.description}</option>
	      </c:forEach>
	     </select>
	  </div>

	  <!-- Date -->
	  <div class="yui3-control-group">
	    <label for="travelInformationDate">Date:<span class="asterisk" >*</span></label>
	    <input id="travelInformationDate" type="text" name="travelModel.travelDate.value" value="<f:formatDate pattern="MM/dd/yyyy" value="${model.travelModel.ltSubjectTravel.travelDate}" />" class="input required date" tabindex="80" size="25" maxlength="10"/>
	  </div>

    </div>
      
    <div class="travel-information column-two">

	  <!-- Source(s) -->
		<div class="yui3-control-group multiselect">
         <label for="travelInformationSource">Source(s):</label>
         <input type="image" name="add" class="image" id="travelInformationSourceAdd" src="<c:url value='/resources/images/p.png'/>" alt="Click to add a new Source" title="Click to add a new Source" tabindex="80"/>
         <select id="travelInformationSource" class="source" tabindex="80" name="travelModel.sources[0].sourceCode.abbreviation">
           <option>Select...</option>
             <c:forEach var="option" items="${requestScope.supportDataCache['SourceCode']}">
               <option value="${option.id}:${option.abbreviation}">${option.description}</option>
             </c:forEach>
         </select>
         <input type="image" class="image remove hidden" name="del" id="" src="<c:url value='/resources/images/m.png'/>" tabindex="80" alt="Click to remove Source selection" title="Click to remove Source selection"/>

		<c:forEach var="source" items="${model.travelModel.ltSubjectTravel.ltSubjectTravelSources}" varStatus="status" >
			<span class="multiselect" style="display: none;"><select id="entry-source-<c:out value="${status.index + 1}" />" class="hidden" tabindex="30" name="travelModel.sources[<c:out value="${status.index + 1}" />].sourceCode.abbreviation">
			<option value="${source.sourceCode.id}:${source.sourceCode.abbreviation}">${source.sourceCode.description}</option>
	        </select><label for="" tabindex="80"><c:out value="${source.sourceCode.description}" /></label><input type="image" name="del" class="image remove" id="" src="/leadtrac-web/resources/images/m.png" alt="Click to remove Source" title="Click to remove Source" tabindex="80"></span>
		</c:forEach>
		</div>

  	  <!-- Comments -->
	  <div class="yui3-control-group comments">
          <label for="travelInformationComments">Comments:</label>
          <textarea id="travelInformationComments" cols="10" rows="5" tabindex="80" maxlength="4000" name="travelModel.ltSubjectTravel.travelComment">${model.travelModel.ltSubjectTravel.travelComment}</textarea>
      </div>

       <div class="clear"></div>  
      </div>
	  <div class="clear">&nbsp;</div>
	 	<input type="hidden" name="ltLead.id" value="${model.ltLead.id}" />
	 	<input type="hidden" name="ltLead.ltSubject.id" value="${model.ltLead.ltSubject.id}" />
	  </div>
	 </div>
</form:form>