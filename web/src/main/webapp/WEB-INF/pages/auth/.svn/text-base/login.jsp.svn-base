<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

  <form id="login-form" class="yui3-form yui3-form-aligned" action="<c:url value='/j_spring_security_check'/>" method="post">

    <div class="login <c:if test="${param.error != 'true'}">hidden</c:if>">
      <img src="<c:url value='/resources/images/leadtrac-sm.jpg'/>" alt="LeadTrac" title="LeadTrac" />
      <fieldset>
        <legend>Login</legend>

        <c:if test="${param.error == 'true'}"><div id="login-error" class="error pad-lr"><strong><c:out value="User Name or Password is incorrect" /></strong></div></c:if>

        <div class="yui3-control-group">
          <label for="username">User Name:</label>
          <input type="text" id="username" name="j_username" size="33"/>
        </div>

        <div class="yui3-control-group">
          <label for="password">Password:</label>
          <input type="password" id="password" name="j_password" size="33"/>
        </div>

        <p class="pad-tb" align="right">Forgot <a id="forgotUsernameLink" href="#" title="Username">User Name</a> or <a id="forgotPasswordLink" href="#" title="Password">Password</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>
        <hr class="clear" />

        <div class="buttons">
          <span class="cancel"><a id="cancelLoginLink" class="yui3-link cancel" href="<c:url value='/login.jsp'/>" title="Cancel">Cancel</a></span>
          <input id="loginButton" type="submit" class="yui3-button" name="submit" value="Login"/>
        </div>

      </fieldset>
      </div>

      <c:if test="${param.error != 'true'}">
      <div class="agree">
        <img src="<c:url value='/resources/images/leadtrac-mid.jpg'/>" alt="LeadTrac" title="LeadTrac" />

        <h2 class="pad-lr"><strong>Access Agreement</strong></h2>

         <p class="pad-lr">You are accessing a U.S. Government information system, which includes (1) this computer, (2) this computer network, (3) all computers connected to this network, and (4) all devices and storage media attached to this network or to a computer on this network.  This information system is provided for U.S. Government-authorized use only.</p>
         <p class="pad-lr">Unauthorized or improper use or access of this system may result in disciplinary action, as well as civil and criminal penalties.</p>
         <p class="pad-lr">By using this information system, you understand and consent to the following:</p>

         <ul class="pad-lr">
          <li>You have no reasonable expectation of privacy when you use this information system; this includes any communications or data transiting or stored on this information system.  At any time, and for any lawful government purpose, the government may, without notice, monitor, intercept, search and seize any communication or data transiting or stored on this information system.</li>
          <li>The government may disclose or use any communications or data transiting or stored on this information system for any lawful government purpose, including but not limited to law enforcement purposes.</li>
          <li>You are NOT authorized to process classified information on this information system.</li>
         </ul>

         <div class="clear">&nbsp;</div>

        <hr class="clear" />

        <div class="buttons">
         <span><a id="cancelAgreementLink" class="yui3-link cancel" href="javascript:window.close();" title="Cancel">Cancel</a></span>
         <input id="agreementButton" class="yui3-button" type="button" name="Agree" value="Agree" />
        </div>

      </div>
    </c:if>
    <div class="clear">&nbsp;</div>

  </form>