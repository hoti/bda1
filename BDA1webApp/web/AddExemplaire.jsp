<%@page import="bda1.entity.Statut"%>
<%@page import="bda1.entity.ProduitType"%>
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


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Exemplaire</title>
    </head>
    <body>

    <h1>Add a Exemplaire</h1>
    <form id="addExemplaireForm" action="AddExemplaire" method="post">
    <table>
        <tr><td>Statut :</td>
            <td>
                <%
                    for(Statut t:Statut.values())
                    {
                        %>
                            <input type="radio" name="statut" value="<%= t.toString() %>"><%= t.toString() %><br>
                        <%
                    }
                    %>
            </td>
        </tr>
        <tr><td>Date d'arrivée dans le stock :</td><td><input type="text" id = "DateArriveeStock" name="DateArriveeStock" /></td></tr>
        <tr><td>Produit :</td><td><input type="text" id = "produit" name="produit" /></td></tr>
    </table>
    <input type="submit" id="AddExemplaire" value="AddExemplaire" />
    </form>
   <!-- <a href="ListCompte"><strong>Go to List of compte</strong></a>-->
</body>
</html>
