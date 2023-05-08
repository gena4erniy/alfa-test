# Alfa-Test AQA Kurchenko V.

Фреймворк для автоматизации тестирования мобильного приложения Alfa-Test (Android).

## Стек технологий:

- Java
- JUnit
- AssertJ
- Appium
- Allure Report
- Gradle
- Selenide
- Owner

## test:

- **java.test.configs** - пакет с классом и интерфейсами для чтения конфигов.
- **java.test.constants** - константы.
- **java.test.driver** - Appium драйвер.
- **java.test.helpers** - вспомогательные классы.
- **java.test.listeners** - пакет лисенером Аллюра.
- **java.test.pages** - классы для работы со страницами/экранами.
- **java.test.tests.authorization** - пакет с тестами Авторизации.
- **java.test.tests.other** - пакет с другими тестами (пока пустой).

## !!! на компьютере должны быть установлены Allure
## !!! для запуска ui-тестов должен быть запущен Appium сервер и эмулятор android с именем (Pixel 5 API 30 2)

## - запуск Allure отчета:

```allure serve build/allure-results```
