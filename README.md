# Курсовой проект "Сервис перевода денег"

## Описание проекта

* MoneyTransferService - приложение - REST-сервис. - предоставляет интерфейс для перевода денег с одной карты на другую по заранее описанной [спецификации](https://github.com/netology-code/jd-homeworks/blob/master/diploma/MoneyTransferServiceSpecification.yaml).

* Подготовленное [веб-приложение](https://github.com/serp-ya/card-transfer) (FRONT) подключается к разработанному сервису и использует его функционал для перевода денег. [Демо](https://serp-ya.github.io/card-transfer/)

Все переводы записываются в **app.log**, он дополняется при каждом запуске, а также при новом переводе.
При запуске приложение использует файл **application.properties** для настройки логгирования и подключения к сети.

## Конфигурация приложения
* конфигурация образа приложения при помощи команды "docker build -t moneytransferapi:1.0 -t moneytransferapi:latest ."
* конфигурация образа front при помощи команды "docker build -t moneytransferfront:1.0 -t moneytransferfront:latest --build-arg APP_DIR=var/app ."
* после конфигурации Docker image для приложения и фронта, приложение запускается командой "docker-compose up"
* порт приложения 5500
* порт фронта 3000

## Эндпоинты

* /transfer - принимает объект с данными формы согласно спецификации
* /confirmOperation - принимает объект с айди операции и секретным кодом 

