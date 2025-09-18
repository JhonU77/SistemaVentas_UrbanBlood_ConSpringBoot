# Sistema de Ventas - Tienda: Urban Blood

Proyecto desarrollado en **Spring Boot** para la gestión de un sistema de ventas de ropa.  
Este avance forma parte del curso **Herramientas de Desarrollo**.

---

## Objetivo

Aplicar conceptos de control de versiones con **Git y GitHub**, evidenciando:

- Creación de repositorios locales y remotos.
- Manejo de commits significativos.
- Creación de ramas (feature branching).
- Fusión de ramas en la principal.
- Buenas prácticas de organización del proyecto con `.gitignore`.

---

## Tecnologías utilizadas

- **Java 17**
- **Spring Boot 3**
- **Maven**
- **Spring Data JPA**
- **JWT (JSON Web Token)**
- **Validation**
- **Lombok**
- **Spring Web**
- **Spring Security**
- **SQL**
- **Git & GitHub**

---

## Funcionalidades implementadas

1. **Backend inicial**: Entity, Dto, Repository, Service, Util.
2. **Validaciones y Controllers**: se añadieron, validaciones y controladores.
3. **Seguridad**: configuración de JWT y Spring Security.
4. **Frontend inicial**:
   - Formulario de registro de usuarios.
   - Login.
   - Ventana de bienvenida.

---

## Estrategia de Branching

Se utilizó la estrategia de **feature branching**:

- La rama principal es `main`.
- Para nuevas funcionalidades se creo la rama secundaria (ejemplo: `feature-backend-validation-controller`).
- Una vez implementadas y probadas, se fusionaron con el `main` mediante `git merge`.

---

## Evidencia de comandos Git

```bash
git config --global user.name
git config --global user.email
git init
git add .
git commit -m "Primer commit: backend con Entity,Dto,Repository,Service,Util"
git remote add origin https://github.com/JhonU77/SistemaVentas_UrbanBlood_ConSpringBoot.git
git push -u origin main

# Creación de rama secundaria
git checkout -b feature-backend-validation-controller
git add .
git commit -m "Segundo commit: agregando al backend validation, controller, config"
git push origin feature-backend-validation-controller

# Fusión de rama secundaria
git checkout main
git pull origin main
git merge feature-backend-validation-controller
git push origin main

# Tercer commit en main
git add .
git commit -m "Tercer commit: agregado formulario de registro, login y ventana de bienvenida"
git push origin main

# Cuarto commit en main subiendo el README
git add README.md
git commit -m "Cuarto commit: agregado README.md con documentación del proyecto"
git push origin main
```
