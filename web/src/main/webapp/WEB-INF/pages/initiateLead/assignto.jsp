<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div id="assign-to-form" class="yui3-form yui3-form-aligned">
  <h5 class="title">
    Assign To:<span class="asterisk">*</span>
  </h5>

  <div class="yui3-control-group">
   <span>
    <label for="MissionSelect" class="mission">Mission Box:</label>
    <select id="MissionSelect" name="ltLeadsModel[0].ltLead.missionCode.abbreviation" class="mission">
      <option>None</option>
      <c:forEach var="option" items="${model.supportDataModel.missionCodes}">
        <option value="${option.id}:${option.abbreviation}">${option.description}</option>
      </c:forEach>
    </select>
   </span>
   <span> 
    <label for="SupervisorSelect" class="supervisor">Supervisor:</label>
    <select id="SupervisorSelect" name="ltLeadsModel[0].supervisorModel.value" class="supervisor">
      <option value="">None</option>
      <c:forEach var="option" items="${model.supervisorsList}">
        <option value="${option.id}" <c:if test="${option.id == model.currentUser.id}"><c:out value='selected=selected class=active'/></c:if>>${option.lastname}, ${option.firstname}</option>
      </c:forEach>
    </select>
   </span>
   <span>
    <label for="AnalystSelect" class="analyst">Analyst:</label>
    <select id="AnalystSelect" name="ltLeadsModel[0].analystModel.value" class="analyst">
      <option value="">None</option>
      <c:forEach var="option" items="${model.analystsList}">
        <option value="${option.id}" <c:if test="${option.id == model.currentUser.id}"><c:out value='selected=selected class=active'/></c:if>>${option.lastname}, ${option.firstname}</option>
      </c:forEach>
    </select>
   </span>
  </div>
  <div class="clear"></div>
</div>