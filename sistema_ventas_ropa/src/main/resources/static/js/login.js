import { cerrarSesion } from "./auth.js";

document.addEventListener("DOMContentLoaded", () => {
  const form = document.getElementById("loginForm");

  form.addEventListener("submit", async function (e) {
    e.preventDefault();

    const correo = document.getElementById("correo").value;
    const contrasena = document.getElementById("contrasena").value;

    const response = await fetch("/auth/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ correo, contrasena }),
    });

    if (response.ok) {
      const data = await response.json();
      localStorage.setItem("token", data.token);
      localStorage.setItem("rol", data.rol);
      localStorage.removeItem("jwtToken");

      // Redireccionar según el rol
      if (data.rol === "ROLE_ADMIN") {
        window.location.href = "/admin/platos/crud";
      } else {
        window.location.href = "/Bienvenida";
      }
    } else {
      const errorMsg = document.getElementById("errorMsg");
      errorMsg.textContent = "Correo o contraseña incorrectos.";
      errorMsg.classList.remove("d-none");
    }
  });
});
