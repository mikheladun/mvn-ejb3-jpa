<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<ul class="breadcrumb"><li><a href="<c:url value='/workQueue'/>">Work Queue</a>&nbsp;&nbsp;&gt;</li><li class="active">Initiate Lead</li></ul>

 <div id="maincontent">
    <div class="contentdiv-border border-top-right"><h4 class="title">Initiate Lead</h4><span><img src="<c:url value='/resources/images/bg-content-tr.jpg'/>" /></span></div>
    <div class="contentdiv-body">

<form:form commandName="model" class="yui3-form yui3-form-aligned" method="post">
<div class="initiate-lead">
<hr class="clear" />
<div class="lead-subject-data-table">
<table class="data-table hidden">
  <thead>
  <tr class="table-top-line">
    <td><strong>Name</strong></td>
    <td><strong>Gender</strong></td>
    <td><strong>DOB</strong></td>
    <td><strong>Lead Type</strong></td>
    <td><strong>Source</strong></td>
    <td><strong>Assigned To</strong></td>
    <td class="no-table-style"></td>
  </tr>
  </thead>
  <tbody>
  </tbody>
</table>
</div>
<div class="column-group two-column">
  <div class="lead-information column-one">
    <jsp:include page="lead.jsp" />
  </div>
  <div class="subject-information column-two">
    <jsp:include page="subject.jsp" />
  </div>
  <div class="clear">&nbsp;</div>
</div>
<hr class="clear" />
<div class="clear">&nbsp;</div>
<div class="associated-subjects hidden">
  <jsp:include page="associates.jsp" />
  <hr class="clear" />
  <div class="clear">&nbsp;</div>
</div>
<div class="identifying-numbers">
  <jsp:include page="numbers.jsp" />
</div>
<div class="clear">&nbsp;</div>
<div class="assign-to">
  <jsp:include page="assignto.jsp" />
  &nbsp;
</div>

<div class="clear"></div>
<div class="buttons initiate-lead-buttons">
  <span><a id="cancelLeadLink" class="yui3-link cancel" href="<c:url value='#'/>" title="Cancel lead information">Cancel</a></span>
  <input id="addLeadButton" type="button" class="yui3-button" name="add" value="Add" title="Add lead information" />
  <input id="deleteLeadButton" type="button" class="yui3-button hidden" name="delete" value="Delete" title="Delete lead information" />
  <input id="updateLeadButton" type="button" class="yui3-button hidden" name="update" value="Update" title="Update lead information" />
</div>
<div class="clear">&nbsp;</div>

<div class="buttons center">
  <input id="initiateLeadButton" type="submit" class="yui3-button" name="initiateLead" value="Initiate" title="Initiate" />
  <span><a id="cancelInitiationLink" class="yui3-link" href="<c:url value='#'/>" title="Cancel Initiation">Cancel Initiation</a></span>
</div>
</div>
<input id="subjectId" type="hidden" name="ltLeadsModel[0].ltLead.ltSubject.id" />
<input id="formId" type="hidden" name="ltLeadsModel[0].formId" />
</form:form>

    </div>
    <div class="contentdiv-border border-bottom-left"><img src="<c:url value='/resources/images/bg-content-bl.jpg'/>" /></div>
 </div>