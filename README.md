# Proyecto FidESPN

Sistema cliente-servidor desarrollado en Java que simula una plataforma deportiva donde usuarios pueden consultar partidos, ver eventos en tiempo real y participar en un chat.

---
Objetivo

Desarrollar una aplicación tipo ESPN que permita:
- Crear partidos
- Reportar eventos en tiempo real
- Visualizar el minuto a minuto
- Interactuar mediante chat

---

Tipos de Usuario

Administrador
- Crear partidos
- Ver partidos
- Acceso completo al sistema

Corresponsal
- Ver partidos asignados
- Reportar eventos (gol, tarjetas, etc.)

Fanático
- Ver partidos
- Ver eventos en tiempo real
- Participar en el chat

---

Funcionalidades

- Login con roles desde MySQL
- Arquitectura cliente-servidor con sockets
- Eventos en tiempo real
- Chat por partido
- Interfaz gráfica en Java Swing
- Control de acceso por roles

---

Tecnologías utilizadas

- Java (Sockets, Swing)
- MySQL
- GitHub

---

Ejecución

1. Ejecutar el servidor
2. Ejecutar el cliente
3. Iniciar sesión con:

| Usuario | Contraseña | Rol |
|--------|-----------|-----|
| admin  | 123       | ADMIN |
| corre  | 123       | CORRESPONSAL |
| fan    | 123       | FAN |

---

Comunicación

El sistema utiliza sockets para comunicación en tiempo real entre cliente y servidor.

---

Notas

- Los eventos se actualizan automáticamente sin recargar la interfaz
- El acceso a funcionalidades depende del rol del usuario
