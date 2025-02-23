> # Documentação da API de Pagamentos
> 
> ## Visão Geral
> 
> Esta API de pagamentos permite que os usuários processem pagamentos e consultem o status de transações. A API é construída com o Spring Boot e utiliza um repositório para armazenar informações sobre transações.
> 
> ## Estrutura da API
> 
> A API possui dois endpoints principais:
> 
> 1. **Criar Pagamento**: `POST /api/payments`
> 2. **Consultar Status da Transação**: `GET /api/payments/{transactionId}`
> 
> ### 1. Criar Pagamento
> 
> **Endpoint**: `POST /api/payments`
> 
> **Descrição**: Este endpoint permite que um usuário processe um pagamento. O usuário deve fornecer informações sobre o pagamento, incluindo o método de pagamento, valor, moeda e, se aplicável, detalhes do cartão de crédito.
> 
> **Requisição**:
> 
> - **URL**: `/api/payments`
> - **Método**: `POST`
> - **Corpo da Requisição** (JSON):
>     ```json
>     {
>         "paymentMethod": "CREDIT_CARD", // ou "PIX"
>         "amount": 100.00,
>         "currency": "BRL",
>         "cardNumber": "1234567890123456", // necessário se o método for CARTÃO DE CRÉDITO
>         "cardExpiry": "12/25", // necessário se o método for CARTÃO DE CRÉDITO
>         "cardCvv": "123", // necessário se o método for CARTÃO DE CRÉDITO
>         "description": "Pagamento de teste",
>         "pixKey": "chave-pix@example.com" // necessário se o método for PIX
>     }
>     ```
> 
> **Resposta**:
> 
> - **Código de Status**: `201 Created`
> - **Corpo da Resposta** (JSON):
>     ```json
>     {
>         "success": true,
>         "data": {
>             "transactionId": "CREDIT_CARD-123e4567-e89b-12d3-a456-426614174000",
>             "status": "CREATED",
>             "amount": 100.00,
>             "currency": "BRL",
>             "paymentMethod": "CREDIT_CARD",
>             "description": "Pagamento de teste",
>             "createdAt": "2023-10-01T12:00:00Z"
>         }
>     }
>     ```
> 
> ### 2. Consultar Status da Transação
> 
> **Endpoint**: `GET /api/payments/{transactionId}`
> 
> **Descrição**: Este endpoint permite que um usuário consulte o status de uma transação específica usando o ID da transação.
> 
> **Requisição**:
> 
> - **URL**: `/api/payments/{transactionId}`
> - **Método**: `GET`
> - **Parâmetros**:
>     - `transactionId`: O ID da transação que você deseja consultar.
> 
> **Resposta**:
> 
> - **Código de Status**: `200 OK`
> - **Corpo da Resposta** (JSON):
>     ```json
>     {
>         "success": true,
>         "data": {
>             "transactionId": "CREDIT_CARD-123e4567-e89b-12d3-a456-426614174000",
>             "status": "CREATED",
>             "amount": 100.00,
>             "currency": "BRL",
>             "paymentMethod": "CREDIT_CARD",
>             "description": "Pagamento de teste",
>             "createdAt": "2023-10-01T12:00:00Z"
>         }
>     }
>     ```
> 
> - **Código de Status**: `404 Not Found` (se a transação não for encontrada)
> - **Corpo da Resposta** (JSON):
>     ```json
>     {
>         "success": false,
>         "message": "Transação não encontrada"
>     }
>     ```
> 
> ## Testando a API
> 
> Para testar a API, você pode usar ferramentas como Postman ou cURL. Aqui estão alguns exemplos de como fazer isso:
> 
> ### Testando a Criação de Pagamento
> 
> **Usando cURL**:
> ```bash
> curl -X POST http://localhost:8080/api/payments \
> -H "Content-Type: application/json" \
> -d '{
>     "paymentMethod": "CREDIT_CARD",
>     "amount": 100.00,
>     "currency": "BRL",
>     "cardNumber": "1234567890123456",
>     "cardExpiry": "12/25",
>     "cardCvv": "123",
>     "description": "Pagamento de teste"
> }'
> ```
> 
> ### Testando a Consulta de Status da Transação
> 
> **Usando cURL**:
> ```bash
> curl -X GET http://localhost:8080/api/payments/CREDIT_CARD-123e4567-e89b-12d3-a456-426614174000
> ```
