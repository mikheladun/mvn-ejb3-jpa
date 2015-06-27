 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="associated-subjects-form" class="yui3-form yui3-form-aligned">
 <h5 class="title">Associated Lead(s)</h5>
 <span class="expand-collapse-link hidden"><a id="expandAllAssociatedLeadLink" href="#">Expand All</a></span>
 <div class="clear"></div>
	<div class="associated-subjects-data-table">
	<table class="data-table hidden">
	  <thead>
	  <tr class="table-top-line">
	    <td width="28%"><strong>Name</strong></td>
	    <td width="70%"><strong>Relationship</strong></td>
	    <td width="2%" class="no-table-style"></td>
	  </tr>
	  </thead>
	  <tbody>
	  </tbody>
	</table>
	</div>

 	<!-- Associated Lead -->
    <div class="yui3-control-group expand-collapse">
      <div class="clear"></div>
      <span class="expand-collapse-link hidden"><br/><a id="collapseAssociatedLeadLink" href="#">Collapse</a></span>
      <div class="clear"></div>
      <label for="associatedLeadSubject">Subject:</label>
      <select id="associatedLeadSubject" class="select" tabindex="18" name="ltLeadsModel[0].associateModel[0].value">
            <option>Select...</option>
       </select>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <!-- Relationship -->
      <label for="associatedLeadRelation">Relationship:</label>
      <select id="associatedLeadRelation" class="select" tabindex="19" name="ltLeadsModel[0].associateModel[0].relationshipCode.abbreviation">
            <option>Select...</option>
            <c:forEach var="option" items="${model.supportDataModel.relationshipCodes}">
                <option value="${option.id}:${option.description}">${option.description}</option>
            </c:forEach>
       </select>
		<div class="clear"></div>
		<div class="buttons hidden">
		  <input id="deleteAssociatedLeadButton" type="button" class="yui3-button" name="delete" value="Delete" title="Delete associated lead" />
		</div>
    </div>

	<div class="clear"></div>
	<div class="buttons">
	  <span><a id="cancelAssociatedLeadLink" class="yui3-link cancel" href="<c:url value='#'/>" title="Cancel associated lead">Cancel</a></span>
	  <input id="addAssociatedLeadButton" type="button" class="yui3-button" name="add" value="Add" title="Add associated lead" />
	  <input id="deleteAssociatedLeadButton" type="button" class="yui3-button hidden" name="delete" value="Delete" title="Delete associated lead" />
	  <input id="updateAssociatedLeadButton" type="button" class="yui3-button hidden" name="update" value="Update" title="Update associated lead" />
	</div>

</div>