# CashFlowPro
Uma API de controle de despesas pessoais.


## Endpoints

- Categorias
    - [Listar Todas](#listar-todas)
    - [Detalhar](#detalhar-categorias)
    - [Cadastrar](#cadastrar-categoria)
    - [Apagar](#apagar-categoria)
    - [Editar](#editar-categoria)

- Movimentações

---

### Listar Todas

`GET` /categoria

Retorna um array com todas as categorias cadastradas.

**Exemplo de Resposta** 

```js
[
    {
        "id": 1,
        "nome": "Alimentação",
        "icone": "fast-food"
    }
]
```

**Códigos de Status** 

| código | descrição |
|--------|-----------|
|200|Dados retornados com sucesso

---

### Detalhar Categorias

`GET` /categoria/{id}

Retornar os dados da categoria com o `id` informado.

**Exemplo de Resposta** 

```js

    {
        "id": 1,
        "nome": "Alimentação",
        "icone": "fast-food"
    }

```

**Códigos de Status** 

| código | descrição |
|--------|-----------|
|200|Dados retornados com sucesso
|404| Id da categoria não encontrado

___


### Cadastrar Categoria

`POST` /categoria

Insere uma nova categoria.

**Corpo da Requisição** 

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|string|✅|Um nome curto para a categoria
|icone|string|❌|O nome do ícone conforme Material Icons


```js
{
    "nome": "Alimentação",
    "icone": "fast-food"
}
```

**Exemplo de Resposta** 

```js
{
    "id": 1,
    "nome": "Alimentação",
    "icone": "fast-food"
}
```

**Códigos de Status** 

| código | descrição |
|--------|-----------|
|201|Categoria criada com sucesso
|400|Erro de validação - verifique o corpo da requisição

---

### Apagar Categoria

`DELETE` /categoria/{id}

Apaga a categoria com o `id` informado.

**Códigos de Status** 

| código | descrição |
|--------|-----------|
|204|Categoria apagada com sucesso
|404| Id da categoria não encontrado

___

### Editar Categoria

`PUT` /categoria/{id}

Atualiza os dados da categoria com o `id` informado.

**Corpo da Requisição** 

|campo|tipo|obrigatório|descrição
|-----|----|:-----------:|---------
|nome|string|✅|Um nome curto para a categoria
|icone|string|✅|O nome do ícone conforme Material Icons


```js
{
    "nome": "Alimentação",
    "icone": "fast-food"
}
```

**Exemplo de Resposta** 

```js
{
    "id": 1,
    "nome": "Alimentação",
    "icone": "fast-food"
}
```

**Códigos de Status** 

| código | descrição |
|--------|-----------|
|200|Categoria atualizada com sucesso
|400| A validação falhou - verifique o corpo da requisição
|404| Id da categoria não encontrado

