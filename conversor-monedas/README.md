# 💱 Conversor de Monedas — Challenge ONE

[![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=java)](https://www.oracle.com/java/)
[![Gson](https://img.shields.io/badge/Gson-2.10.1-blue?logo=google)](https://github.com/google/gson)
[![ExchangeRate-API](https://img.shields.io/badge/ExchangeRate--API-Free-green)](https://www.exchangerate-api.com/)
[![Status](https://img.shields.io/badge/Status-Completed-brightgreen)]()

> Aplicación de consola en Java que convierte monedas en tiempo real consumiendo la API de **ExchangeRate-API**.

---

## 📋 Descripción

Este proyecto forma parte del **Challenge ONE** de Alura + Oracle Next Education. Consiste en un conversor de monedas por consola que permite al usuario:

- Seleccionar un par de monedas a convertir
- Ingresar el monto deseado
- Obtener el resultado actualizado en tiempo real desde una API externa

---

## 🚀 Funcionalidades

| Opción | Conversión |
|--------|-----------|
| 1 | 🇺🇸 Dólar (USD) → Peso Argentino (ARS) |
| 2 | 🇦🇷 Peso Argentino (ARS) → Dólar (USD) |
| 3 | 🇺🇸 Dólar (USD) → Real Brasileño (BRL) |
| 4 | 🇧🇷 Real Brasileño (BRL) → Dólar (USD) |
| 5 | 🇺🇸 Dólar (USD) → Peso Colombiano (COP) |
| 6 | 🇨🇴 Peso Colombiano (COP) → Dólar (USD) |
| 7 | 🚪 Salir |

---

## 🛠️ Tecnologías Utilizadas

- **Java 17+** — lenguaje principal
- **java.net.http (HttpClient)** — para solicitudes HTTP nativas
- **Gson 2.10.1** — para parsear respuestas JSON
- **ExchangeRate-API** — fuente de tasas de cambio en tiempo real

---

## ⚙️ Requisitos Previos

1. **Java JDK 17 o superior** instalado  
   → [Descargar Java JDK](https://www.oracle.com/java/technologies/downloads/)

2. **Biblioteca Gson 2.10.1** (archivo `.jar`)  
   → [Descargar desde Maven Central](https://search.maven.org/artifact/com.google.code.gson/gson/2.10.1/jar)

3. **Clave de API gratuita** de ExchangeRate-API  
   → [Registrarse en ExchangeRate-API](https://www.exchangerate-api.com/)

---

## 📁 Estructura del Proyecto

```
conversor-monedas/
├── src/
│   └── com/
│       └── conversor/
│           └── ConversorMonedas.java   ← Código principal
├── lib/
│   └── gson-2.10.1.jar                ← Biblioteca Gson
└── README.md
```

---

## 🔧 Configuración y Ejecución

### 1. Clonar o descargar el repositorio

```bash
git clone https://github.com/tu-usuario/conversor-monedas.git
cd conversor-monedas
```

### 2. Agregar tu API Key

Abre el archivo `src/com/conversor/ConversorMonedas.java` y reemplaza en la línea:

```java
private static final String API_KEY = "TU_API_KEY_AQUI";
```

por tu clave real, por ejemplo:

```java
private static final String API_KEY = "abc123xyz456";
```

### 3. Agregar la biblioteca Gson

- Descarga `gson-2.10.1.jar` desde [Maven Central](https://search.maven.org/artifact/com.google.code.gson/gson/2.10.1/jar)
- Colócalo dentro de la carpeta `lib/`

### 4. Compilar el proyecto

```bash
javac -cp lib/gson-2.10.1.jar -d out src/com/conversor/ConversorMonedas.java
```

### 5. Ejecutar el proyecto

```bash
java -cp out:lib/gson-2.10.1.jar com.conversor.ConversorMonedas
```

> 💡 **En Windows**, usa `;` en lugar de `:` para separar el classpath:
> ```bash
> java -cp out;lib/gson-2.10.1.jar com.conversor.ConversorMonedas
> ```

---

## 💻 Ejemplo de Uso

```
*************************************
Sea bienvenido/a al Conversor de Moneda =]
*************************************

=============================================
          MENÚ DE CONVERSIÓN DE MONEDAS
=============================================
1) Dólar (USD)          =>> Peso Argentino (ARS)
2) Peso Argentino (ARS) =>> Dólar (USD)
3) Dólar (USD)          =>> Real Brasileño (BRL)
4) Real Brasileño (BRL) =>> Dólar (USD)
5) Dólar (USD)          =>> Peso Colombiano (COP)
6) Peso Colombiano (COP) =>> Dólar (USD)
7) Salir
=============================================
Elige una opción válida: 1

Ingresa el valor que deseas convertir: 25

✅ El valor de 25.00 [USD] corresponde al valor final de 20293.75 [ARS]
```

---

## 🌐 API Utilizada

**ExchangeRate-API** — [https://www.exchangerate-api.com/](https://www.exchangerate-api.com/)

- ✅ Gratuita hasta 1,500 solicitudes/mes
- ✅ Actualización diaria de tasas
- ✅ Fácil integración con Java

**Endpoint utilizado (consulta por pares):**

```
https://v6.exchangerate-api.com/v6/{API_KEY}/pair/{MONEDA_ORIGEN}/{MONEDA_DESTINO}
```

**Ejemplo de respuesta JSON:**

```json
{
  "result": "success",
  "base_code": "USD",
  "target_code": "ARS",
  "conversion_rate": 811.75
}
```

---

## 👩‍💻 Cómo Funciona el Código

1. **Menú interactivo**: se muestra en un bucle `while` usando `Scanner` para leer la entrada del usuario.
2. **Construcción de URL**: según la opción elegida, se forma la URL con el par de monedas.
3. **Solicitud HTTP**: se usa `HttpClient` y `HttpRequest` para llamar a la API.
4. **Parseo JSON**: la respuesta se procesa con `Gson` para extraer la tasa de conversión.
5. **Cálculo y muestra**: se multiplica el monto ingresado por la tasa y se muestra el resultado.

---

## 📌 Posibles Mejoras (Extras)

- [ ] Historial de conversiones guardado en archivo `.txt`
- [ ] Soporte para más monedas (CLP, BOB, etc.)
- [ ] Marca de tiempo en cada conversión
- [ ] Interfaz gráfica con JavaFX o Swing

---

## 📄 Licencia

Este proyecto fue desarrollado con fines educativos como parte del programa **Oracle Next Education (ONE)** con **Alura Latam**.

---

*Desarrollado con ❤️ como parte del Challenge ONE — Alura + Oracle*
