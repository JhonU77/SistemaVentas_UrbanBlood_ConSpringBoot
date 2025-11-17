import { mostrarUsuarioLogeado, cerrarSesion } from "./auth.js";

document.addEventListener("DOMContentLoaded", () => {
  mostrarUsuarioLogeado();
  window.cerrarSesion = cerrarSesion;
});
