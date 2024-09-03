# Weather App

## Descrição

O **Weather App** é um aplicativo que fornece previsões meteorológicas em tempo real. Através de uma interface simples e intuitiva, você pode consultar a temperatura da cidade que você desejar.

## Funcionalidades

- Consulta de previsão do tempo atual e futura por localização atual.
- Busca por localização usando nome da cidade.
- Exibição de informações de tempo para os próximos dias.
- Interface amigável.

## Tecnologias Utilizadas

- Kotlin
- Koin
- Retrofit
- Mockk
- Coroutines
- **API:** HG Weather - https://console.hgbrasil.com/documentation/weather

## Arquitetura utilizada

A arquitetura utilizada para esse projeto foi Clean Architecture + MVVM, para uma melhor separação das camadas e organização do código.

## Melhorias Futuras

- Adicionar nova tela para detalhes por dia da previsão do tempo;
- Criação de Fragments para tela atual e tela de detalhes;
- Criação de uma MainActivity;
- Criar ícone/splash screen;
- Tratamento de erro mais informativo;
- Adição de testes de integração(Repository).
- Adição de testes instrumentados(Espresso).
