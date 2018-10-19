<%-- 
    Document   : index
    Created on : 19/09/2018, 19:56:34
    Author     : anderson
--%>

<%@page import="pojo.Usuario"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
      <form action="Cadastrar" method="post" >
                Login: <input type="text" name="login"  >
                Senha: <input type="password" name="senha"  >
                <input type="submit" value="Cadastrar" >
                <input type="reset" value="Limpar" >
       </form> 
        
       <%
           Class.forName("com.mysql.jdbc.Driver");
            Connection c = DriverManager.getConnection("jdbc:mysql://localhost/batepapo","batepapo","batepapo");
            PreparedStatement  p =  c.prepareStatement("select * from usuario");
            ResultSet r =   p.executeQuery() ; 
       %>
       <br><br>
<table border="1">
    <tr> 
        <td> Login </td> 
        <td> Senha </td> 
        <td> Ação </td> 
     </tr> 
        <%while (r.next()) {  %>      
            <tr> 
                <td> <%= r.getString("login") %> </td> 
                <td> <%= r.getString("senha") %> </td> 
                  <td><% out.print("<a href='Cadastrar?id=" + r.getString("id_usuario") + "' > Excluir </a>"); %> </td>
            </tr> 
            <% }     %>
</table>
    </body>
</html>
