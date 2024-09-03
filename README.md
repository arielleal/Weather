# Weather App

![Screen_Recording_20240902_213238_MyWeather-ezgif com-video-to-gif-converter](https://github.com/user-attachments/assets/8b81df75-9a5c-4478-b1c4-8101adb1d355)

## Descrição

O **Weather App** é um aplicativo que fornece previsões meteorológicas em tempo real. Através de uma interface simples e intuitiva, você pode consultar a temperatura da cidade que você desejar.

## Funcionalidades

- Consulta de previsão do tempo atual e futura por localização atual.
- Busca por localização usando nome da cidade.
- Exibição de informações de tempo para os próximos dias.
- Guarda a ultima cidade pesquisada.
- Interface amigável.

## Tecnologias Utilizadas

- Kotlin
- Koin
- Retrofit
- Mockk
- Coroutines
- SharedPreferences
  
- **API:** HG Weather - https://console.hgbrasil.com/documentation/weather

## Arquitetura utilizada

A arquitetura utilizada para esse projeto foi Clean Architecture + MVVM, para uma melhor separação das camadas e organização do código.

## Melhorias Futuras

- Adicionar nova tela para detalhes por dia da previsão do tempo;
- Criação de Fragments para tela atual e tela de detalhes;
- Criação de uma MainActivity;
- Criar ícone/splash screen;
- Tratamento de erro mais informativo;
- Acessibilidade;
- Adição de testes de integração(Repository);
- Adição de testes instrumentados(Espresso).
