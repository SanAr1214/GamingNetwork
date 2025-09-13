<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Inicio de Sesión - Gaming Network</title>

  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;600;700&display=swap" rel="stylesheet">

  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">

  <style>
    * { margin: 0; padding: 0; box-sizing: border-box; }
    body, html {
      font-family: 'Poppins', sans-serif;
      height: 100%;
      background-color: #0c0c0c;
    }

    .container {
      display: flex;
      height: 100vh;
    }

    /* Lado izquierdo con imagen */
    .left-side {
      flex: 1;
      background: linear-gradient(rgba(0,0,0,0.3), rgba(0,0,0,0.5)),
                  url('<%= request.getContextPath() %>/img/logo.jpg') no-repeat center center;
      background-size: cover;
    }

    /* Lado derecho formulario */
    .right-side {
      flex: 1;
      background-color: #111;
      color: #fff;
      padding: 60px 50px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      box-shadow: -5px 0 25px rgba(0,0,0,0.5);
    }

    /* Título */
    .right-side h2 {
     font-size: 36px;
    margin-bottom: 25px;
    font-weight: 700;
    text-align: center;
    background: linear-gradient(90deg, #ffffff, #e0e0e0); /* Blanco → gris claro */
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    }

    /* Inputs */
    form.login-form {
      display: flex;
      flex-direction: column;
      gap: 18px;
    }

    .input-group {
      position: relative;
    }

    .input-group input {
      width: 100%;
      padding: 16px 50px 16px 15px;
      border: none;
      border-radius: 12px;
      background-color: #1e1e1e;
      color: white;
      font-size: 16px;
      transition: box-shadow 0.3s ease;
    }

    .input-group input::placeholder {
      color: #aaa;
    }

    .input-group input:focus {
      outline: none;
      box-shadow: 0 0 12px #b71c1c;
    }

    .input-group i {
      position: absolute;
      right: 15px;
      top: 50%;
      transform: translateY(-50%);
      color: #888;
      font-size: 18px;
    }

    /* Recordarme y link */
    .olvidaste {
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 14px;
    }

    .olvidaste a {
      color: #888;
      text-decoration: none;
    }
    .olvidaste a:hover { text-decoration: underline; }

    /* Botón login */
    form.login-form button[type="submit"] {
      padding: 14px;
      background: linear-gradient(90deg, #ff4b2b, #ff416c);
      color: #fff;
      font-weight: bold;
      border: none;
      border-radius: 12px;
      font-size: 16px;
      cursor: pointer;
      transition: transform 0.2s ease, box-shadow 0.3s ease;
    }

    form.login-form button[type="submit"]:hover {
      transform: scale(1.04);
      box-shadow: 0 0 15px #ff416c;
    }

    /* Redes sociales */
    .center-text {
      text-align: center;
      margin: 25px 0 12px;
      font-size: 14px;
      color: #ccc;
    }

    .redes {
      display: flex;
      justify-content: center;
      gap: 15px;
    }

    .redes button {
      display: flex;
      align-items: center;
      gap: 8px;
      padding: 12px 22px;
      border: none;
      border-radius: 10px;
      font-size: 15px;
      cursor: pointer;
      color: white;
      font-weight: 600;
      transition: transform 0.2s ease, opacity 0.3s ease;
    }

    .redes button:hover {
      transform: scale(1.05);
      opacity: 0.9;
    }

    .google-btn { background-color: #db4437; }
    .facebook-btn { background-color: #4267B2; }

    /* Signup */
    .signup {
      text-align: center;
      margin-top: 18px;
      color: #ccc;
      font-size: 14px;
    }
    .signup a {
      color: #ff416c;
      font-weight: bold;
      text-decoration: none;
    }
    .signup a:hover { text-decoration: underline; }
    .auth-card {
    width: 420px;
    max-width: 95vw;
    border-radius: 16px;
    padding: 36px 28px;
    background: var(--card-bg);
    box-shadow:
        0 6px 30px rgba(0, 0, 0, 0.7),
        0 0 80px rgba(239, 43, 43, 0.12);
    border: 1px solid rgba(255, 255, 255, 0.06);
    backdrop-filter: blur(10px);
    position: relative;
    animation: proEntry 0.9s cubic-bezier(0.25, 1, 0.3, 1);
}

    .auth-card {
    width: 420px;
    max-width: 95vw;
    border-radius: 16px;
    padding: 36px 28px;
    background: var(--card-bg);
    box-shadow:
        0 6px 30px rgba(0, 0, 0, 0.7),
        0 0 80px rgba(239, 43, 43, 0.12);
    border: 1px solid rgba(255, 255, 255, 0.06);
    backdrop-filter: blur(10px);
    position: relative;

    /* ANIMACIÓN DE ENTRADA */
    opacity: 0; /* estado inicial */
    transform: scale(0.9); /* empieza más pequeño */
    animation: smoothAppear 0.8s ease-out forwards;
}

    @keyframes smoothAppear {
    from {
        opacity: 0;
        transform: scale(0.9);
    }
    to {
        opacity: 1;
        transform: scale(1);
    }
}



    /* Error */
    .error-alert {
      margin-top: 15px;
      background: rgba(183,28,28,0.15);
      border: 1px solid rgba(183,28,28,0.4);
      color: #ffcccc;
      padding: 12px;
      border-radius: 8px;
      font-size: 14px;
    }

    /* Responsive */
    @media (max-width: 768px) {
      .container { flex-direction: column; }
      .left-side { display: none; }
      .right-side { padding: 40px 25px; }
    }
  </style>
</head>
<body>
  <main class="container">
    <aside class="left-side" aria-hidden="true"></aside>

    <section class="right-side" aria-labelledby="loginTitle">
      <h2 id="loginTitle">Iniciar Sesión</h2>

      <form action="${pageContext.request.contextPath}/LoginServlet" method="post" class="login-form" novalidate>
        <div class="input-group">
          <input type="text" id="nickname" name="nickname" placeholder="Nombre de usuario" required autocomplete="username">
          <i class="fa fa-envelope"></i>
        </div>

        <div class="input-group">
          <input type="password" id="contrasena" name="contrasena" placeholder="Contraseña" required autocomplete="current-password">
          <i class="fa fa-lock"></i>
        </div>

        <div class="olvidaste">
          <label>
            <input type="checkbox" name="recordarme"> Recuérdame
          </label>
          <a href="#">¿Olvidaste tu contraseña?</a>
        </div>

        <button type="submit">Iniciar sesión</button>
      </form>

      <% String error = (String) request.getAttribute("error");
         if (error != null) { %>
        <div class="error-alert" role="alert">
          <%= error %>
        </div>
      <% } %>

      <p class="center-text">O inicia sesión con</p>
      <div class="redes">
        <button type="button" class="google-btn">
          <i class="fab fa-google"></i> Google
        </button>
        <button type="button" class="facebook-btn">
          <i class="fab fa-facebook-f"></i> Facebook
        </button>
      </div>

      <p class="signup">¿No tienes cuenta?
        <a href="registroUsuario.jsp">Crea una ahora</a>
      </p>
    </section>
  </main>
</body>
</html>
