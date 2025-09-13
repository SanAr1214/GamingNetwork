<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.usuario" %>
<%
    usuario u = (usuario) request.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Perfil - Gaming Network</title>
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
<style>
    * { box-sizing: border-box; margin:0; padding:0; }

    body {
        background: linear-gradient(135deg, #181818, #1f1f1f);
        font-family: 'Roboto', sans-serif;
        color: #e0e0e0;
        min-height: 100vh;
        display: flex;
        justify-content: center;
        align-items: center;
        padding: 20px;
    }

    .container {
        background: linear-gradient(145deg, #2c2c2c, #212121);
        padding: 50px 40px;
        border-radius: 20px;
        box-shadow: 0 20px 50px rgba(0,0,0,0.7);
        width: 100%;
        max-width: 650px;
        transition: all 0.4s ease;
        opacity: 0;
        transform: translateY(-40px);
        animation: fadeIn 0.8s forwards;
    }

    @keyframes fadeIn {
        to { opacity: 1; transform: translateY(0); }
    }

    h2 {
        text-align: center;
        margin-bottom: 40px;
        font-weight: 700;
        font-size: 2.2rem;
        background: linear-gradient(to right, #f44336, #ff7961);
        -webkit-background-clip: text;
        -webkit-text-fill-color: transparent;
    }

    .perfil-row {
        display: flex;
        align-items: center;
        margin-bottom: 20px;
    }

    .perfil-row label { flex:1; font-weight:500; font-size:1rem; }
    .perfil-row span { flex:2; color:#cccccc; font-size:1rem; transition: all 0.3s ease; }
    .perfil-row input {
        flex:2;
        padding: 10px 12px;
        border-radius: 10px;
        border: 1px solid #444;
        background-color: #1f1f1f;
        color: #e0e0e0;
        font-size: 1rem;
        transition: all 0.3s ease;
    }
    .perfil-row input:focus {
        border-color: #2196f3;
        box-shadow: 0 0 10px rgba(33,150,243,0.6);
        outline: none;
    }

    .buttons {
        display: flex;
        justify-content: center;
        gap: 20px;
        margin-top: 30px;
    }

    button {
        padding: 12px 28px;
        border:none;
        border-radius:12px;
        cursor:pointer;
        font-weight:600;
        font-size:1rem;
        transition: all 0.3s ease;
        box-shadow: 0 5px 15px rgba(0,0,0,0.4);
    }

    #editarBtn {
        background-color: #2196f3;
        color: #fff;
    }
    #editarBtn:hover {
        background-color:#42a5f5;
        transform:translateY(-3px);
        box-shadow:0 10px 25px rgba(33,150,243,0.5);
    }

    #guardarBtn {
        background-color: #4caf50;
        color:#fff;
        display:none;
    }
    #guardarBtn:hover {
        background-color:#66bb6a;
        transform:translateY(-3px);
        box-shadow:0 10px 25px rgba(76,175,80,0.5);
    }

    #eliminarBtn {
        background-color:#f44336;
        color:#fff;
        margin-top:25px;
        padding:10px 20px;
        border-radius:10px;
    }
    #eliminarBtn:hover {
        background-color:#e53935;
        transform:translateY(-2px);
        box-shadow:0 8px 20px rgba(244,67,54,0.5);
    }

    .error {
        color:#ff5252;
        text-align:center;
        margin-bottom:20px;
        font-weight:500;
    }
    
    .close-btn {
        position: absolute;
        top: 20px;
        right: 20px;
        font-size: 2rem;
        color: #f44336;
        text-decoration: none;
        font-weight: bold;
        transition: transform 0.2s ease, color 0.2s ease;
    }

    .close-btn:hover {
        color: #ff7961;
        transform: scale(1.2);
    }

    /* Ajuste para que el contenedor tenga posición relativa */
    .container {
        position: relative;
    }
</style>
</head>
<body>
<div class="container">
    <a href="<%= request.getContextPath() %>/vista/dashboard.jsp" class="close-btn">&times;</a>


    <h2>Perfil de Usuario</h2>

    <% if(request.getAttribute("error") != null){ %>
        <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>

    <form id="perfilForm" action="perfil" method="post">
        <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">

        <div class="perfil-row">
            <label>Nombre:</label>
            <span id="nombreSpan"><%= u.getNombre() %></span>
            <input type="text" name="nombre" id="nombreInput" value="<%= u.getNombre() %>" style="display:none;">
        </div>

        <div class="perfil-row">
            <label>Nickname:</label>
            <span id="nicknameSpan"><%= u.getNickname() %></span>
            <input type="text" name="nickname" id="nicknameInput" value="<%= u.getNickname() %>" style="display:none;">
        </div>

        <div class="perfil-row">
            <label>Correo:</label>
            <span id="correoSpan"><%= u.getCorreo() %></span>
            <input type="email" name="correo" id="correoInput" value="<%= u.getCorreo() %>" style="display:none;">
        </div>

        <div class="perfil-row">
            <label>Contraseña:</label>
            <span id="contraseñaSpan">********</span>
            <input type="password" name="contraseña" id="contraseñaInput" placeholder="Nueva contraseña" style="display:none;">
        </div>

        <div class="perfil-row">
            <label>Rol:</label>
            <span><%= u.getRol() %></span>
        </div>

        <!-- Botón Editar y Guardar -->
        <div class="buttons">
            <button type="button" id="editarBtn" onclick="activarEdicion()">Editar</button>
            <button type="submit" id="guardarBtn">Guardar Cambios</button>
        </div>
    </form>

    <!-- Botón Eliminar más compacto -->
    <div style="text-align:center;">
        <form action="eliminarUsuario" method="post">
            <input type="hidden" name="idUsuario" value="<%= u.getIdUsuario() %>">
            <button type="submit" id="eliminarBtn" onclick="return confirm('¿Seguro que deseas eliminar tu cuenta?');">Eliminar Cuenta</button>
        </form>
    </div>
</div>

<script>
    function activarEdicion(){
        ["nombre","nickname","correo","contraseña"].forEach(function(id){
            document.getElementById(id+"Span").style.display="none";
            document.getElementById(id+"Input").style.display="inline-block";
        });
        document.getElementById("editarBtn").style.display="none";
        document.getElementById("guardarBtn").style.display="inline-block";
    }
</script>
</body>
</html>
