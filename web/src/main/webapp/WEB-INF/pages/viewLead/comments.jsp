 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<form:form commandName="model" class="yui3-form yui3-form-aligned" method="post">
<div id="lead-comments-form" class="update-form yui3-form yui3-form-aligned">
 <h4 class="title">Lead Comments</h4>
  <span class="expand-collapse-link<c:if test="${empty model.commentModel.ltLeadComments}"> hidden</c:if>"><a id="expandAllCommentsButton" href="#">Expand All</a></span>
	<div class="lead-comments-data-table">
	<table class="data-table<c:if test="${empty model.commentModel.ltLeadComments}"> hidden</c:if>">
	  <thead>
	  <tr class="table-top-line">
	    <td width="25%"><strong>Added by</strong></td>
	    <td width="20%"><strong>Date</strong></td>
	    <td><strong>Comment</strong></td>
	    <td width="5%" class="no-table-style"></td>
	  </tr>
	  </thead>
	  <tbody>
		<c:forEach var="comment" items="${model.commentModel.ltLeadComments}" varStatus="status" >
		<tr <c:if test="${(status.index + 1) % 2 eq 0}">class="table-even"</c:if><c:if test="${(status.index + 1) % 2 ne 0}">class="table-odd"</c:if>>
			<td><a href="#">${comment.ltUserByCreateBy.lastname}, ${comment.ltUserByCreateBy.firstname}</a></td>
			<td><f:formatDate pattern="MM/dd/yyyy - Hm" value="${comment.createDate}" /></td>
			<td class="ellipsis"><span class="ellipsis" title="${comment.leadComment}">${comment.leadComment}</span></td>
			<td class="no-table-style"><c:if test="${comment.ltUserByCreateBy.id == requestScope.currentUser.id}"><input type="image" name="del" class="image remove" id="deleteCommentImage-<c:out value="${status.index}"/>" src="/leadtrac-web/resources/images/m.png" alt="Click to remove comment" title="Click to remove comment" tabindex="50"></c:if>
			<input type="hidden" name="row" value="${status.index}" />
			</td>
		</tr>
		</c:forEach>
	  </tbody>
	</table>
	</div>

    <!-- Comments -->
    <div class="yui3-control-group comments expand-collapse">
      <div class="clear"></div>
      <h5 class="title"></h5>
      <span class="expand-collapse-link hidden"><br/><a id="collapseCommentLink" href="#" tabindex="50">Collapse</a></span>
      <div class="clear"></div>
      <label for="leadComments">Comments:</label>
      <textarea id="leadComments" name="commentModel.comments[0].text" cols="" rows="7" class="textarea" tabindex="50" maxlength="2000"></textarea>
		<div class="clear"></div>
		<div class="buttons hidden">
		  <input id="deleteCommentButton" type="button" class="yui3-button" name="delete" value="Delete" title="Delete lead comment" />
		  <input type="hidden" name="commentModel.comments[0].user" value="${requestScope.currentUser.id}:${requestScope.currentUser.lastname}, ${requestScope.currentUser.firstname}" />
		  <input type="hidden" name="commentModel.comments[0].date.value" value="" />
		  <input type="hidden" name="commentModel.comments[0].id" value="" />
		  <input type="hidden" name="commentModel.comments[0].editable" value="" />
		</div>
    </div>
	<div class="clear"></div>
	<div class="buttons">
	  <span><a id="cancelCommentLink" class="yui3-link cancel" href="<c:url value='#'/>" title="Cancel lead comment" tabindex="50">Cancel</a></span>
	  <input id="addCommentButton" type="button" class="yui3-button" name="add" value="Add" title="Add lead comment" tabindex="50" />
	  <input id="updateCommentButton" type="button" class="yui3-button hidden" name="update" value="Update" title="Update lead comment" tabindex="50" />
	  <input id="deleteCommentButton" type="button" class="yui3-button hidden" name="delete" value="Delete" title="Delete lead comment" tabindex="50" />
	</div>
	<div class="clear"></div>
	<c:forEach var="comment" items="${model.commentModel.ltLeadComments}" varStatus="status" >
	    <div class="yui3-control-group comments expand-collapse-div expand-collapse-div-<c:out value="${status.index}" /> hidden">
	      <div class="clear"></div>
	      <h5 class="title"><c:out value="${comment.ltUserByCreateBy.lastname}, ${comment.ltUserByCreateBy.firstname}"/> : <f:formatDate pattern="MM/dd/yyyy - Hm" value="${comment.createDate}" /></h5>
	      <span class="expand-collapse-link hidden"><br/><a id="collapseCommentLink" href="#" tabindex="50">Collapse</a></span>
	      <div class="clear"></div>
	      <label for="leadComments">Comments:</label>
	      	<textarea readonly="readonly" class="readonly"><c:out value="${comment.leadComment}" /></textarea>
			<div class="clear"></div>
			<div class="buttons hidden">
			  <input id="<c:out value="${status.index}" />:deleteCommentButton" type="button" <c:if test="${comment.ltUserByCreateBy.id ne requestScope.currentUser.id}">disabled="disabled"</c:if> class="yui3-button" name="delete" value="Delete" title="Delete lead comment" />
			</div>
	    </div>
	</c:forEach>
	<div class="clear">&nbsp;</div>
	<input type="hidden" name="currentUser" value="${requestScope.currentUser.id}:${requestScope.currentUser.lastname}, ${requestScope.currentUser.firstname}" />
 	<input type="hidden" name="ltLead.id" value="${model.ltLead.id}" />
 	<input type="hidden" name="ltLead.ltSubject.id" value="${model.ltLead.ltSubject.id}" />
</div>
</form:form>