 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<div id="lead-information-form">
 <h5 class="title">Lead Information</h5>
    <!-- Fields for subject information -->

    <!-- Surname field -->
    <div class="yui3-control-group">
      <label for="sourceOfInformation">Source of Information:<span class="asterisk" >*</span></label>
      <input type="text" id="sourceOfInformation" name="ltLeadsModel[0].ltLeadSource.name" class="input required" tabindex="1" size="24" maxlength="100" />
    </div>
  
    <!-- Source Title -->
    <div class="yui3-control-group">
      <label for="sourceTitle">Source Title:<span class="asterisk" >*</span></label>
      <input type="text" id="sourceTitle" name="ltLeadsModel[0].ltLeadSource.title" class="input required" tabindex="2" size="24" maxlength="100" />
    </div>

    <!--  Source Contact Type -->
    <div class="yui3-control-group">
      <label for="leadSourceContactType">Source Contact Type:<span class="asterisk" >*</span></label>
      <select id="leadSourceContactType" name="ltLeadsModel[0].ltLeadSource.contactTypeCode.abbreviation" class="select required" tabindex="3">
            <option>Select...</option>
            <c:forEach var="option" items="${model.supportDataModel.contactTypeCodes}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
          </c:forEach>
       </select>
    </div>

    <!--  Source Phone -->
    <div class="yui3-control-group">
      <label for="leadSourceContact">Source Contact:<span class="asterisk" >*</span></label>
      <input id="leadSourceContact" name="ltLeadsModel[0].ltLeadSource.contact" type="text" class="input required" tabindex="4" size="24" maxlength="100" />
    </div>

    <!-- Generated From -->
    <div class="yui3-control-group">
      <label for="leadGeneratedFrom">Lead Generated From:<span class="asterisk" >*</span></label>
      <select id="leadGeneratedFrom" name="ltLeadsModel[0].ltLead.leadGeneratedFromCode.abbreviation" class="select required" tabindex="5">
            <option>Select...</option>
            <c:forEach var="option" items="${model.supportDataModel.leadGeneratedFromCodes}">
                <option value="${option.id}:${option.abbreviation}">${option.description}</option>
          </c:forEach>
       </select>
    </div>

    <!-- Lead Type -->
    <div class="yui3-control-group">
      <label for="leadType">Lead Type:<span class="asterisk" >*</span></label>
      <select id="leadType" name="ltLeadsModel[0].ltLead.leadTypeCode.abbreviation" class="select required" tabindex="6">
             <option>Select...</option>
             <c:forEach var="option" items="${model.supportDataModel.leadTypeCodes}">
             <option value="${option.id}:${option.abbreviation}">${option.description}</option>
            </c:forEach>
         </select>
    </div>

    <!-- Comments -->
    <div class="yui3-control-group comments">
      <label for="leadComments">Comments:</label>
      <textarea id="leadComments" name="ltLeadsModel[0].ltLeadComment.leadComment" cols="" rows="10" class="textarea" tabindex="7" maxlength="2000"></textarea>
    </div>
    <div class="clear"></div>

 </div>