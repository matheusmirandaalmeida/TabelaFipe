# 🔧 Consulta Tabela FIPE - Java + Spring Boot

Este projeto é uma aplicação de linha de comando que consome a [API pública da Tabela FIPE](https://deividfortuna.github.io/fipe/) para exibir informações sobre veículos (carros, motos e caminhões), como modelos, marcas, anos e valores.

## 🧪 Tecnologias utilizadas

- Java 17+
- Spring Boot
- API REST (consumo com `HttpClient`)
- Registros (`record`) do Java
- JSON (com `Jackson`)
- Programação funcional com `Stream API`

## 📦 Funcionalidades

- Escolha entre carro, moto ou caminhão
- Lista de marcas e modelos disponíveis
- Filtro de modelos por nome
- Consulta dos valores por ano do modelo
- Exibição de informações detalhadas de veículos (valor, combustível, ano etc.)

## ▶️ Como executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/seu-repo.git
   ```

2. Acesse a pasta do projeto:
   ```bash
   cd nome-do-projeto
   ```

3. Execute com o Spring Boot:
   ```bash
   ./mvnw spring-boot:run
   ```

Ou via sua IDE preferida (IntelliJ, Eclipse, VS Code), rodando a classe `TabelaFipeApplication`.

---

## 📁 Estrutura principal

```
Portfolio.TabelaFipe
├── model         -> Representações dos dados da API (record)
├── principal     -> Menu e lógica principal da aplicação
├── service       -> Consumo de API e conversão JSON
└── TabelaFipeApplication.java
```

---

## 🤓 Autor

**Matheus Miranda Almeida**  
[GitHub](https://github.com/matheusmirandaalmeida)

