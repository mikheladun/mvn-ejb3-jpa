<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<form:form commandName="model" id="reg">
  <h2>Form</h2>
  <table>
    <tbody>
      <tr>
        <td><form:label path="member.name">User Name:</form:label></td>
        <td><form:input path="member.name" /></td>
        <td><form:errors class="invalid" path="member.name" /></td>
      </tr>
      <tr>
        <td><form:label path="member.email">Email:</form:label></td>
        <td><form:input path="member.email" /></td>
        <td><form:errors class="invalid" path="member.email" /></td>
      </tr>
      <tr>
        <td><form:label path="member.phoneNumber">Phone #:</form:label>
        <td><form:input path="member.phoneNumber" /></td>
        <td><form:errors class="invalid" path="member.phoneNumber" /></td>
      </tr>
      <tr>
        <td></td>
        <td colspan="2"><input type="submit" value="Save" class="register" /></td>
      </tr>
    </tbody>
  </table>
</form:form>
<h2>Users</h2>
<c:choose>
  <c:when test="${model.members.size() == 0}">
    <em>No registered users.</em>
  </c:when>
  <c:otherwise>
<table class="data-table">
  <tr class="table-top-line">
          <td>Id</td>
          <td>Name</td>
          <td>Email</td>
          <td>Phone #</td>
          <td>REST URL</td>
        </tr>
        <c:forEach items="${model.members}" var="member">
          <tr>
            <td>${member.id}</td>
            <td>${member.name}</td>
            <td>${member.email}</td>
            <td>${member.phoneNumber}</td>
            <td><a href="<c:url value="/rest/personnel/${member.id}"/>">/rest/personnel/${member.id}</a></td>
        </c:forEach>
      </tbody>
    </table>
    <div>REST URL for all users: <a href="<c:url value="/rest/personnel"/>">/rest/users</a></div>
  </c:otherwise>
</c:choose>