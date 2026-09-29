# Medifast - Sistema Web de Gestión de Citas y Simulación de Pagos

Sistema web desarrollado para el **Consultorio Médico Medifast** (Trujillo, 2026), orientado a centralizar y organizar las actividades administrativas asociadas a la gestión de citas médicas, control de disponibilidad y simulación del proceso de pago mediante Yape.

---

## 📌 Descripción del Proyecto

El Consultorio Medifast, bajo la dirección del **Dr. Moisés Barrantes Cabrera** (especialista en Medicina Interna y Reumatología), gestionaba tradicionalmente su agenda mediante mecanismos manuales y anotaciones físicas, lo que originaba sobrecarga administrativa y riesgo de cruce de horarios.

Este proyecto implementa una solución web desarrollada bajo la metodología ágil **Extreme Programming (XP)**. Su alcance está estrictamente delimitado al ámbito administrativo de citas, excluyendo el tratamiento de información clínica (historias clínicas, recetas o diagnósticos) y prescindiendo de transacciones monetarias reales.

---

## 🎯 Requerimientos y Casos de Uso Cubiertos

| Código | Requerimiento Funcional | Caso de Uso Asociado | Actor |
| :--- | :--- | :--- | :--- |
| **RF01** | Reservar una cita en un horario disponible | CUS03 – Reservar cita | Paciente |
| **RF02** | Registrar información básica del paciente (DNI, nombre, teléfono) | CUS02 – Registrar paciente | Paciente |
| **RF03** | Simular el proceso de pago mediante Yape | CUS04 – Simular pago mediante Yape | Paciente |
| **RF04** | Consultar citas programadas y estado administrativo | CUS05 – Consultar citas programadas | Médico |
| **RF05** | Consultar y mostrar los horarios disponibles | CUS01 – Consultar disponibilidad | Paciente |

### Reglas de Negocio Implementadas
* **RN01:** Un bloque de horario disponible admite una única cita activa para evitar cruces.
* **RN02:** La duración estimada de cada cita médica oscila entre 30 y 60 minutos.

---

## 🛠️ Stack Tecnológico

* **Lenguaje:** Java 17+
* **Backend:** Spring Boot / Java SE
* **Persistencia y Datos:** MySQL 8.x
* **Vistas / UI:** Thymeleaf, HTML5, CSS3 y JavaScript
* **Gestión de Dependencias y Build:** Apache Maven
* **Control de Versiones:** Git y GitHub

---

## 🏛️ Arquitectura del Sistema

El sistema implementa una arquitectura por capas desacopladas:
1. **Capa de Presentación:** Controladores y vistas web adaptables para dispositivos móviles y de escritorio.
2. **Capa de Negocio (Servicios):** `CitaService` orquesta la validación de turnos, registro de pacientes, persistencia y confirmación de citas.
3. **Capa de Dominio / Datos:** Entidades principales (`Paciente`, `Horario`, `Cita`, `Pago`) mapeadas directamente a la base de datos relacional normalizada en 3FN.

---

## 📂 Estructura del Proyecto

```text
medifast/
├── docs/
│   ├── javadoc/               # Documentación generada por Javadoc
│   └── architecture/          # Diagramas UML, BPMN y casos de uso
├── sql/
│   ├── schema.sql             # Script DDL de creación de tablas
│   └── data-test.sql          # Inserciones y datos de prueba
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── medifast/
│   │   │           ├── medifast/
│   │   │           │   └── Medifast.java     # Clase principal (Main)
│   │   │           ├── model/                # Entidades de dominio
│   │   │           │   ├── Cita.java
│   │   │           │   ├── Horario.java
│   │   │           │   ├── Paciente.java
│   │   │           │   └── Pago.java
│   │   │           └── service/              # Lógica de negocio
│   │   │               └── CitaService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/                    # Vistas HTML / Thymeleaf
├── pom.xml
└── README.md
```

---

## ⚙️ Requisitos Previos

Asegúrate de contar con las siguientes herramientas instaladas:
* [Java Development Kit (JDK) 17 o superior](https://www.oracle.com/java/technologies/downloads/)
* [Apache Maven 3.8+](https://maven.apache.org/)
* [MySQL Community Server 8.0+](https://dev.mysql.com/downloads/mysql/)
* [Git](https://git-scm.com/)

---

## 🚀 Guía de Instalación y Ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/tu-usuario/medifast.git
cd medifast
```

### 2. Configurar la Base de Datos
Ejecuta los scripts ubicados en la carpeta `sql/` dentro de tu gestor de base de datos MySQL (Workbench, DBeaver o consola):
```sql
SOURCE sql/schema.sql;
SOURCE sql/data-test.sql;
```

Asegúrate de configurar las credenciales en `src/main/resources/application.properties` si se conecta a base de datos externa:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/medifast_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=tu_contraseña
```

### 3. Compilar el proyecto
Para descargar dependencias y compilar el código fuente:
```bash
mvn clean compile
```

### 4. Ejecutar la Aplicación
Para ejecutar el punto de entrada principal (`Medifast.java`):

* **Vía Maven:**
  ```bash
  mvn exec:java
  ```
* **O como aplicación Spring Boot (si aplica el plugin de boot):**
  ```bash
  mvn spring-boot:run
  ```

---

## 📖 Generación de Javadoc

Para generar la documentación técnica en formato HTML dentro del directorio `docs/javadoc/`:

```bash
javadoc -d docs/javadoc -sourcepath src/main/java -subpackages com.medifast -encoding UTF-8 -charset UTF-8
```

Una vez completado el comando, abre `docs/javadoc/index.html` en cualquier navegador web.

---

## 🎨 Prototipo Navegable

El diseño de experiencia e interfaz de usuario se encuentra documentado y prototipado en:
* **Figma / Prototipo Interactivo:** `[INSERTAR ENLACE AL PROTOTIPO DE FIGMA]`

---

## 👥 Equipo de Desarrollo

* **Estudiantes de Ingeniería de Sistemas e Informática** - Universidad Tecnológica del Perú (UTP).
* **Cliente / Sponsor:** Dr. Moisés Barrantes Cabrera — Consultorio Medifast (Trujillo).