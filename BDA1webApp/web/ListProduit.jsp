<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>

<!--
  Copyright (c) 2010, Oracle. All rights reserved.

  Redistribution and use in source and binary forms, with or without
  modification, are permitted provided that the following conditions are met:

  * Redistributions of source code must retain the above copyright notice,
    this list of conditions and the following disclaimer.

  * Redistributions in binary form must reproduce the above copyright notice,
    this list of conditions and the following disclaimer in the documentation
    and/or other materials provided with the distribution.

  * Neither the name of Oracle nor the names of its contributors
    may be used to endorse or promote products derived from this software without
    specific prior written permission.

  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
  AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
  IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
  ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
  SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
  INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
  CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
  ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
  THE POSSIBILITY OF SUCH DAMAGE.
-->

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Of Produits</title>
    </head>
    <body>

    <h1>List of Produits currently in Database</h1>

<table id="produitsListTable" border="3">
<tr >
    <th bgcolor=>ID</th>
    <th bgcolor=>titre</th>
    <th bgcolor=>peutEtreReemprunter</th>
    <th bgcolor=>Adhérents ayant réservé ce produit</th>
    <th bgcolor=>datePublication</th>
    <th bgcolor=>auteurs</th>
    <th bgcolor=>genres</th>
    <th bgcolor=>type</th>
</tr>
<c:forEach var="produit" begin="0" items="${requestScope.produitList}">
    <tr>
    <td>${produit.id}&nbsp;&nbsp;</td> 
    <td>${produit.titre}&nbsp;&nbsp;</td>
    <td>${produit.peutEtreReemprunter}&nbsp;&nbsp;</td> 
    <td>
        <c:forEach var="adherentDemandeur" begin="0" items="${produit.getAdherentsDemandeurs()}">
            ${adherentDemandeur.id}&nbsp;&nbsp;<br />
        </c:forEach>
    </td>
    
    <td><fmt:formatDate pattern="yyyy-MM-dd" value="${produit.datePublication}" />&nbsp;&nbsp;</td> 
    
    <td>
        <c:forEach var="produitAuteur" begin="0" items="${produit.auteurs}">
            ${produitAuteur.nom}&nbsp;&nbsp;<br />
        </c:forEach>
    </td>
    <td>
        <c:forEach var="produitGenre" begin="0" items="${produit.genres}">
            ${produitGenre.nom}&nbsp;&nbsp;<br />
        </c:forEach>
    </td>
    <td>${produit.type.toString()}&nbsp;&nbsp;</td>    
</tr> 

</c:forEach>

</table>
<a href="AddProduit.jsp"><strong>Add a Produit Record</strong></a>
<a href="ReserverProduit.jsp"><strong>Réserver un Produit</strong></a>
</body>
</html>