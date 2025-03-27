# Taller App

## Descripción
Taller App es una aplicación backend integral diseñada para gestionar operaciones de un taller mecánico. La aplicación permite un seguimiento detallado de clientes, vehículos y sus respectivas órdenes de reparación, documentando los servicios prestados y sus costos asociados. Esta solución proporciona una herramienta eficiente para administrar el historial de mantenimiento y reparaciones de los vehículos de los clientes.

## Características Técnicas

### Tecnologías Principales
- **Spring Boot**: Framework utilizado junto a Java para el desarrollo eficiente de la aplicación
- **Maven**: Herramienta de gestión del proyecto
- **Lombok**: Biblioteca de reducción de código 
- **JPA/Hibernate**: Framework ORM para la persistencia de datos
- **MySQL**: Sistema de gestión de base de datos relacional

### Patrones de Diseño e Implementación
- **Patrón DTO**: Transferencia de datos entre capas de la aplicación
- **Patrón Builder**: Construcción de objetos complejos paso a paso
- **Patrón Factory**: Creación de objetos sin especificar la clase exacta
- **Patrón Singleton**: Garantizar una única instancia de ciertas clases
- **Inversión de Control (IoC)**: Principio de diseño implementado mediante Spring
- **Inyección de Dependencias**: Suministro de dependencias a objetos mediante Spring

### Funcionalidades Específicas
- **Manejo de Errores**: Sistema robusto para la gestión de excepciones
- **Proyecciones JPA**: Recuperación selectiva de datos de entidades
- **Consultas Personalizadas**: Implementación de queries específicas para operaciones complejas
- **Mappers**: 
  - Model Mapper para transformaciones objeto-objeto automáticas
  - Mappers personalizados para casos específicos

### Despliegue
- **Docker**: Containerización de la aplicación para facilitar el despliegue
- **Railway**: Plataforma de hosting donde la aplicación está desplegada y disponible

## Estado del Proyecto
Este proyecto es una API de backend completamente funcional, lista para ser consumida por cualquier cliente frontend o aplicación que se conecte a sus endpoints.
