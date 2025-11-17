export function getRol() {
  return localStorage.getItem("rol");
}

export function getToken() {
  return localStorage.getItem("token");
}

export function isAuthenticated() {
  return getToken() !== null;
}

export function cerrarSesion() {
  console.log("Cerrando sesión...");
  localStorage.removeItem("token");
  localStorage.removeItem("rol");
  localStorage.removeItem("carrito");
  window.location.href = "/login";
}

export const headers = () => ({
  "Content-Type": "application/json",
  Authorization: "Bearer " + getToken(),
}); // <- quitada la coma final

export function mostrarUsuarioLogeado() {
  const loginZone = document.getElementById("loginZone");
  const userZone = document.getElementById("userZone");
  const userEmail = document.getElementById("userEmail");

  const token = getToken();

  if (token) {
    try {
      const payload = JSON.parse(atob(token.split(".")[1]));
      const correo = payload.sub;

      if (userEmail) userEmail.textContent = correo;

      // Mostrar zona de usuario logueado, ocultar login
      userZone?.classList.remove("d-none");
      loginZone?.classList.add("d-none");
    } catch (e) {
      console.error("Token inválido", e);
      cerrarSesion();
    }
  } else {
    loginZone?.classList.remove("d-none");
    userZone?.classList.add("d-none");
  }
}
