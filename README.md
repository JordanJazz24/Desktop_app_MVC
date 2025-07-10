# MVC_desktop_app

**Autor:** [JordanJazz24](https://github.com/JordanJazz24)  
**Repositorio:** [Desktop_app_MVC](https://github.com/JordanJazz24/Desktop_app_MVC)  
**Lenguaje Principal:** Java  
**Patrón de Diseño:** MVC (Modelo-Vista-Controlador)  
**Persistencia:** Sin persistencia de datos en base de datos (datos en memoria/XML)

---

## Descripción

MVC_desktop_app es una aplicación de escritorio para la gestión de inventario de una ferretería, desarrollada en Java siguiendo el patrón de diseño MVC. El enfoque modular y el uso de clases especializadas para lógica, datos y presentación, permiten una estructura escalable, mantenible y fácil de entender.

La aplicación permite la administración de tipos de instrumentos, instrumentos, calibraciones y mediciones. La persistencia de datos se logra a través de archivos XML (opcional), y el sistema está preparado para futuras extensiones con bases de datos u otros mecanismos de almacenamiento.

---

## Características principales

- **Gestión de tipos de instrumentos:**  
  Crea, actualiza y elimina tipos de instrumentos (ej: Termómetro, Barómetro, Balanza, Voltímetro) con sus unidades de medida.

- **Control de instrumentos:**  
  Permite registrar instrumentos con sus características, asociarlos a un tipo y gestionar sus límites.

- **Registro y administración de calibraciones:**  
  Asocia calibraciones a instrumentos, con fecha y conjunto de mediciones.

- **Gestión de mediciones:**  
  Generación automática de mediciones a partir del rango del instrumento y la cantidad especificada.

- **Validaciones de integridad:**  
  No permite eliminar tipos con instrumentos asociados ni modificar instrumentos con calibraciones existentes.

- **Persistencia opcional en XML:**  
  Los datos pueden serializarse a y desde archivos XML usando JAXB, lo que permite conservar el estado de la aplicación entre sesiones.

---

## Estructura del proyecto

```
Desktop_app_MVC/
├── src/
│   └── main/java/SILAB/
│       ├── data/            # Clases de datos y persistencia (Data.java)
│       ├── logic/           # Lógica de negocio (Service, Entidades: Instrumento, Calibracion, Medicion, TipoInstrumento)
│       └── ... (MVC: Vistas y Controladores)
└── README.md
```

---

## Principales entidades y flujo

- **TipoInstrumento:**  
  - Código, nombre y unidad.
  - Relación 1:N con Instrumento.

- **Instrumento:**  
  - Serie, descripción, rango (mínimo, máximo), tolerancia y tipo asociado.
  - Relación 1:N con Calibracion.

- **Calibracion:**  
  - Número, instrumento, fecha y lista de mediciones.

- **Medicion:**  
  - Número, referencia y lectura.

---

## Ejemplo de uso

1. **Crear tipo de instrumento:**  
   - Termómetro, Barómetro, etc.
2. **Registrar instrumentos:**  
   - Serie, descripción, rango, tolerancia, tipo.
3. **Agregar calibraciones:**  
   - Relacionar con instrumento, fecha, y generar mediciones.
4. **Gestionar mediciones:**  
   - Visualizar y modificar lecturas.

---

## Instalación y ejecución

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/JordanJazz24/Desktop_app_MVC.git
   cd Desktop_app_MVC
   ```
2. **Abre el proyecto en tu IDE Java favorito (Netbeans, IntelliJ IDEA, Eclipse, etc.).**
3. **Ejecuta la clase principal de la aplicación.**

---

## Extensión y personalización

- Puedes adaptar la lógica de persistencia para usar una base de datos relacional o NoSQL.
- El diseño MVC permite añadir nuevas funcionalidades fácilmente, como reportes, exportación de datos, o integración con otros sistemas.

---

## Contacto

Para dudas, sugerencias o soporte, contacta a [JordanJazz24](https://github.com/JordanJazz24).

---

**Este proyecto demuestra buenas prácticas de arquitectura, separación de responsabilidades y uso efectivo del patrón MVC para aplicaciones de escritorio en Java.**
