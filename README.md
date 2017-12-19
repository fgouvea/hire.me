# Implementação
A API foi implementada em Java, utilizando o framework Spring. Além disso, foi feito um cliente em Javascript (utilizando React) que faz chamadas à API para expor as funcionalidades.

Foi realizado um deploy em uma instância EC2 da AWS.
O cliente pode ser acessado em: http://ec2-18-218-29-185.us-east-2.compute.amazonaws.com/
E a API em: http://ec2-18-218-29-185.us-east-2.compute.amazonaws.com/api/

## Geração de Alias
Quando o usuário não especifica um custom alias, um alias é gerado com caracteres aleatórios de um alfabeto que contém [A-Z], [a-z], [0-9], [\_] e [-]. Caso haja colisão com alguma outra URL já guardada no banco de dados, o tamanho é aumentado em 1 e outro alias é gerado. O tamanho inicial é de 3 caractéres.

Esse procedimento foi projetado tendo em mente que em um encurtador de URLs é importante que os aliases gerados sejam pequenos. Dessa forma, é possível gerar aliases pequenos, e quando já houverem registros o suficiente para colisões ficarem frequentes, o sistema começa a gerar aliases maiores. Foi considerado que o overhead de gerar um alias menor antes não seria grande, e que colisões sucessivas são exponencialmente mais improváveis.

Outras abordagens consideradas foram usar um contador ou uma função de hash na url. Porém, uma função de hash ainda teria que checar colisões (tanto colisões de hash normais quanto colisões com custom alias), e gerar os aliases baseado em um contador global traz problemas de concorrência, e ainda poderia ocorrer colisões com custom aliases. Levando isso em conta, gerar aleatoriamente não traz grandes problemas.

## Redirecionamento
Ao recuperar um alias válido, era possível responder com status 301 e a URL no cabeçalho Location do HTTP, para redirecionar automaticamente. Porém, por considerar que a API deveria ser usada por um cliente independente, que não fosse necessariamente um navegador, optei por apenas retornar a url e deixar que o cliente redirecione.

## Rquisições atendidas pela API
```
GET http://.../api/{alias}
```

```
GET http://.../api/top10Urls
```

```
POST http://.../api/create?url={url}
```

```
POST http://.../api/create?url={url}&alias={alias}
```

# Hire.me
Um pequeno projeto para testar suas habilidades como programador.

## Instruções Gerais

1. *Clone* este repositório
2. Em seu *fork*, atenda os casos de usos especificados e se desejar também os bonus points
3. Envio um e-mail para rh@bemobi.com.br com a seu Nome e endereço do repositorio.

## Projeto

O projeto consiste em reproduzir um encurtador de URL's (apenas sua API), simples e com poucas funções, porém com espaço suficiente para mostrar toda a gama de desenho de soluções, escolha de componentes, mapeamento ORM, uso de bibliotecas de terceiros, uso de GIT e criatividade.

O projeto consiste de dois casos de uso:

1. Shorten URL
2. Retrieve URL

### 1 - Shorten URL
![Short URL](http://i.imgur.com/MFB7VP4.jpg)

1. Usuario chama a API passando a URL que deseja encurtar e um parametro opcional **CUSTOM_ALIAS**
    1. Caso o **CUSTOM_ALIAS** já exista, um erro especifico ```{ERR_CODE: 001, Description:CUSTOM ALIAS ALREADY EXISTS}``` deve ser retornado.
    2. Toda URL criada sem um **CUSTOM_ALIAS** deve ser reduzida a um novo alias, **você deve sugerir um algoritmo para isto e o porquê.**

2. O Registro é colocado em um repositório (*Data Store*)
3. É retornado para o cliente um resultado que contenha a URL encurtada e outros detalhes como
    1. Quanto tempo a operação levou
    2. URL Original

Exemplos (Você não precisa seguir este formato):

* Chamada sem CUSTOM_ALIAS
```
PUT http://shortener/create?url=http://www.bemobi.com.br

{
   "alias": "XYhakR",
   "url": "http://shortener/u/XYhakR",
   "statistics": {
       "time_taken": "10ms",
   }
}
```

* Chamada com CUSTOM_ALIAS
```
PUT http://shortener/create?url=http://www.bemobi.com.br&CUSTOM_ALIAS=bemobi

{
   "alias": "bemobi",
   "url": "http://shortener/u/bemobi",
   "statistics": {
       "time_taken": "12ms",
   }
}
```

* Chamada com CUSTOM_ALIAS que já existe
```
PUT http://shortener/create?url=http://www.github.com&CUSTOM_ALIAS=bemobi

{
   "alias": "bemobi",
   "err_code": "001",
   "description": "CUSTOM ALIAS ALREADY EXISTS"
}
```

### 2 - Retrieve URL
![Retrieve URL](http://i.imgur.com/f9HESb7.jpg)

1. Usuario chama a API passando a URL que deseja acessar
    1. Caso a **URL** não exista, um erro especifico ```{ERR_CODE: 002, Description:SHORTENED URL NOT FOUND}``` deve ser retornado.
2. O Registro é lido de um repositório (*Data Store*)
3. Esta tupla ou registro é mapeado para uma entidade de seu projeto
3. É retornado para o cliente um resultado que contenha a URL final, a qual ele deve ser redirecionado automaticamente

## Stack Tecnológico

Não há requerimentos específicos para linguagens, somos poliglotas. Utilize a linguagem que você se sente mais confortável.

## Bonus Points

1. Crie *testcases* para todas as funcionalidades criadas
2. Crie um *endpoint* que mostre as dez *URL's* mais acessadas
3. Crie um *client* para chamar sua API
4. Faça um diagrama de sequencia da implementação feita nos casos de uso (Dica, use o https://www.websequencediagrams.com/)
5. Monte um deploy da sua solução utilizando containers
